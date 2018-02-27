package aiportal.ai.com.scanner.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baggio on 2017/12/25.
 */

public class BaseResult<V extends Object>  implements Serializable{
    /**
     * {"status":1,"total":0,"msg":"成功","data":null}
     */
    private int status = 0;
    private int total = 0;
    private String msg = "";
    private List<V> data ;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<V> getData() {
        return data;
    }

    public void setData(List<V> data) {
        this.data = data;
    }
}
