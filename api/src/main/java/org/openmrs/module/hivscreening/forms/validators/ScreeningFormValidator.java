package org.openmrs.module.hivscreening.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.module.hivscreening.HivScreening;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.enumerations.FinalResultType;
import org.openmrs.module.hivscreening.enumerations.TestReactionType;
import org.openmrs.module.hivscreening.forms.ScreeningForm;
import org.openmrs.module.hivscreening.utils.UtilFunctions;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

@Handler(supports = {ScreeningForm.class}, order = 50)
public class ScreeningFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ScreeningForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ScreeningForm form = (ScreeningForm) o;

        if (form == null) {
            errors.reject("hivscreening", "general.error");
        } else {
            ValidationUtils.rejectIfEmpty(errors, "screeningDate", null, "Requis !");
//            ValidationUtils.rejectIfEmpty(errors, "screeningCode", null, "Requis !");
//            ValidationUtils.rejectIfEmpty(errors, "gender", null, "Requis !");
//            ValidationUtils.rejectIfEmpty(errors, "age", null, "Requis !");
//            ValidationUtils.rejectIfEmpty(errors, "populationType", null, "Requis !");
//            ValidationUtils.rejectIfEmpty(errors, "screeningReason", null, "Requis !");
            ValidationUtils.rejectIfEmpty(errors, "test1Reaction", null, "Requis !");
//            ValidationUtils.rejectIfEmpty(errors, "test3Reaction", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "finalResult", null, "Requis !");

           if (form.getSampling() == null || form.getSampling().isEmpty()) {
               if (form.getScreeningCode() == null || form.getScreeningCode().isEmpty()){
                   errors.rejectValue("screeningCode", null, "Requis !");
               }
               if (form.getGender() == null || form.getGender().isEmpty()){
                   errors.rejectValue("gender", null, "Requis !");
               }
               if (form.getAge() == null) {
                   errors.rejectValue("age", null, "Requis !");
               }
               if (form.getPopulationType() == null){
                   errors.rejectValue("populationType", null, "Requis !");
               }
               if (form.getScreeningReason() == null){
                   errors.rejectValue("screeningReason", null, "Requis !");
                }
           } else {
               if (form.getGender() != null){
                   errors.rejectValue("gender", null, "Pas nécessaire !");
               }
               if (form.getAge() != null) {
                   errors.rejectValue("age", null, "Pas nécessaire !");
               }
               if (form.getPopulationType() != null){
                   errors.rejectValue("populationType", null, "Pas nécessaire !");
               }
               if (form.getMaritalStatus() != null){
                   errors.rejectValue("maritalStatus", null, "Pas nécessaire !");
               }
               if (form.getScreeningReason() != null){
                   errors.rejectValue("screeningReason", null, "Pas nécessaire !");
               }
           }

            if (form.getScreeningDate() != null && form.getScreeningDate().after(new Date())) {
                errors.rejectValue("screeningDate", null, "Incorect !");
            }

            if (form.getScreeningCode() != null) {
                HivScreening existingScreening = UtilFunctions.service().getHivScreeningByScreeningCode(form.getScreeningCode());
                if (existingScreening != null) {
                    if (form.getId() == null || (!existingScreening.getId().equals(form.getId()))) {
                        errors.rejectValue("screeningCode", null, "Dupliqué");
                    }
                }
            }

            ScreeningRegisterInfo registerInfo = UtilFunctions.service().getScreeningRegisterInfoById(form.getRegisterInfoId());
            if (!registerInfo.getScreeningSiteType().equals("Laboratoire")) {

                if (form.getTest1Reaction() != null) {
                    if (form.getTest1Reaction().equals(TestReactionType.NON_REACTIVE)) {
                        if (form.getTest3Reaction() != null) {
                            errors.rejectValue("test3Reaction", null, "Pas nécessaire !");
                        } else {
                            if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.NEG)) {
                                errors.rejectValue("finalResult", null, "Incorrect !");
                            }
                        }

                    } else {
                        if (form.getTest3Reaction() != null) {
                            if (form.getTest3Reaction().equals(TestReactionType.REACTIVE)) {
                                if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.POS)) {
                                    errors.rejectValue("finalResult", null, "Incorrect !");
                                }
                            } else {
                                if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.IND)) {
                                    errors.rejectValue("finalResult", null, "Incorrect !");
                                }
                            }
                        }
                        else {
                            errors.rejectValue("test3Reaction", null, "Requis !");
                        }
                    }
                }

                if (form.getTest1Reaction() != null && form.getTest3Reaction() != null) {
                    if (form.getTest1Reaction().equals(TestReactionType.REACTIVE) &&
                            form.getTest3Reaction().equals(TestReactionType.REACTIVE) &&
                            !form.getFinalResult().equals(FinalResultType.POS)
                    ) {
                        errors.rejectValue("finalResult", null, "Incorect !");
                    }
                    if (((form.getTest1Reaction().equals(TestReactionType.REACTIVE) &&
                            form.getTest3Reaction().equals(TestReactionType.NON_REACTIVE)) ||
                            (form.getTest1Reaction().equals(TestReactionType.NON_REACTIVE) &&
                                    form.getTest3Reaction().equals(TestReactionType.REACTIVE))) &&
                            !form.getFinalResult().equals(FinalResultType.IND)
                    ) {
                        errors.rejectValue("finalResult", null, "Incorect !");
                    }
                    if (form.getTest1Reaction().equals(TestReactionType.NON_REACTIVE) &&
                            !form.getFinalResult().equals(FinalResultType.NEG)
                    ) {
                        errors.rejectValue("finalResult", null, "Incorect !");
                    }
                }

            } else {
                if (form.getTest1Reaction() != null) {
                    if (form.getTest1Reaction().equals(TestReactionType.NON_REACTIVE)) {
                        if (form.getTest3Reaction() != null) {
                            errors.rejectValue("test3Reaction", null, "Pas nécessaire !");
                        }

                        if (form.getTest2Reaction() != null) {
                            errors.rejectValue("test2Reaction", null, "Pas nécessaire !");
                        }

                        if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.NEG)) {
                            errors.rejectValue("finalResult", null, "Incorrect !");
                        }
                    } else {
                        if (form.getTest2Reaction() == null) {
                            errors.rejectValue("test2Reaction", null, "Requis !");
                        } else {
                            if (form.getTest2Reaction().equals(TestReactionType.NON_REACTIVE)) {
                                if (form.getTest3Reaction() == null) {
                                    errors.rejectValue("test3Reaction", null, "Requis !");
                                } else {
                                    if (form.getTest3Reaction().equals(TestReactionType.NON_REACTIVE)) {
                                        if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.NEG)) {
                                            errors.rejectValue("finalResult", null, "Incorrect !");
                                        }
                                    } else {
                                        if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.POS)) {
                                            errors.rejectValue("finalResult", null, "Incorrect !");
                                        }
                                    }
                                }
                            } else {
                                if (form.getTest3Reaction() != null) {
                                    errors.rejectValue("test3Reaction", null, "Pas nécessaire !");
                                }

                                if (form.getTest2Reaction().equals(TestReactionType.REACTIVE_1)) {
                                    if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.POS1)) {
                                        errors.rejectValue("finalResult", null, "Incorrect !");
                                    }
                                } else if (form.getTest2Reaction().equals(TestReactionType.REACTIVE_2)) {
                                    if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.POS2)) {
                                        errors.rejectValue("finalResult", null, "Incorrect !");
                                    }
                                } else {
                                    if (form.getFinalResult() != null && !form.getFinalResult().equals(FinalResultType.POS1_2)) {
                                        errors.rejectValue("finalResult", null, "Incorrect !");
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
