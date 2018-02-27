package aiportal.ai.com.scanner.tasks;

import android.content.Context;

import aiportal.ai.com.scanner.Api.ScannerAPI;
import aiportal.ai.com.scanner.interfac.TaskCallback;

public class ReceiveTask extends BaseTask {
    private String orderNo ;

    public ReceiveTask(Context context, String orderNo , TaskCallback callback) {
        super(context, callback);
        this.orderNo = orderNo;
    }

    @Override
    protected Object doInBackground(Void... voids) {

        String result = null;
        {
            ScannerAPI api = new ScannerAPI();
            result = api.receivedOrder(orderNo);
        }
        return result;
    }

}
