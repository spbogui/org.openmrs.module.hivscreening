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
package org.openmrs.module.hivscreening.api.db;

import org.openmrs.module.hivscreening.HivScreening;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.TestingKit;
import org.openmrs.module.hivscreening.api.HivScreeningRegisterService;

import java.util.List;

/**
 *  Database methods for {@link HivScreeningRegisterService}.
 */
public interface HivScreeningRegisterDAO {
    TestingKit getTestingKitById(Integer testingKitId);

	ScreeningRegisterInfo getScreeningRegisterInfoById(Integer id);

	TestingKit getTestingKitByBatchNumber(String testingKitBatchNumber);

    TestingKit saveTestingKit(TestingKit testingKit);

	ScreeningRegisterInfo saveScreeningRegisterInfo(ScreeningRegisterInfo registerInfo);

    HivScreening getHivScreeningByScreeningCode(String screeningCode);

	HivScreening getHivScreeningById(Integer id);

	HivScreening saveHivScreening(HivScreening hivScreening);

	List<HivScreening> getAllHivScreening(Integer first, Integer numberOfLine, ScreeningRegisterInfo registerInfo);

	void removeHivScreening(HivScreening hivScreening);

	List<ScreeningRegisterInfo> getAllScreeningRegisterInfo(Integer numberOfLine);

    void removeRegisterInfo(ScreeningRegisterInfo registerInfo);

	Integer countScreening(ScreeningRegisterInfo registerInfo);

	/*
	 * Add DAO methods here
	 */
}
