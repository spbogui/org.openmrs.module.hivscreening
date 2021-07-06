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
package org.openmrs.module.hivscreening.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hivscreening.HivScreening;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.TestingKit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(HivScreeningRegisterService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface HivScreeningRegisterService extends OpenmrsService {
    TestingKit getTestingKitById(Integer testingKitId);

	ScreeningRegisterInfo getScreeningRegisterInfoById(Integer id);

	TestingKit getTestingKitByBatchNumber(String testingKitBatchNumber);

	TestingKit saveTestingKit(TestingKit testingKit);

	ScreeningRegisterInfo saveScreeningRegisterInfo(ScreeningRegisterInfo registerInfo);

    HivScreening getHivScreeningByScreeningCode(String screeningCode);

	HivScreening getHivScreeningById(Integer id);

	HivScreening saveHivScreening(HivScreening hivScreening);

	List<HivScreening> getAllHivScreening(Integer first, Integer numberOfLine, ScreeningRegisterInfo registerInfo);

	Integer countScreening(ScreeningRegisterInfo registerInfo);

	List<ScreeningRegisterInfo> getAllScreeningRegisterInfo(Integer numberOfLine);

	void removeHivScreening(HivScreening hivScreening);

	void removeRegisterInfo(ScreeningRegisterInfo registerInfo);
	/*
	 * Add service methods here
	 * 
	 */

}
