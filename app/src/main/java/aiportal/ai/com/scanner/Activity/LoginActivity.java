package aiportal.ai.com.scanner.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

import aiportal.ai.com.scanner.BaseActivity;
import aiportal.ai.com.scanner.Contanst;
import aiportal.ai.com.scanner.R;
import aiportal.ai.com.scanner.bean.LoginResult;
import aiportal.ai.com.scanner.bean.Order;
import aiportal.ai.com.scanner.bean.OrderResult;
import aiportal.ai.com.scanner.bean.ProductResult;
import aiportal.ai.com.scanner.interfac.TaskCallback;
import aiportal.ai.com.scanner.tasks.GetProductAppTask;
import aiportal.ai.com.scanner.tasks.LoginTask;
import aiportal.ai.com.scanner.tasks.ScannerTask;

public class LoginActivity extends BaseActivity {
    private Context mContext;
    private Button login;
    private EditText editTextUserName, editTextPassword;
    private final String USER_NAME = "userName";
    private final String PASSWORD = "password";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        setContentView(R.layout.activity_login);
        editTextUserName = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        String userName = preferences.getString(USER_NAME,"");
        String password = preferences.getString(PASSWORD,"");
        userName = "sailunjinyu\\limsadmin";
        password = "lims@123";
        editTextUserName.setText(userName);
        editTextPassword.setText(password);
        Contanst.userName = userName;
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginTask.loginRequest loginRequest = new LoginTask.loginRequest();
                loginRequest.userName = editTextUserName.getText().toString();
                loginRequest.passWord = editTextPassword.getText().toString();
                login(loginRequest);
            }
        });

    }

    public void login(final LoginTask.loginRequest loginRequest) {
/**
 * <?xml version="1.0" encoding="utf-8"?>
 <string xmlns="http://tempuri.org/">{"status":1,"total":0,"msg":"成功","data":null}</string>
 */
        new LoginTask(mContext, loginRequest, new TaskCallback() {
            @Override
            public void callback(Object result) {
                String resultStr = result.toString();
                Gson gson = new Gson();
                LoginResult loginResult = gson.fromJson(resultStr, LoginResult.class);//对于javabean直接给出class实例
                if(loginResult.getStatus() == 1){
                   //startActivity(new Intent(LoginActivity.this,ScannerSelectActivity.class));
                    goToOrder("201802260054-1");
                    //getToTire("71432407973");
                   // LoginActivity.this.finish();
                }else {
                    Toast.makeText(mContext,loginResult.getMsg(),Toast.LENGTH_SHORT).show();
                }


            }
        }).execute();
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
                    Intent intent = new Intent(LoginActivity.this,OrderActivity.class);
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
                    Intent intent = new Intent(LoginActivity.this,ProductActivity.class);
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
}
