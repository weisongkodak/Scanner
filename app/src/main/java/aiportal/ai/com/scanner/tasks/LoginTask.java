package aiportal.ai.com.scanner.tasks;

import android.content.Context;

import aiportal.ai.com.scanner.Api.ScannerAPI;
import aiportal.ai.com.scanner.interfac.TaskCallback;
public class LoginTask extends BaseTask {
    private loginRequest mRequest;

    public LoginTask(Context context, loginRequest mRequest, TaskCallback callback) {
        super(context, callback);
        this.mRequest = mRequest;
    }

    @Override
    protected Object doInBackground(Void... voids) {

        String result;
        {
            ScannerAPI api = new ScannerAPI();
            result = api.login(mRequest.userName,mRequest.passWord);
        }
        return result;
    }

    public static class loginRequest {
        public String userName = "";
        public String passWord = "";
    }
}
