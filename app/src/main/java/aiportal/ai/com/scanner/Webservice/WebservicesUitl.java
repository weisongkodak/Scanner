package aiportal.ai.com.scanner.Webservice;

import com.liushuai.network.Callback;
import com.liushuai.network.SoapClient;
import com.liushuai.network.SoapRequest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by baggio on 2017/12/24.
 */
//http://100.75.218.88/WebSerivce/Service%20References/LoginService.asmx?op=Login
//设置是否是调试模式
public class WebservicesUitl {
    //没有特殊情况尽量保持一个SoapClient
    final static String IP = "222.247.84.112:6003";

    final static String SERVICE_NS = "http://" + IP + "/WebSerivce/";
    final static String SERVICE_URL = "http://" + IP + "/WebSerivce/Service%20References/LoginService.asmx?op=Login";
    private static SoapClient mSoapClient = new SoapClient();

    /*如果调用的是.net平台的WebService，请务必在构造SoapRequest的时候设置setDotNet(true)*/
    public static void getSupportCity(String cityName, Callback callback) {
        SoapRequest request = new SoapRequest.Builder().endPoint("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx")
                .methodName("getSupportCity")
                .soapAction("http://WebXml.com.cn/" + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace("LIMS.Web")
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }

    /*如果调用的是.net平台的WebService，请务必在构造SoapRequest的时候设置setDotNet(true)*/
    public static SoapEnvelope getSupportCity(String cityName) {
        SoapRequest request = new SoapRequest.Builder().endPoint("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx")
                .methodName("getSupportCity")
                .soapAction("http://WebXml.com.cn/" + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace("http://WebXml.com.cn/")
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }

    public static void login(String userName, String password) {
        //调用的方法
        String methodName = "Login";
        //创建httpTransportSE传输对象
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
        ht.debug = true;
        //使用soap1.1协议创建Envelop对象
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //实例化SoapObject对象
        SoapObject request = new SoapObject(SERVICE_NS, methodName);
        /**
         * 设置参数，参数名不一定需要跟调用的服务器端的参数名相同，只需要对应的顺序相同即可
         * */
        request.addProperty("name", "administrator");
        request.addProperty("password", "raycomsoft@20g");
        //将SoapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息

        envelope.bodyOut = request;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;

        try {
            //调用webService
            ht.call(SERVICE_NS + methodName, envelope);
            //txt1.setText("看看"+envelope.getResponse());
            if (envelope.getResponse() != null) {

                SoapObject result = (SoapObject) envelope.bodyIn;
                String name = result.getProperty(0).toString();

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
