package org.openmrs.module.hivscreening.forms;

import org.openmrs.api.context.Context;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.TestingKit;
import org.openmrs.module.hivscreening.api.HivScreeningRegisterService;
import org.openmrs.module.hivscreening.utils.UtilFunctions;

import java.util.Date;

public class RegisterForm {
    private Integer id;
    private String screeningSiteType;
    private String screeningPost;
    private String registerCode;

    private Integer testingKit1Id;
    private String testingKit1Name;
    private String testingKit1BatchNumber;
    private Date testingKit1ExpiryDate;

    private Integer testingKit2Id;
    private String testingKit2Name;
    private String testingKit2BatchNumber;
    private Date testingKit2ExpiryDate;

    private Integer labTestingKitId;
    private String labTestingKitName;
    private String labTestingKitBatchNumber;
    private Date labTestingKitExpiryDate;

    public RegisterForm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScreeningSiteType() {
        return screeningSiteType;
    }

    public void setScreeningSiteType(String screeningSiteType) {
        this.screeningSiteType = screeningSiteType;
    }

    public String getScreeningPost() {
        return screeningPost;
    }

    public void setScreeningPost(String screeningPost) {
        this.screeningPost = screeningPost;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public Integer getTestingKit1Id() {
        return testingKit1Id;
    }

    public void setTestingKit1Id(Integer testingKit1Id) {
        this.testingKit1Id = testingKit1Id;
    }

    public String getTestingKit1Name() {
        return testingKit1Name;
    }

    public void setTestingKit1Name(String testingKit1Name) {
        this.testingKit1Name = testingKit1Name;
    }

    public String getTestingKit1BatchNumber() {
        return testingKit1BatchNumber;
    }

    public void setTestingKit1BatchNumber(String testingKit1BatchNumber) {
        this.testingKit1BatchNumber = testingKit1BatchNumber;
    }

    public Date getTestingKit1ExpiryDate() {
        return testingKit1ExpiryDate;
    }

    public void setTestingKit1ExpiryDate(Date testingKit1ExpiryDate) {
        this.testingKit1ExpiryDate = testingKit1ExpiryDate;
    }

    public Integer getTestingKit2Id() {
        return testingKit2Id;
    }

    public void setTestingKit2Id(Integer testingKit2Id) {
        this.testingKit2Id = testingKit2Id;
    }

    public String getTestingKit2Name() {
        return testingKit2Name;
    }

    public void setTestingKit2Name(String testingKit2Name) {
        this.testingKit2Name = testingKit2Name;
    }

    public String getTestingKit2BatchNumber() {
        return testingKit2BatchNumber;
    }

    public void setTestingKit2BatchNumber(String testingKit2BatchNumber) {
        this.testingKit2BatchNumber = testingKit2BatchNumber;
    }

    public Date getTestingKit2ExpiryDate() {
        return testingKit2ExpiryDate;
    }

    public void setTestingKit2ExpiryDate(Date testingKit2ExpiryDate) {
        this.testingKit2ExpiryDate = testingKit2ExpiryDate;
    }

    public Integer getLabTestingKitId() {
        return labTestingKitId;
    }

    public void setLabTestingKitId(Integer labTestingKitId) {
        this.labTestingKitId = labTestingKitId;
    }

    public String getLabTestingKitName() {
        return labTestingKitName;
    }

    public void setLabTestingKitName(String labTestingKitName) {
        this.labTestingKitName = labTestingKitName;
    }

    public String getLabTestingKitBatchNumber() {
        return labTestingKitBatchNumber;
    }

    public void setLabTestingKitBatchNumber(String labTestingKitBatchNumber) {
        this.labTestingKitBatchNumber = labTestingKitBatchNumber;
    }

    public Date getLabTestingKitExpiryDate() {
        return labTestingKitExpiryDate;
    }

    public void setLabTestingKitExpiryDate(Date labTestingKitExpiryDate) {
        this.labTestingKitExpiryDate = labTestingKitExpiryDate;
    }

    public void setTestingKit1(TestingKit testingKit1) {
        setTestingKit1Id(testingKit1.getId());
        setTestingKit1Name(testingKit1.getName());
        setTestingKit1ExpiryDate(testingKit1.getExpiryDate());
        setTestingKit1BatchNumber(testingKit1.getBatchNumber());
    }

    public void setTestingKit2(TestingKit testingKit2) {
        setTestingKit2Id(testingKit2.getId());
        setTestingKit2Name(testingKit2.getName());
        setTestingKit2ExpiryDate(testingKit2.getExpiryDate());
        setTestingKit2BatchNumber(testingKit2.getBatchNumber());
    }

    public void setLabTestingKit(TestingKit labTestingKit) {
        setLabTestingKitId(labTestingKit.getId());
        setLabTestingKitName(labTestingKit.getName());
        setLabTestingKitExpiryDate(labTestingKit.getExpiryDate());
        setLabTestingKitBatchNumber(labTestingKit.getBatchNumber());
    }

    public TestingKit getTestingKit1() {
        TestingKit testingKit = new TestingKit();
        if (getTestingKit1Id() != null) {
            testingKit = service().getTestingKitById(getTestingKit1Id());
        } else {
            TestingKit existingKit = UtilFunctions.service().getTestingKitByBatchNumber(getTestingKit1BatchNumber());
            if (existingKit == null) {
                testingKit.setBatchNumber(getTestingKit1BatchNumber());
                testingKit.setExpiryDate(getTestingKit1ExpiryDate());
                testingKit.setDateCreated(new Date());
                testingKit.setName(getTestingKit1Name());
                testingKit.setLocation(UtilFunctions.getUserLocation());
            }/* else {

            }*/
//            if (getTestingKit1BatchNumber() != null) {
//            } else {
//                testingKit.setBatchNumber(getTestingKit1BatchNumber());
//                testingKit.setExpiryDate(getTestingKit1ExpiryDate());
//                testingKit.setDateCreated(new Date());
//                testingKit.setName(getTestingKit1Name());
//                testingKit.setLocation(UtilFunctions.getUserLocation());
//            }
        }
        return testingKit;
    }

    public TestingKit getTestingKit2() {
        TestingKit testingKit = new TestingKit();
        if (getTestingKit2Id() != null) {
            testingKit = service().getTestingKitById(getTestingKit2Id());
        } else {
            TestingKit existingKit = UtilFunctions.service().getTestingKitByBatchNumber(getTestingKit2BatchNumber());
            if (existingKit == null) {
                testingKit.setBatchNumber(getTestingKit2BatchNumber());
                testingKit.setExpiryDate(getTestingKit2ExpiryDate());
                testingKit.setDateCreated(new Date());
                testingKit.setName(getTestingKit2Name());
                testingKit.setLocation(UtilFunctions.getUserLocation());
            } /*else {

            }
            if (getTestingKit2BatchNumber() != null) {
            } else {
                testingKit.setBatchNumber(getTestingKit2BatchNumber());
                testingKit.setExpiryDate(getTestingKit2ExpiryDate());
                testingKit.setDateCreated(new Date());
                testingKit.setName(getTestingKit2Name());
                testingKit.setLocation(UtilFunctions.getUserLocation());
            }*/
        }
        return testingKit;
    }

    public TestingKit getLabTestingKit() {
        TestingKit testingKit = new TestingKit();
        if (getLabTestingKitId() != null) {
            testingKit = service().getTestingKitById(getLabTestingKitId());
        } else {
            TestingKit existingKit = UtilFunctions.service().getTestingKitByBatchNumber(getLabTestingKitBatchNumber());
            if (existingKit == null) {
                testingKit.setBatchNumber(getLabTestingKitBatchNumber());
                testingKit.setExpiryDate(getLabTestingKitExpiryDate());
                testingKit.setDateCreated(new Date());
                testingKit.setName(getLabTestingKitName());
                testingKit.setLocation(UtilFunctions.getUserLocation());
            } /*else {

                }
                if (getLabTestingKitBatchNumber() != null) {
                } else {
                    testingKit.setBatchNumber(getLabTestingKitBatchNumber());
                    testingKit.setExpiryDate(getLabTestingKitExpiryDate());
                    testingKit.setDateCreated(new Date());
                    testingKit.setName(getLabTestingKitName());
                    testingKit.setLocation(UtilFunctions.getUserLocation());
                }*/
        }
        return testingKit;
    }

    public void setRegisterInfo(ScreeningRegisterInfo registerInfo) {
        setId(registerInfo.getId());
        setScreeningSiteType(registerInfo.getScreeningSiteType());
        setScreeningPost(registerInfo.getScreeningPost());
        setRegisterCode(registerInfo.getRegisterCode());
    }

    public ScreeningRegisterInfo getScreeningRegisterInfo() {
        ScreeningRegisterInfo registerInfo = new ScreeningRegisterInfo();
        if (getId() != null) {
            registerInfo = service().getScreeningRegisterInfoById(getId());
        } else {
            registerInfo.setLocation(UtilFunctions.getUserLocation());
        }
        registerInfo.setScreeningSiteType(getScreeningSiteType());
        registerInfo.setScreeningPost(getScreeningPost());
        registerInfo.setRegisterCode(getRegisterCode());
        return registerInfo;
    }

    public HivScreeningRegisterService service() {
        return Context.getService(HivScreeningRegisterService.class);
    }
}
