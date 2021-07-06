package org.openmrs.module.hivscreening.forms;

import org.openmrs.module.hivscreening.HivScreening;
import org.openmrs.module.hivscreening.enumerations.*;
import org.openmrs.module.hivscreening.utils.UtilFunctions;

import java.util.Date;

public class ScreeningForm {
    private Integer id;
    private Integer registerInfoId;
    private Date screeningDate;
    private String screeningCode;
    private String sampling;
    private String profession;
    private String gender;
    private Integer age;
    private String residence;
    private MaritalStatusType maritalStatus;
    private String otherMaritalStatus;
    private PopulationType populationType;
    private String otherPopulationType;
    private TestReasonType screeningReason;
    private String otherScreeningReason;
    private boolean invalidatedTest1 = false;
    private TestReactionType test1Reaction;
    private boolean invalidatedTest2 = false;
    private TestReactionType test2Reaction;
    private boolean invalidatedTest3 = false;
    private TestReactionType test3Reaction;
    private FinalResultType finalResult;
    private Date resultAnnouncingDate;
    private Boolean retesting;
    private String comment;

    public ScreeningForm() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getRegisterInfoId() {
        return registerInfoId;
    }

    public void setRegisterInfoId(Integer registerInfoId) {
        this.registerInfoId = registerInfoId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public String getScreeningCode() {
        return screeningCode;
    }

    public void setScreeningCode(String screeningCode) {
        this.screeningCode = screeningCode;
    }

    public String getSampling() {
        return sampling;
    }

    public void setSampling(String sampling) {
        this.sampling = sampling;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public MaritalStatusType getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatusType maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOtherMaritalStatus() {
        return otherMaritalStatus;
    }

    public void setOtherMaritalStatus(String otherMaritalStatus) {
        this.otherMaritalStatus = otherMaritalStatus;
    }

    public PopulationType getPopulationType() {
        return populationType;
    }

    public void setPopulationType(PopulationType populationType) {
        this.populationType = populationType;
    }

    public String getOtherPopulationType() {
        return otherPopulationType;
    }

    public void setOtherPopulationType(String otherPopulationType) {
        this.otherPopulationType = otherPopulationType;
    }

    public TestReasonType getScreeningReason() {
        return screeningReason;
    }

    public void setScreeningReason(TestReasonType screeningReason) {
        this.screeningReason = screeningReason;
    }

    public String getOtherScreeningReason() {
        return otherScreeningReason;
    }

    public void setOtherScreeningReason(String otherScreeningReason) {
        this.otherScreeningReason = otherScreeningReason;
    }

    public boolean isInvalidatedTest1() {
        return invalidatedTest1;
    }

    public void setInvalidatedTest1(boolean invalidatedTest1) {
        this.invalidatedTest1 = invalidatedTest1;
    }

    public TestReactionType getTest1Reaction() {
        return test1Reaction;
    }

    public void setTest1Reaction(TestReactionType test1Reaction) {
        this.test1Reaction = test1Reaction;
    }

    public boolean isInvalidatedTest2() {
        return invalidatedTest2;
    }

    public void setInvalidatedTest2(boolean invalidatedTest2) {
        this.invalidatedTest2 = invalidatedTest2;
    }

    public TestReactionType getTest2Reaction() {
        return test2Reaction;
    }

    public void setTest2Reaction(TestReactionType test2Reaction) {
        this.test2Reaction = test2Reaction;
    }

    public boolean isInvalidatedTest3() {
        return invalidatedTest3;
    }

    public void setInvalidatedTest3(boolean invalidatedTest3) {
        this.invalidatedTest3 = invalidatedTest3;
    }

    public TestReactionType getTest3Reaction() {
        return test3Reaction;
    }

    public void setTest3Reaction(TestReactionType test3Reaction) {
        this.test3Reaction = test3Reaction;
    }

    public FinalResultType getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(FinalResultType finalResult) {
        this.finalResult = finalResult;
    }

    public Date getResultAnnouncingDate() {
        return resultAnnouncingDate;
    }

    public void setResultAnnouncingDate(Date resultAnnouncingDate) {
        this.resultAnnouncingDate = resultAnnouncingDate;
    }

    public Boolean getRetesting() {
        return retesting;
    }

    public void setRetesting(Boolean retesting) {
        this.retesting = retesting;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setHivScreening(HivScreening hivScreening) {
        setId(hivScreening.getId());
        setScreeningDate(hivScreening.getScreeningDate());
        setScreeningCode(hivScreening.getScreeningCode());
        setSampling(hivScreening.getSampling());
        setProfession(hivScreening.getProfession());
        setAge(hivScreening.getAge());
        setGender(hivScreening.getGender());
        setResidence(hivScreening.getResidence());
        setMaritalStatus(hivScreening.getMaritalStatus());
        setOtherMaritalStatus(hivScreening.getOtherMaritalStatus());
        setPopulationType(hivScreening.getPopulationType());
        setOtherPopulationType(hivScreening.getOtherPopulationType());
        setScreeningReason(hivScreening.getScreeningReason());
        setOtherScreeningReason(hivScreening.getOtherScreeningReason());
        setOtherScreeningReason(hivScreening.getOtherScreeningReason());
        setInvalidatedTest1(hivScreening.isInvalidatedTest1());
        setTest1Reaction(hivScreening.getTest1Reaction());
        setInvalidatedTest2(hivScreening.isInvalidatedTest2());
        setTest2Reaction(hivScreening.getTest2Reaction());
        setInvalidatedTest3(hivScreening.isInvalidatedTest3());
        setTest3Reaction(hivScreening.getTest3Reaction());
        setFinalResult(hivScreening.getFinalResult());
        setResultAnnouncingDate(hivScreening.getResultAnnouncingDate());
        setRetesting(hivScreening.getRetesting());
        setComment(hivScreening.getComment());
        setRegisterInfoId(hivScreening.getRegisterInfo().getId());
    }

    public HivScreening getHivScreening() {
        HivScreening hivScreening = new HivScreening();
        if (getId() != null) {
            hivScreening = UtilFunctions.service().getHivScreeningById(getId());
        } else {
            hivScreening.setLocation(UtilFunctions.getUserLocation());
            hivScreening.setRegisterInfo(UtilFunctions.service().getScreeningRegisterInfoById(getRegisterInfoId()));
        }
        hivScreening.setScreeningDate(getScreeningDate());
        hivScreening.setProfession(getProfession());
        hivScreening.setScreeningCode(getScreeningCode());
        hivScreening.setSampling(getSampling());
        hivScreening.setAge(getAge());
        hivScreening.setGender(getGender());
        hivScreening.setResidence(getResidence());
        hivScreening.setMaritalStatus(getMaritalStatus());
        hivScreening.setOtherMaritalStatus(getOtherMaritalStatus());
        hivScreening.setPopulationType(getPopulationType());
        hivScreening.setOtherPopulationType(getOtherPopulationType());
        hivScreening.setScreeningReason(getScreeningReason());
        hivScreening.setOtherScreeningReason(getOtherScreeningReason());
        hivScreening.setInvalidatedTest1(isInvalidatedTest1());
        hivScreening.setTest1Reaction(getTest1Reaction());
        hivScreening.setInvalidatedTest2(isInvalidatedTest2());
        hivScreening.setTest2Reaction(getTest2Reaction());
        hivScreening.setInvalidatedTest3(isInvalidatedTest3());
        hivScreening.setTest3Reaction(getTest3Reaction());
        hivScreening.setFinalResult(getFinalResult());
        hivScreening.setResultAnnouncingDate(getResultAnnouncingDate());
        hivScreening.setRetesting(getRetesting());
        hivScreening.setComment(getComment());
//        if (getComment() != null && !getComment().isEmpty()) {
//        }
        hivScreening.setRegisterInfo(UtilFunctions.service().getScreeningRegisterInfoById(getRegisterInfoId()));
        return hivScreening;
    }
}
