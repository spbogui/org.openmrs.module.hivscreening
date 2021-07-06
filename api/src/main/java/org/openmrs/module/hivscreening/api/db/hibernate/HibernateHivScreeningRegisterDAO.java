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
package org.openmrs.module.hivscreening.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hivscreening.HivScreening;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.TestingKit;
import org.openmrs.module.hivscreening.api.db.HivScreeningRegisterDAO;

import java.util.List;

/**
 * It is a default implementation of  {@link HivScreeningRegisterDAO}.
 */
public class HibernateHivScreeningRegisterDAO implements HivScreeningRegisterDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }

	@Override
	public TestingKit getTestingKitById(Integer testingKitId) {
		return (TestingKit) sessionFactory.getCurrentSession().get(TestingKit.class, testingKitId);
	}

	@Override
	public ScreeningRegisterInfo getScreeningRegisterInfoById(Integer id) {
		return (ScreeningRegisterInfo) sessionFactory.getCurrentSession().get(ScreeningRegisterInfo.class, id);
	}

	@Override
	public TestingKit getTestingKitByBatchNumber(String testingKitBatchNumber) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TestingKit.class);
		return (TestingKit) criteria.add(Restrictions.eq("batchNumber", testingKitBatchNumber)).uniqueResult();
	}

	@Override
	public TestingKit saveTestingKit(TestingKit testingKit) {
    	sessionFactory.getCurrentSession().saveOrUpdate(testingKit);
		return testingKit;
	}

	@Override
	public ScreeningRegisterInfo saveScreeningRegisterInfo(ScreeningRegisterInfo registerInfo) {
		sessionFactory.getCurrentSession().saveOrUpdate(registerInfo);
		return registerInfo;
	}

	@Override
	public HivScreening getHivScreeningByScreeningCode(String screeningCode) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HivScreening.class);
		return (HivScreening) criteria.add(Restrictions.eq("screeningCode", screeningCode)).uniqueResult();
	}

	@Override
	public HivScreening getHivScreeningById(Integer id) {
		return (HivScreening) sessionFactory.getCurrentSession().get(HivScreening.class, id);
	}

	@Override
	public HivScreening saveHivScreening(HivScreening hivScreening) {
		sessionFactory.getCurrentSession().saveOrUpdate(hivScreening);
		return hivScreening;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HivScreening> getAllHivScreening(Integer first, Integer numberOfLine, ScreeningRegisterInfo registerInfo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HivScreening.class);
		return criteria
				.add(Restrictions.eq("registerInfo", registerInfo))
				.addOrder(Order.desc("screeningDate")).setFirstResult(first).setMaxResults(numberOfLine).list();
	}

	@Override
	public void removeHivScreening(HivScreening hivScreening) {
		sessionFactory.getCurrentSession().delete(hivScreening);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ScreeningRegisterInfo> getAllScreeningRegisterInfo(Integer numberOfLine) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ScreeningRegisterInfo.class);
		return criteria.addOrder(Order.desc("dateCreated")).setMaxResults(numberOfLine).list();
	}

	@Override
	public void removeRegisterInfo(ScreeningRegisterInfo registerInfo) {
		sessionFactory.getCurrentSession().delete(registerInfo);
	}

	@Override
	public Integer countScreening(ScreeningRegisterInfo registerInfo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ScreeningRegisterInfo.class);
		return criteria.list().size();
	}
}
