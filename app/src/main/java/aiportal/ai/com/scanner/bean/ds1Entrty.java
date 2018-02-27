package aiportal.ai.com.scanner.bean;

import java.io.Serializable;

/**
 * Created by baggio on 2017/12/27.
 */

public class ds1Entrty implements Serializable {
    private Integer RowNo; //申请人
    private String DESCRIPTION;//申请时间

    public Integer getRowNo() {
        return RowNo;
    }

    public void setRowNo(Integer rowNo) {
        RowNo = rowNo;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
}
