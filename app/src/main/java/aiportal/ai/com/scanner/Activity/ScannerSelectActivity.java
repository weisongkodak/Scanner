package aiportal.ai.com.scanner.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.seuic.scanner.DecodeInfo;
import com.seuic.scanner.DecodeInfoCallBack;
import com.seuic.scanner.Scanner;
import com.seuic.scanner.ScannerFactory;
import com.seuic.scanner.ScannerKey;

import aiportal.ai.com.scanner.BaseActivity;
import aiportal.ai.com.scanner.Contanst;
import aiportal.ai.com.scanner.R;
import aiportal.ai.com.scanner.bean.Order;
import aiportal.ai.com.scanner.bean.OrderResult;
import aiportal.ai.com.scanner.bean.ProductResult;
import aiportal.ai.com.scanner.interfac.TaskCallback;
import aiportal.ai.com.scanner.tasks.GetProductAppTask;
import aiportal.ai.com.scanner.tasks.ScannerTask;
import butterknife.ButterKnife;

public class ScannerSelectActivity extends BaseActivity  implements DecodeInfoCallBack {
    private Context mContext;
    Button btnCannerOne;
    Button btnCannerTwo,btn_loginout;
    TextView txt_name;
    Scanner mScanner ;
    ButtonListener buttonListener;
    SparseIntArray mapParams;
    private final int BUTTON1_DOWN = 10;
    private final int BUTTON1_UP = 11;
    private final int BUTTON2_DOWN = 20;
    private final int BUTTON2_UP = 21;
    private int buttonEventCode = -1;
    private boolean pageOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_scanner_select);
        ButterKnife.inject(this);
        getViews();
        setEvents();
        init();
    }

    void init(){

        //Get Scanner
        mScanner = ScannerFactory.getScanner(this);

        mapParams = mScanner.getAllParams();
        new Thread(runnable).start();

    }

    private void getViews(){
        btnCannerOne = (Button) findViewById(R.id.btn_cannerOne);
        btnCannerTwo = (Button) findViewById(R.id.btn_cannerTwo);
        btn_loginout = (Button) findViewById(R.id.btn_loginout);
        txt_name = (TextView) findViewById(R.id.txt_username);
        txt_name.setText(Contanst.userName);
    }

    private void setEvents() {
        buttonListener = new ButtonListener();
        btnCannerOne.setOnTouchListener(buttonListener);
        btnCannerTwo.setOnTouchListener(buttonListener);
        btn_loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
    }

    @Override
    protected void onResume() {
        pageOn = true;
        //Set Callback
        mScanner.setDecodeInfoCallBack(this);
        mScanner.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        pageOn = false;
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

    Runnable runnable = new Runnable() {

        @Override
        public void run() {

                while(true){
                    if(buttonEventCode > -1){
                        switch (buttonEventCode) {
                            case BUTTON1_DOWN:
                                mScanner.startScan();
                                break;
                            case BUTTON1_UP:
                                mScanner.stopScan();
                                break;

                            case BUTTON2_DOWN:
                                mScanner.startScan();
                                break;
                            case BUTTON2_UP:
                                mScanner.stopScan();
                                break;
                        }
                    }
                }

        }
    };

    @Override
    public void onDecodeComplete(DecodeInfo info) {
        String code = info.barcode;


        switch (buttonEventCode) {


            case BUTTON1_UP:
                //code = "201712251000001";
                goToOrder(code);
                break;

            case BUTTON2_UP:
                //code = "346233";
                getToTire(code);
                break;
        }

    }

    private void goToOrder(String code) {
        new ScannerTask(mContext, code, new TaskCallback() {
            @Override
            public void callback(Object result) {
                String resultStr = result.toString();
                Gson gson = new Gson();
                OrderResult oderResult = gson.fromJson(resultStr, OrderResult.class);//对于javabean直接给出class实例
                if(oderResult.getStatus() == 1){
                    Order order = oderResult.getData().get(0);
                    Intent intent = new Intent(ScannerSelectActivity.this,OrderActivity.class);
                    Bundle  bundle = new Bundle ();
                    bundle.putSerializable("Order",order);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext,"没有找到该数据",Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        }).execute();
    }


    private void getToTire(String code){
        code = "201802260054-1";
        //code = "71432407973";
        new GetProductAppTask(mContext, code, new TaskCallback() {
            @Override
            public void callback(Object result) {
                String resultStr = result.toString();
                Gson gson = new Gson();
                ProductResult productResult = gson.fromJson(resultStr,ProductResult.class);
                if(productResult.getStatus() ==1){
                    Intent intent = new Intent(ScannerSelectActivity.this,ProductActivity.class);
                    Bundle  bundle = new Bundle ();
                    bundle.putSerializable("Product",productResult.getData());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext,"没有找到该数据",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).execute();
    }
    class ButtonListener implements View.OnClickListener, View.OnTouchListener {


        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(v.getId() == R.id.btn_cannerOne){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonEventCode = BUTTON1_UP;
                    btnCannerOne.setBackgroundResource(R.drawable.round_corner_blue_solid);
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonEventCode = BUTTON1_DOWN;
                    btnCannerOne.setBackgroundResource(R.drawable.round_corner_yellow_solid);
                }
            }else if(v.getId() == R.id.btn_cannerTwo){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonEventCode = BUTTON2_UP;
                    btnCannerTwo.setBackgroundResource(R.drawable.round_corner_blue_solid);

                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonEventCode = BUTTON2_DOWN;
                    btnCannerTwo.setBackgroundResource(R.drawable.round_corner_yellow_solid);

                }
            }
            return false;
        }
    }
}

