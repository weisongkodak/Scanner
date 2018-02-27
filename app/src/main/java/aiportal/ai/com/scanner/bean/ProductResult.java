package aiportal.ai.com.scanner.bean;

import java.io.Serializable;

/**
 * Created by baggio on 2017/12/27.
 */

public class ProductResult  implements Serializable {
    private Product data;
    private int status = 0;
    private int total = 0;
    private String msg = "";
    public Product getData() {
        return data;
    }
    public void setData(Product data) {
        this.data = data;
    }

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
}
