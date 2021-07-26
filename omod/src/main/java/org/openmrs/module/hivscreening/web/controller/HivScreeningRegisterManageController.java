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
package org.openmrs.module.hivscreening.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hivscreening.HivScreening;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.TestingKit;
import org.openmrs.module.hivscreening.api.HivScreeningRegisterService;
import org.openmrs.module.hivscreening.forms.RegisterForm;
import org.openmrs.module.hivscreening.forms.ScreeningForm;
import org.openmrs.module.hivscreening.forms.validators.RegisterFormValidator;
import org.openmrs.module.hivscreening.forms.validators.ScreeningFormValidator;
import org.openmrs.module.hivscreening.utils.UtilFunctions;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * The main controller.
 */
@Controller
public class  HivScreeningRegisterManageController {

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/hivscreening/manage.form", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
		model.addAttribute("services", UtilFunctions.getServices());
		model.addAttribute("registers", UtilFunctions.service().getAllScreeningRegisterInfo(10));
	}

	@RequestMapping(value = "/module/hivscreening/createRegister.form", method = RequestMethod.GET)
	public String register(ModelMap model,
						   HttpServletRequest request,
						   @RequestParam(value = "serviceId", defaultValue = "0", required = false) Integer serviceId,
						   @RequestParam(value = "registerId", defaultValue = "0", required = false) Integer registerId,
						   @RequestParam(value = "action", defaultValue = "", required = false) String action,
						   RegisterForm registerForm) {
		if (Context.isAuthenticated()) {
			if (registerId != 0) {
				ScreeningRegisterInfo registerInfo = UtilFunctions.service().getScreeningRegisterInfoById(registerId);
				if (registerInfo != null) {
					if (action != null && !action.isEmpty()) {
						ScreeningRegisterInfo screeningRegisterInfo = new ScreeningRegisterInfo();
						screeningRegisterInfo.setScreeningSiteType(registerInfo.getScreeningSiteType());
						screeningRegisterInfo.setScreeningPost(registerInfo.getScreeningPost());
						if (action.equals("register")) {
							int code = Integer.parseInt(registerInfo.getRegisterCode()) + 1;
							screeningRegisterInfo.setRegisterCode(Integer.toString(code));
						}
						if (!registerInfo.getTesting1Kit().getKitCountMax().equals(registerInfo.getTesting1Kit().getCountUsage()) &&
								!registerInfo.getTesting1Kit().getExpiryDate().before(new Date())) {
							registerForm.setTestingKit1(registerInfo.getTesting1Kit());
						}
						if (!registerInfo.getTesting2Kit().getKitCountMax().equals(registerInfo.getTesting2Kit().getCountUsage()) &&
								!registerInfo.getTesting2Kit().getExpiryDate().before(new Date())) {
							registerForm.setTestingKit2(registerInfo.getTesting2Kit());
						}
						if (registerInfo.getScreeningSiteType().equals("Laboratoire")) {
							if (!registerInfo.getLabTesting2Kit().getKitCountMax().equals(registerInfo.getLabTesting2Kit().getCountUsage()) &&
									!registerInfo.getLabTesting2Kit().getExpiryDate().before(new Date())) {
								registerForm.setLabTestingKit(registerInfo.getLabTesting2Kit());
							}
						}

						registerForm.setRegisterInfo(screeningRegisterInfo);
					}
				}
			} else {
				registerForm.setScreeningSiteType(UtilFunctions.getServices().get(serviceId));
			}

//			model.addAttribute("serviceName", UtilFunctions.getServices().get(serviceId));
			model.addAttribute("registerForm", registerForm);
			model.addAttribute("testingKit1Names", UtilFunctions.getTesting1KitsNames());
			model.addAttribute("testingKit2Names", UtilFunctions.getTesting2KitsNames());
			if (UtilFunctions.getServices().get(serviceId).equals("Laboratoire")) {
				model.addAttribute("labTestingKitNames", UtilFunctions.getLabTestingKitsNames());
			}
		}
		return null;
	}


	@RequestMapping(value = "/module/hivscreening/createRegister.form", method = RequestMethod.POST)
	public String saveRegister(ModelMap model,
							   HttpServletRequest request,
							   @RequestParam(value = "serviceId", defaultValue = "0", required = false) Integer serviceId,
							   @RequestParam(value = "registerId", defaultValue = "0", required = false) Integer registerId,
							   @RequestParam(value = "action", defaultValue = "", required = false) String action,
							   RegisterForm registerForm,
							   BindingResult result) {
		if (Context.isAuthenticated()) {
			new RegisterFormValidator().validate(registerForm, result);

			if (!result.hasErrors()){
				ScreeningRegisterInfo registerInfo = registerForm.getScreeningRegisterInfo();
				TestingKit testing1Kit = registerForm.getTestingKit1();
				if (testing1Kit.getId() == null) {
					registerInfo.setTesting1Kit(UtilFunctions.service().saveTestingKit(testing1Kit));
				} else {
					registerInfo.setTesting1Kit(testing1Kit);
				}

				TestingKit testing2Kit = registerForm.getTestingKit2();
				if (testing2Kit.getId() == null) {
					registerInfo.setTesting2Kit(UtilFunctions.service().saveTestingKit(testing2Kit));
				} else {
					registerInfo.setTesting1Kit(testing1Kit);
				}
				if (registerForm.getScreeningSiteType().equals("Laboratoire")) {
					TestingKit labTestingKit = registerForm.getLabTestingKit();
					if (labTestingKit.getId() == null) {
						registerInfo.setLabTesting2Kit(UtilFunctions.service().saveTestingKit(labTestingKit));
					} else {
						registerInfo.setLabTesting2Kit(labTestingKit);
					}
				}

				ScreeningRegisterInfo info = UtilFunctions.service().saveScreeningRegisterInfo(registerInfo);
				HttpSession session = request.getSession();
				session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Vous pouvez maintenant ajouter les lignes de dépistage !");
				return "redirect:/module/hivscreening/screening.form?registerId=" +
						info.getId();
			}

			registerForm.setScreeningSiteType(UtilFunctions.getServices().get(serviceId));
			model.addAttribute("registerForm", registerForm);
			model.addAttribute("testingKit1Names", UtilFunctions.getTesting1KitsNames());
			if (UtilFunctions.getServices().get(serviceId).equals("Laboratoire")) {
				model.addAttribute("labTestingKitNames", UtilFunctions.getLabTestingKitsNames());
			}
			model.addAttribute("testingKit2Names", UtilFunctions.getTesting2KitsNames());
//			System.out.println("------------------ I'm here");
		}
		return null;
	}

	@RequestMapping(value = "/module/hivscreening/screening.form", method = RequestMethod.GET)
	public String screening(ModelMap model,
							HttpServletRequest request,
							@RequestParam(value = "registerId") Integer registerId,
							@RequestParam(value = "screeningId", defaultValue = "0", required = false) Integer screeningId,
							@RequestParam(value = "first", defaultValue = "0", required = false) Integer first,
							@RequestParam(value = "numberOfLine", defaultValue = "10", required = false) Integer numberOfLine,
							ScreeningForm screeningForm) {
		if (Context.isAuthenticated()) {
			ScreeningRegisterInfo registerInfo = UtilFunctions.service().getScreeningRegisterInfoById(registerId);
			if (registerInfo != null) {
				if (screeningId != 0) {
					HivScreening screening = UtilFunctions.service().getHivScreeningById(screeningId);
					if (screening != null) {
						screeningForm.setHivScreening(screening);
					}
				} else {
					screeningForm.setRegisterInfoId(registerId);
				}
				model.addAttribute("finishedKit", false);
				model.addAttribute("expiryKit", false);
				if (registerInfo.getTesting1Kit().getCountUsage().equals(UtilFunctions.getTesting1MaxCount(registerInfo.getTesting1Kit().getName()))) {
					model.addAttribute("finishedKit", true);
				} else
				if (registerInfo.getTesting2Kit().getCountUsage().equals(UtilFunctions.getTesting2MaxCount(registerInfo.getTesting2Kit().getName()))) {
					model.addAttribute("finishedKit", true);
				}
				if (registerInfo.getTesting1Kit().getExpiryDate().before(new Date())) {
					model.addAttribute("expiryKit", true);
				} else
				if (registerInfo.getTesting2Kit().getExpiryDate().before(new Date())) {
					model.addAttribute("expiryKit", true);
				}
				if (registerInfo.getScreeningSiteType().equals("Laboratoire")){
					if (registerInfo.getLabTesting2Kit().getCountUsage().equals(UtilFunctions.getLabTestingMaxCount(registerInfo.getLabTesting2Kit().getName()))) {
						model.addAttribute("finishedKit", true);
					}
					else if (registerInfo.getLabTesting2Kit().getExpiryDate().before(new Date())) {
						model.addAttribute("expiryKit", true);
					}
				}

				Integer countScreening = Context.getService(HivScreeningRegisterService.class).countScreening(registerInfo);
				Integer page = first + 1;
				double last = Math.ceil(countScreening / (numberOfLine * 1.0));
				model.addAttribute("registerInfo", registerInfo);
				model.addAttribute("screeningForm", screeningForm);
				model.addAttribute("first", first);
				model.addAttribute("last", (int) last);
				model.addAttribute("page", page);
				model.addAttribute("numberOfScreening", countScreening);
				model.addAttribute("hivScreenings", UtilFunctions.service().getAllHivScreening(first * numberOfLine, numberOfLine, registerInfo));
			}
		}
		return null;
	}

	@RequestMapping(value = "/module/hivscreening/screening.form", method = RequestMethod.POST)
	public String saveScreening(ModelMap model,
							HttpServletRequest request,
							@RequestParam(value = "registerId") Integer registerId,
							@RequestParam(value = "screeningId", defaultValue = "0", required = false) Integer screeningId,
							@RequestParam(value = "first", defaultValue = "0", required = false) Integer first,
							@RequestParam(value = "numberOfLine", defaultValue = "10", required = false) Integer numberOfLine,
							ScreeningForm screeningForm,
							BindingResult result) {
		if (Context.isAuthenticated()) {
			new ScreeningFormValidator().validate(screeningForm, result);

			if (!result.hasErrors()) {
				HivScreening hivScreening = screeningForm.getHivScreening();
				System.out.println("Screening location : ");
				HttpSession session = request.getSession();
				if (hivScreening.getId() != null) {
					UtilFunctions.service().saveHivScreening(hivScreening);
					session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Dépistage inséré avec succés !");
				} else {
					UtilFunctions.service().saveHivScreening(hivScreening);
					session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Dépistage mis à jour avec succès !");
				}
//				if (hivScreening.getId() == null) {
//					TestingKit testingKit1 = hivScreening.getRegisterInfo().getTesting1Kit();
//					int count = testingKit1.getCountUsage() + 1;
//					if (screeningForm.isInvalidatedTest1()) {
//						count += 1;
//					}
//					testingKit1.setCountUsage(count);
//					UtilFunctions.service().saveTestingKit(testingKit1);
//
//					if (screeningForm.getTest3Reaction() != null) {
//						count = 0;
//						TestingKit testingKit3 = hivScreening.getRegisterInfo().getTesting2Kit();
//						count = testingKit3.getCountUsage() + 1;
//						if (screeningForm.isInvalidatedTest3()) {
//							count += 1;
//						}
//						testingKit3.setCountUsage(count);
//						UtilFunctions.service().saveTestingKit(testingKit3);
//					}
//
//					if (hivScreening.getRegisterInfo().getScreeningSiteType().equals("Laboratoire")) {
//						if (screeningForm.getTest2Reaction() != null) {
//							count = 0;
//							TestingKit labTestingKit = hivScreening.getRegisterInfo().getLabTesting2Kit();
//							count = labTestingKit.getCountUsage() + 1;
//							if (screeningForm.isInvalidatedTest3()) {
//								count += 1;
//							}
//							labTestingKit.setCountUsage(count);
//							UtilFunctions.service().saveTestingKit(labTestingKit);
//						}
//					}
//				}

				if (first == 0)
					return "redirect:/module/hivscreening/screening.form?registerId=" + registerId;
				else {
					return "redirect:/module/hivscreening/screening.form?registerId=" + registerId + "&first=" + first + "&numberOfLine=" + numberOfLine;
				}
			}
		}
		ScreeningRegisterInfo registerInfo = UtilFunctions.service().getScreeningRegisterInfoById(registerId);
		model.addAttribute("registerInfo", registerInfo);
		model.addAttribute("first", first);
		model.addAttribute("numberOfScreening", Context.getService(HivScreeningRegisterService.class).countScreening(registerInfo));
		model.addAttribute("screeningForm", screeningForm);
		model.addAttribute("hivScreenings", UtilFunctions.service().getAllHivScreening(first, numberOfLine, registerInfo));
		return null;
	}

	@RequestMapping(value = "/module/hivscreening/deleteScreening.form", method = RequestMethod.POST)
	public String saveScreening(ModelMap model,
								@RequestParam(value = "registerId") Integer registerId,
								@RequestParam(value = "screeningId", defaultValue = "0", required = false) Integer screeningId)
	{
		if (Context.isAuthenticated()) {
			if (screeningId != 0) {
				HivScreening hivScreening = UtilFunctions.service().getHivScreeningById(screeningId);
				if (hivScreening != null) {
					TestingKit testingKit1 = hivScreening.getRegisterInfo().getTesting1Kit();
					int count = testingKit1.getCountUsage() - 1;
					if (hivScreening.isInvalidatedTest1()) {
						count -= 1;
					}
					testingKit1.setCountUsage(count);
					UtilFunctions.service().saveTestingKit(testingKit1);

					if (hivScreening.getTest3Reaction() != null) {
						count = 0;
						TestingKit testingKit3 = hivScreening.getRegisterInfo().getTesting2Kit();
						count = testingKit3.getCountUsage() - 1;
						if (hivScreening.isInvalidatedTest3()) {
							count -= 1;
						}
						testingKit3.setCountUsage(count);
						UtilFunctions.service().saveTestingKit(testingKit3);
					}

					if (hivScreening.getRegisterInfo().getScreeningSiteType().equals("Laboratoire")) {
						if (hivScreening.getTest2Reaction() != null) {
							count = 0;
							TestingKit labTestingKit = hivScreening.getRegisterInfo().getLabTesting2Kit();
							count = labTestingKit.getCountUsage() - 1;
							if (hivScreening.isInvalidatedTest3()) {
								count -= 1;
							}
							labTestingKit.setCountUsage(count);
							UtilFunctions.service().saveTestingKit(labTestingKit);
						}
					}

					UtilFunctions.service().removeHivScreening(hivScreening);
				}
			}
			return "redirect:/module/hivscreening/screening.form?registerId=" + registerId;
		}
		return null;
	}
	//

	@RequestMapping(value = "/module/hivscreening/registerDelete.form", method = RequestMethod.GET)
	public String deleteRegister(HttpServletRequest request,
								@RequestParam(value = "registerId") Integer registerId) {
		if (Context.isAuthenticated()) {
			ScreeningRegisterInfo registerInfo = Context.getService(HivScreeningRegisterService.class).getScreeningRegisterInfoById(registerId);
			if (registerInfo != null) {
				HttpSession session = request.getSession();
				Context.getService(HivScreeningRegisterService.class).removeRegisterInfo(registerInfo);
				session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Le régistre à été supprimé avec succés !");
				return "redirect:/module/hivscreening/manage.form";
			}
		}
		return null;
	}
}
