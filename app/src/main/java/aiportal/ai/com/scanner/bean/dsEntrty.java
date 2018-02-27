package aiportal.ai.com.scanner.bean;

import java.io.Serializable;

/**
 *
 */

public class dsEntrty implements Serializable {
    private String CreateBy; //申请人
    private String CreateDate;//申请时间
    private String Dep;//申请部门
    private String Barcode;//样品条码
    private String sNo;//物料代码
    private String TireNo;//规格型号
    private String MaterialDesc;//样品名称
    private String Specification;
    private String IsSculptureTire;//样品批次号
    private String Brand;//样品外观状态
    private String LoadIndex;//实验目的
    private String SpeedLevel;//要求完成时间
    private String Pattern;//委托检测地点
    private String Level;//委托检测地点
    private String FetalPressure;//委托检测地点
    private String CompleteTime;//委托检测地点
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

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getTireNo() {
        return TireNo;
    }

    public void setTireNo(String tireNo) {
        TireNo = tireNo;
    }

    public String getMaterialDesc() {
        return MaterialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        MaterialDesc = materialDesc;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getIsSculptureTire() {
        return IsSculptureTire;
    }

    public void setIsSculptureTire(String isSculptureTire) {
        IsSculptureTire = isSculptureTire;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getLoadIndex() {
        return LoadIndex;
    }

    public void setLoadIndex(String loadIndex) {
        LoadIndex = loadIndex;
    }

    public String getSpeedLevel() {
        return SpeedLevel;
    }

    public void setSpeedLevel(String speedLevel) {
        SpeedLevel = speedLevel;
    }

    public String getPattern() {
        return Pattern;
    }

    public void setPattern(String pattern) {
        Pattern = pattern;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getFetalPressure() {
        return FetalPressure;
    }

    public void setFetalPressure(String fetalPressure) {
        FetalPressure = fetalPressure;
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
