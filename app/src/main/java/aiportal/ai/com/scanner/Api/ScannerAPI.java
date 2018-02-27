package aiportal.ai.com.scanner.Api;


import aiportal.ai.com.scanner.OkHttpBaseAPI;

public class ScannerAPI extends OkHttpBaseAPI {
    private final String IP = "http://118.178.224.240:8008/";
    private final String WorkSpace = "/Service%20References/";

    public String login(String userName, String passWord) {
        //http://222.247.85.139:6003/WebSerivce/Service%20References/LoginService.asmx/Login?username=administrator&password=raycomsoft@20g

        String result = "";

        String url;
        try {
            url = IP + WorkSpace + "LoginService.asmx/Login?" + "username=" + userName + "&password=" + passWord;
            result = httpGetTask(url, "login");

        } catch (Exception e) {

        }

        return result;
    }

    /**
     * http://222.247.85.139:6003/WebSerivce/Service%20References/GetSampleService.asmx?op=GetOrderByBarcode
     *
     * @param barcode
     * @return
     */
    public String getOrderByBarcode(String barcode) {
        String result = "";
        String url;
        try {
            url = IP + WorkSpace + "GetSampleService.asmx/GetOrderByBarcode?" + "Barcode=" + barcode;
            result = httpGetTask(url, "login");
        } catch (Exception e) {

        }

        return result;
    }

    /**
     * http://222.247.85.139:6003/WebSerivce/Service%20References/GetSampleService.asmx/GetProductAppByTireNo?TireNo=190493
     *
     * @param tireNo
     * @return
     */
    public String GetProductAppByTireNo(String tireNo) {
        String result = "";
        String url;
        try {
            url = IP + WorkSpace + "GetSampleService.asmx/GetProductAppByTireNo?" + "TireNo=" + tireNo;
            result = httpGetTask(url, "login");
        } catch (Exception e) {

        }

        return result;
    }

    public String refuseOrder(String orderNo){
       // http://223.159.85.169:6003/WebSerivce/Service%20References/GetSampleStatusService.asmx/RefuseSample?OrderNo=201712251000001
        String result = "";
        String url;
        try {
            url = IP + WorkSpace + "GetSampleStatusService.asmx/RefuseSample?OrderNo=" + orderNo;
            result = httpGetTask(url, "login");
        } catch (Exception e) {

        }

        return result;
    }

    public String receivedOrder(String tireNo) {
        //http://223.159.85.169:6003/WebSerivce/Service%20References/GetSampleStatusService.asmx/ReceivedSample?OrderNo=190493
        String result = "";
        String url;
        try {
            url = IP + WorkSpace + "GetSampleStatusService.asmx/ReceivedSample?OrderNo=" + tireNo;
            result = httpGetTask(url, "login");
        } catch (Exception e) {

        }

        return result;
    }
}
