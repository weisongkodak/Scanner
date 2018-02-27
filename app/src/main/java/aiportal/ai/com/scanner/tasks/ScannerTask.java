package aiportal.ai.com.scanner.tasks;

import android.content.Context;

import aiportal.ai.com.scanner.Api.ScannerAPI;
import aiportal.ai.com.scanner.interfac.TaskCallback;

public class ScannerTask extends BaseTask {
    private String barcode;

    public ScannerTask(Context context, String barcode, TaskCallback callback) {
        super(context, callback);
        this.barcode = barcode;
    }

    /**
     * {
     "status": 1,
     "total": 1,
     "msg": "成功",
     "data": [
     {
     "CreateBy": "杨勇平",
     "CreateDate": "2017-12-25T12:35:53.687",
     "Dep": "检测部",
     "Barcode": "201712251000001",
     "MaterialNo": "A0EF",
     "Specification": null,
     "MaterialDesc": "终炼胶",
     "SerialNo": "PWTL3I",
     "ExteriorStatus": null,
     "ExPurpose": "研发专项",
     "CompleteTime": "2017-12-30T00:00:00",
     "TestLab": "广饶实验室"
     }
     ]
     }
     * @param voids
     * @return
     */
    @Override
    protected Object doInBackground(Void... voids) {

        String result;
        {
            ScannerAPI api = new ScannerAPI();
            result = api.getOrderByBarcode(barcode);
        }
        return result;
    }

}
