package aiportal.ai.com.scanner.tasks;

import android.content.Context;

import aiportal.ai.com.scanner.Api.ScannerAPI;
import aiportal.ai.com.scanner.interfac.TaskCallback;

public class GetProductAppTask extends BaseTask {
    private String tireNo ;

    public GetProductAppTask(Context context, String tireNo , TaskCallback callback) {
        super(context, callback);
        this.tireNo = tireNo;
    }

    /**
     * {
     "status": 1,
     "total": 0,
     "msg": "成功",
     "data": {
     "ds": [
     {
     "CreateBy": "杨勇平",
     "CreateDate": "2017-12-25T13:31:34.403",
     "Dep": "检测部",
     "Barcode": "201712251000003",
     "sNo": "JILS",
     "TireNo": "190493",
     "MaterialDesc": "BTR雪地胎",
     "Specification": "22565R18",
     "IsSculptureTire": "1",
     "Brand": "赛轮金宇",
     "LoadIndex": null,
     "SpeedLevel": "H级",
     "Pattern": "1",
     "Level": null,
     "FetalPressure": null,
     "CompleteTime": "2017-12-30T00:00:00",
     "TestLab": "青岛实验中心"
     }
     ],
     "ds1": [
     {
     "RowNo": 1,
     "DESCRIPTION": "成品室外检测项目"
     }
     ]
     }
     }
     * @param voids
     * @return
     */
    @Override
    protected Object doInBackground(Void... voids) {

        String result = null;
        {
            ScannerAPI api = new ScannerAPI();
            result = api.GetProductAppByTireNo(tireNo);
        }
        return result;
    }

}
