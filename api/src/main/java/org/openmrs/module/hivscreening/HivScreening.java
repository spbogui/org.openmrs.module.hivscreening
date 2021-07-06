/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hivscreening;

import java.util.Date;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.Location;
import org.openmrs.module.hivscreening.enumerations.*;

import javax.persistence.*;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
@Entity(name = "HivScreening")
@Table(name = "hiv_screening_hiv_screening")
public class HivScreening extends AbstractObject {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hiv_screening_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "register_info", nullable = false)
	private ScreeningRegisterInfo registerInfo;

	@Column(name = "screening_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date screeningDate;

	@Column(name = "screening_code", length = 20)
	private String screeningCode;

	@Column(name = "sampling", length = 50)
	private String sampling;

	@Column(name = "profession")
	private String profession;

	@Column(name = "gender", nullable = false, length = 2)
	private String gender;

	@Column(name = "age")
	private Integer age;

	@Column(name = "residence")
	private String residence;

	@Column(name = "marital_status")
	private MaritalStatusType maritalStatus;

	@Column(name = "other_marital_status")
	private String otherMaritalStatus;

	@Column(name = "population_type", nullable = false)
	private PopulationType populationType;

	@Column(name = "other_population_type", nullable = false)
	private String otherPopulationType;

	@Column(name = "screening_reason")
	private TestReasonType screeningReason;

	@Column(name = "other_screening_reason")
	private String otherScreeningReason;

	@Column(name = "invalidated_test1")
	private boolean invalidatedTest1 = false;

	@Column(name = "test1_reaction", nullable = false)
	private TestReactionType test1Reaction;

	@Column(name = "invalidated_test2", nullable = false)
	private boolean invalidatedTest2 = false;

	@Column(name = "test2_reaction", nullable = false)
	private TestReactionType test2Reaction;

	@Column(name = "invalidated_test3", nullable = false)
	private boolean invalidatedTest3 = false;

	@Column(name = "test3_reaction", nullable = false)
	private TestReactionType test3Reaction;

	@Column(name = "final_result", nullable = false)
	private FinalResultType finalResult;

	@Column(name = "result_announcing_date")
	private Date resultAnnouncingDate;

	@Column(name = "retesting", nullable = false)
	private Boolean retesting;

	@Column(name = "comment")
	private String comment;

	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;

	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public ScreeningRegisterInfo getRegisterInfo() {
		return registerInfo;
	}

	public void setRegisterInfo(ScreeningRegisterInfo registerInfo) {
		this.registerInfo = registerInfo;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
