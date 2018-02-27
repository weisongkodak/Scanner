package aiportal.ai.com.scanner.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import aiportal.ai.com.scanner.interfac.TaskCallback;
import aiportal.ai.com.scanner.widget.WaitDialog;


public abstract class BaseTask extends AsyncTask<Void, Void, Object> {

    protected String TAG = "";
    private WaitDialog dialog;
    protected Context context;
    private boolean needShowDialog = true;
    protected TaskCallback callback;

    public BaseTask(Context context, TaskCallback callback) {
        this(context, callback, true);
    }

    public BaseTask(Context context, TaskCallback callback, boolean showDialog) {

        this.TAG = this.getClass().getSimpleName();
        this.context = context;
        this.needShowDialog = showDialog;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (needShowDialog && dialog == null) {
            dialog = new WaitDialog();
            dialog.setCancelable(true);
            dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "wait_dialog");
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (dialog != null && dialog.isVisible()) {
            dialog.dismiss();
        }
        if (result == null || result.equals("")) {
            Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
            return;
        } else if (result instanceof Error) {
            Error error = (Error) result;
        } else if (callback == null) {
            //
        } else {
            callback.callback(result);
        }
    }

}
