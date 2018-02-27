package aiportal.ai.com.scanner.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baggio on 2017/12/27.
 */

public class Product implements Serializable {
    private List<ds1Entrty> ds1;
    private List<dsEntrty> ds;

    public List<ds1Entrty> getDs1() {
        return ds1;
    }

    public void setDs1(List<ds1Entrty> ds1) {
        this.ds1 = ds1;
    }

    public List<dsEntrty> getDs() {
        return ds;
    }

    public void setDs(List<dsEntrty> ds) {
        this.ds = ds;
    }
}
