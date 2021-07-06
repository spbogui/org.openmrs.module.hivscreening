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
package org.openmrs.module.hivscreening.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.hivscreening.HivScreening;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.TestingKit;
import org.openmrs.module.hivscreening.api.HivScreeningRegisterService;
import org.openmrs.module.hivscreening.api.db.HivScreeningRegisterDAO;

import java.util.List;

/**
 * It is a default implementation of {@link HivScreeningRegisterService}.
 */
public class HivScreeningRegisterServiceImpl extends BaseOpenmrsService implements HivScreeningRegisterService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private HivScreeningRegisterDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(HivScreeningRegisterDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public HivScreeningRegisterDAO getDao() {
	    return dao;
    }

    @Override
    public TestingKit getTestingKitById(Integer testingKitId) {
        return dao.getTestingKitById(testingKitId);
    }

    @Override
    public ScreeningRegisterInfo getScreeningRegisterInfoById(Integer id) {
        return dao.getScreeningRegisterInfoById(id);
    }

    @Override
    public TestingKit getTestingKitByBatchNumber(String testingKitBatchNumber) {
        return dao.getTestingKitByBatchNumber(testingKitBatchNumber);
    }

    @Override
    public TestingKit saveTestingKit(TestingKit testingKit) {
        return dao.saveTestingKit(testingKit);
    }

    @Override
    public ScreeningRegisterInfo saveScreeningRegisterInfo(ScreeningRegisterInfo registerInfo) {
        return dao.saveScreeningRegisterInfo(registerInfo);
    }

    @Override
    public HivScreening getHivScreeningByScreeningCode(String screeningCode) {
        return dao.getHivScreeningByScreeningCode(screeningCode);
    }

    @Override
    public HivScreening getHivScreeningById(Integer id) {
        return dao.getHivScreeningById(id);
    }

    @Override
    public HivScreening saveHivScreening(HivScreening hivScreening) {
        return dao.saveHivScreening(hivScreening);
    }

    @Override
    public List<HivScreening> getAllHivScreening(Integer first, Integer numberOfLine, ScreeningRegisterInfo registerInfo) {
        return dao.getAllHivScreening(first, numberOfLine, registerInfo);
    }

    @Override
    public Integer countScreening(ScreeningRegisterInfo registerInfo) {
        return dao.countScreening(registerInfo);
    }

    @Override
    public List<ScreeningRegisterInfo> getAllScreeningRegisterInfo(Integer numberOfLine) {
        return dao.getAllScreeningRegisterInfo(numberOfLine);
    }

    @Override
    public void removeHivScreening(HivScreening hivScreening) {
        dao.removeHivScreening(hivScreening);
    }

    @Override
    public void removeRegisterInfo(ScreeningRegisterInfo registerInfo) {
        dao.removeRegisterInfo(registerInfo);
    }
}
