package aiportal.ai.com.scanner.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import aiportal.ai.com.scanner.BaseActivity;
import aiportal.ai.com.scanner.Contanst;
import aiportal.ai.com.scanner.R;
import aiportal.ai.com.scanner.bean.BaseResult;
import aiportal.ai.com.scanner.bean.Order;
import aiportal.ai.com.scanner.bean.OrderResult;
import aiportal.ai.com.scanner.interfac.TaskCallback;
import aiportal.ai.com.scanner.tasks.ReceiveTask;
import aiportal.ai.com.scanner.tasks.RefuseOrderTask;
import aiportal.ai.com.scanner.tasks.ScannerTask;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class OrderActivity extends BaseActivity {


    @InjectView(R.id.txt_title)
    TextView txtTitle;
    @InjectView(R.id.txt_username)
    TextView txtUsername;
    @InjectView(R.id.btn_loginout)
    Button btnLoginout;
    @InjectView(R.id.btn_urgent)
    Button btnUrgent;
    @InjectView(R.id.txt_CreateByValue)
    TextView txtCreateByValue;
    @InjectView(R.id.txt_CreateDateValue)
    TextView txtCreateDateValue;
    @InjectView(R.id.txt_DepValue)
    TextView txtDepValue;
    @InjectView(R.id.txt_BarcodeValue)
    TextView txtBarcodeValue;
    @InjectView(R.id.txt_MaterialNoValue)
    TextView txtMaterialNoValue;
    @InjectView(R.id.txt_SpecificationValue)
    TextView txtSpecificationValue;
    @InjectView(R.id.txt_MaterialDescValue)
    TextView txtMaterialDescValue;
    @InjectView(R.id.txt_SerialNoValue)
    TextView txtSerialNoValue;
    @InjectView(R.id.txt_ExteriorStatusValue)
    TextView txtExteriorStatusValue;
    @InjectView(R.id.txt_ExPurposeValue)
    TextView txtExPurposeValue;
    @InjectView(R.id.txt_CompleteTimeValue)
    TextView txtCompleteTimeValue;
    @InjectView(R.id.txt_TestLabValue)
    TextView txtTestLabValue;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    @InjectView(R.id.btn_reject)
    Button btnReject;
    @InjectView(R.id.relty_bottom)
    RelativeLayout reltyBottom;
    private Context mContext;
    private String orderNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mContext = this;
        ButterKnife.inject(this);
        initData();
        btnLoginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ReceiveTask(mContext, orderNo, new TaskCallback() {
                    @Override
                    public void callback(Object result) {
                        String resultStr = result.toString();
                        Gson gson = new Gson();
                        BaseResult baseResult = gson.fromJson(resultStr, BaseResult.class);//对于javabean直接给出class实例
                        if(baseResult.getStatus() == 1){

                        finish();
                        }
                        Toast.makeText(mContext,"收样确定" + baseResult.getMsg() ,Toast.LENGTH_SHORT).show();

                    }
                }).execute();
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RefuseOrderTask(mContext, orderNo, new TaskCallback() {
                    @Override
                    public void callback(Object result) {
                        String resultStr = result.toString();
                        Gson gson = new Gson();
                        BaseResult baseResult = gson.fromJson(resultStr, BaseResult.class);//对于javabean直接给出class实例
                        if(baseResult.getStatus() == 1){

                            finish();
                        }
                        Toast.makeText(mContext,"收样驳回" + baseResult.getMsg() ,Toast.LENGTH_SHORT).show();


                    }
                }).execute();
            }
        });
    }

    private void initData(){
        Intent intent = getIntent();
        if(intent != null){
            Order order= (Order) intent.getSerializableExtra("Order");
            txtCreateByValue.setText(order.getCreateBy());
            txtCreateDateValue.setText(order.getCreateDate());
            txtDepValue.setText(order.getDep());
            txtBarcodeValue.setText(order.getBarcode());
            txtMaterialNoValue.setText(order.getMaterialNo());
            txtSpecificationValue.setText(order.getSpecification());
            txtMaterialDescValue.setText(order.getMaterialDesc());
            txtSerialNoValue.setText(order.getSerialNo());
            txtExteriorStatusValue.setText(order.getExteriorStatus());
            txtExPurposeValue.setText(order.getExPurpose());
            txtCompleteTimeValue.setText(order.getCompleteTime());
            txtTestLabValue.setText(order.getTestLab());
            orderNo = order.getOrderNo();
            txtUsername.setText(Contanst.userName);
        }

    }
}
