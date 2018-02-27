package aiportal.ai.com.scanner.bean;

import java.io.Serializable;

/**
 * Created by baggio on 2017/12/27.
 */

public class Order  implements Serializable {
    private String CreateBy; //申请人
    private String CreateDate;//申请时间
    private String Dep;//申请部门
    private String Barcode;//样品条码
    private String MaterialNo;//物料代码
    private String Specification;//规格型号
    private String MaterialDesc;//样品名称
    private String SerialNo;//样品批次号
    private String ExteriorStatus;//样品外观状态
    private String ExPurpose;//实验目的
    private String CompleteTime;//要求完成时间
    private String TestLab;//委托检测地点
    private String OrderNo;

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getDep() {
        return Dep;
    }

    public void setDep(String dep) {
        Dep = dep;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getMaterialNo() {
        return MaterialNo;
    }

    public void setMaterialNo(String materialNo) {
        MaterialNo = materialNo;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getMaterialDesc() {
        return MaterialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        MaterialDesc = materialDesc;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String serialNo) {
        SerialNo = serialNo;
    }

    public String getExteriorStatus() {
        return ExteriorStatus;
    }

    public void setExteriorStatus(String exteriorStatus) {
        ExteriorStatus = exteriorStatus;
    }

    public String getExPurpose() {
        return ExPurpose;
    }

    public void setExPurpose(String exPurpose) {
        ExPurpose = exPurpose;
    }

    public String getCompleteTime() {
        return CompleteTime;
    }

    public void setCompleteTime(String completeTime) {
        CompleteTime = completeTime;
    }

    public String getTestLab() {
        return TestLab;
    }

    public void setTestLab(String testLab) {
        TestLab = testLab;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }
}
