package aiportal.ai.com.scanner.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import aiportal.ai.com.scanner.Adapter.TestAdapter;
import aiportal.ai.com.scanner.BaseActivity;
import aiportal.ai.com.scanner.Contanst;
import aiportal.ai.com.scanner.R;
import aiportal.ai.com.scanner.bean.BaseResult;
import aiportal.ai.com.scanner.bean.Product;
import aiportal.ai.com.scanner.bean.ds1Entrty;
import aiportal.ai.com.scanner.bean.dsEntrty;
import aiportal.ai.com.scanner.interfac.TaskCallback;
import aiportal.ai.com.scanner.tasks.ReceiveTask;
import aiportal.ai.com.scanner.tasks.RefuseOrderTask;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProductActivity extends BaseActivity {

    TextView txtProductCreateByValue;
    TextView txtProductCreateDateValue;
    TextView txtProductDepValue;
    TextView txtProductBarcodeValue;

    TextView txtProductSNoValue;

    TextView txtProductTireNoValue;

    TextView txtProductMaterialDescValue;

    TextView txtProductSpecificationValue;

    TextView txtProductIsSculptureTireValue;

    TextView txtProductBrandValue;

    TextView txtProductLoadIndexValue;

    TextView txtProductSpeedLevelValue;

    TextView txtProductPatternValue;

    TextView txtProductLevelValue;

    TextView txtProductFetalPressureValue;

    //TextView txtProductCompleteTimeValue;

    TextView txtProductTestLabValue;

    TextView txt_name;
    Button btn_loginout;


    @InjectView(R.id.list_product_test)
    ListView listProductTest;
    dsEntrty dsEntrty = null;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    @InjectView(R.id.btn_reject)
    Button btnReject;
    private Context mContext;
    private List<ds1Entrty> ds1EntrtyList;
    private String tirNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.inject(this);
        mContext = this;
        initData();
        initView();
        btn_loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ReceiveTask(mContext, tirNo, new TaskCallback() {
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
                new RefuseOrderTask(mContext, tirNo, new TaskCallback() {
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

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);

        RelativeLayout headerView = (RelativeLayout) inflater.inflate(R.layout.head_product, null);
        txtProductCreateByValue = (TextView) headerView.findViewById(R.id.txt_product_CreateByValue);
        txtProductCreateDateValue = (TextView) headerView.findViewById(R.id.txt_product_CreateDateValue);
        txtProductDepValue = (TextView) headerView.findViewById(R.id.txt_product_DepValue);
        txtProductBarcodeValue = (TextView) headerView.findViewById(R.id.txt_product_BarcodeValue);

        txtProductSNoValue = (TextView) headerView.findViewById(R.id.txt_product_sNoValue);

        txtProductTireNoValue = (TextView) headerView.findViewById(R.id.txt_product_TireNoValue);

        txtProductMaterialDescValue = (TextView) headerView.findViewById(R.id.txt_product_MaterialDescValue);

        txtProductSpecificationValue = (TextView) headerView.findViewById(R.id.txt_product_SpecificationValue);

        txtProductIsSculptureTireValue = (TextView) headerView.findViewById(R.id.txt_product_IsSculptureTireValue);

        txtProductBrandValue = (TextView) headerView.findViewById(R.id.txt_product_BrandValue);

        txtProductLoadIndexValue = (TextView) headerView.findViewById(R.id.txt_product_LoadIndexValue);

        txtProductSpeedLevelValue = (TextView) headerView.findViewById(R.id.txt_product_SpeedLevelValue);

        txtProductPatternValue = (TextView) headerView.findViewById(R.id.txt_product_PatternValue);

        txtProductLevelValue = (TextView) headerView.findViewById(R.id.txt_product_LevelValue);

        txtProductFetalPressureValue = (TextView) headerView.findViewById(R.id.txt_product_FetalPressureValue);

       // txtProductCompleteTimeValue = (TextView) headerView.findViewById(R.id.txt_product_CompleteTimeValue);

        txtProductTestLabValue = (TextView) headerView.findViewById(R.id.txt_product_TestLabValue);
        txt_name = (TextView) headerView.findViewById(R.id.txt_product_username);
        btn_loginout = (Button) headerView.findViewById(R.id.btn_loginout);
        if (dsEntrty!=null){
            txtProductCreateByValue.setText(dsEntrty.getCreateBy());
            txtProductCreateDateValue.setText(dsEntrty.getCreateDate());
            txtProductDepValue.setText(dsEntrty.getDep());
            txtProductBarcodeValue.setText(dsEntrty.getBarcode());
            txtProductSNoValue.setText(dsEntrty.getsNo());
            txtProductTireNoValue.setText(dsEntrty.getTireNo());
            txtProductMaterialDescValue.setText(dsEntrty.getMaterialDesc());
            txtProductSpecificationValue.setText(dsEntrty.getSpecification());
            txtProductIsSculptureTireValue.setText(dsEntrty.getIsSculptureTire());
            txtProductBrandValue.setText(dsEntrty.getBrand());
            txtProductLoadIndexValue.setText(dsEntrty.getLoadIndex());
            txtProductSpeedLevelValue.setText(dsEntrty.getSpeedLevel());
            txtProductPatternValue.setText(dsEntrty.getPattern());
            txtProductLevelValue.setText(dsEntrty.getLevel());
            txtProductFetalPressureValue.setText(dsEntrty.getFetalPressure());
            // txtProductCompleteTimeValue.setText(dsEntrty.getCompleteTime());
            txtProductTestLabValue.setText(dsEntrty.getTestLab());
            txt_name.setText(Contanst.userName);
            listProductTest.addHeaderView(headerView);
            TestAdapter testAdapter = new TestAdapter(mContext, ds1EntrtyList);
            listProductTest.setAdapter(testAdapter);
        }


    }


    private void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            Product product = (Product) intent.getSerializableExtra("Product");
            if (product != null){
                dsEntrty = product.getDs().get(0);
                ds1EntrtyList = product.getDs1();
                tirNo = dsEntrty.getOrderNo();
            }

        }

    }
}
