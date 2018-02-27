package aiportal.ai.com.scanner.Activity;


import com.seuic.scanner.DecodeInfo;
import com.seuic.scanner.DecodeInfoCallBack;
import com.seuic.scanner.Scanner;
import com.seuic.scanner.ScannerFactory;
import com.seuic.scanner.ScannerKey;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aiportal.ai.com.scanner.BaseActivity;
import aiportal.ai.com.scanner.R;

public class MainActivity extends BaseActivity implements DecodeInfoCallBack{

    static final int SCANNER_KEYCODE = 142 ;

    EditText mEditText;

    Button btn_setvalue;
    Button btn_getvalue;

    EditText edt_id;
    EditText edt_value;

    Scanner mScanner ;

    SparseIntArray mapParams;

    MainOnClickListener clickListener = new MainOnClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        init();
    }

    void init(){

        //Get Scanner
        mScanner = ScannerFactory.getScanner(this);

        mapParams = mScanner.getAllParams();
        mEditText = (EditText) findViewById(R.id.text);

        btn_getvalue = (Button) findViewById(R.id.btn_getvalue);
        btn_setvalue = (Button) findViewById(R.id.btn_setvalue);

        btn_getvalue.setOnClickListener(clickListener);
        btn_setvalue.setOnClickListener(clickListener);

        edt_id = (EditText) findViewById(R.id.edt_id);
        edt_value = (EditText) findViewById(R.id.edt_value);

        new Thread(runnable).start();

    }

    @Override
    protected void onResume() {
        //Set Callback
        mScanner.setDecodeInfoCallBack(this);
        mScanner.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        //Remove Callback
        mScanner.close();
        mScanner.setDecodeCallBack(null);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        //Close Scanner
        ScannerKey.close();
        Scanner.startScanService(this);
        super.onDestroy();
    }

    class MainOnClickListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_getvalue:
                    getValueToShow();
                    break;
                case R.id.btn_setvalue:
                    setValue();
                    break;
            }
        }
    }


    void setValue(){
        try{
            int id = Integer.parseInt(edt_id.getText().toString());
            int value = Integer.parseInt(edt_value.getText().toString());
            if(mapParams.indexOfKey(id) > -1){
                Toast.makeText(MainActivity.this, "Unsupport Param!", Toast.LENGTH_SHORT).show();
                return ;
            }

            //Set Params
            if(mScanner.setParams(id, value)){
                showMsg("Set param successfully");
            }else{
                showMsg("Error:Settings failed ");
            }
        }catch(Exception ex){
            showMsg("Error:" + ex.getMessage());
        }

    }

    void getValueToShow(){
        try{
            int id = Integer.parseInt(edt_id.getText().toString());
            if(mapParams.indexOfKey(id) > -1){
                showMsg("Unsupport Param!");
                return ;
            }

            //Get Params
            int value = mScanner.getParams(id);

            edt_value.setText(value + "");
        }catch(Exception ex){
            showMsg("Error"+ex.getMessage());
        }
    }

    void showMsg(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            int ret1 = ScannerKey.open();
            if(ret1 > -1){
                while(true){
                    int ret = ScannerKey.getKeyEvent();
                    if(ret > -1){
                        switch (ret) {
                            case ScannerKey.KEY_DOWN:
                                mScanner.startScan();
                                break;
                            case ScannerKey.KEY_UP:
                                mScanner.stopScan();
                                break;
                        }
                    }
                }
            }
        }
    };

    @Override
    public void onDecodeComplete(DecodeInfo info) {
        mEditText.append("barcode:  " + info.barcode + "\ntype:  " + info.codetype + "\n");
    }
}
