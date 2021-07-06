package org.openmrs.module.hivscreening.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.module.hivscreening.ScreeningRegisterInfo;
import org.openmrs.module.hivscreening.TestingKit;
import org.openmrs.module.hivscreening.forms.RegisterForm;
import org.openmrs.module.hivscreening.utils.UtilFunctions;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.regex.Pattern;

@Handler(supports = {RegisterForm.class}, order = 50)
public class RegisterFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(RegisterForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterForm form = (RegisterForm) o;

        if (form == null) {
            errors.reject("hivscreening", "general.error");
        } else {
            ValidationUtils.rejectIfEmpty(errors, "screeningSiteType", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "screeningPost", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "registerCode", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "testingKit1Name", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "testingKit1BatchNumber", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "testingKit1ExpiryDate", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "testingKit2Name", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "testingKit2BatchNumber", null, "Ce champ est requis !");
            ValidationUtils.rejectIfEmpty(errors, "testingKit2ExpiryDate", null, "Ce champ est requis !");

            if (form.getScreeningSiteType() != null && form.getScreeningSiteType().equals("Laboratoire")) {
                if (form.getLabTestingKitName() == null || form.getLabTestingKitName().isEmpty()) {
                    errors.rejectValue("labTestingKitName", null, "Ce champ est requis !");
                }
                if (form.getLabTestingKitExpiryDate() == null) {
                    errors.rejectValue("labTestingKitExpiryDate", null, "Ce champ est requis !");
                }
                if (form.getLabTestingKitBatchNumber() == null) {
                    errors.rejectValue("labTestingKitBatchNumber", null, "Ce champ est requis !");
                }
            }

            Pattern pattern = Pattern.compile("^[0-9]{2}$", Pattern.CASE_INSENSITIVE);

            if (form.getScreeningPost() != null && !form.getScreeningPost().isEmpty()
                    && !pattern.matcher(form.getScreeningPost()).matches()) {
                errors.rejectValue("screeningPost", null, "Numéro de poste entre 01 .. 99 !");
            }

            if (form.getRegisterCode() != null && !form.getRegisterCode().isEmpty()
                    && !pattern.matcher(form.getScreeningPost()).matches()) {
                errors.rejectValue("registerCode", null, "Numéro du registre entre 01 .. 99 !");
            }

            if (form.getTestingKit1ExpiryDate() != null && form.getTestingKit1ExpiryDate().before(new Date())) {
                errors.rejectValue("testingKit1ExpiryDate", null, "La date de péremption rend le kit inutilisable !");
            }
            if (form.getTestingKit2ExpiryDate() != null && form.getTestingKit2ExpiryDate().before(new Date())) {
                errors.rejectValue("testingKit2ExpiryDate", null, "La date de péremption rend le kit inutilisable !");
            }
            if (form.getLabTestingKitExpiryDate() != null && form.getLabTestingKitExpiryDate().before(new Date())) {
                errors.rejectValue("labTestingKitExpiryDate", null, "La date de péremption rend le kit inutilisable !");
            }

//            if (form.getTestingKit1BatchNumber() != null) {
//                TestingKit existingTestingKit = UtilFunctions.service().getTestingKitByBatchNumber(form.getTestingKit1BatchNumber());
//                if (existingTestingKit != null) {
//                    if (form.getTestingKit1Id() == null || (!existingTestingKit.getId().equals(form.getTestingKit1Id()))) {
//                        errors.rejectValue("testingKit1BatchNumber", null, "Ce numéro de lot existe déjà pour un autre Kit !");
//                    }
//                }
//            }
//            if (form.getTestingKit2BatchNumber() != null) {
//                TestingKit existingTestingKit = UtilFunctions.service().getTestingKitByBatchNumber(form.getTestingKit2BatchNumber());
//                if (existingTestingKit != null) {
//                    if (form.getTestingKit2Id() == null || (!existingTestingKit.getId().equals(form.getTestingKit2Id()))) {
//                        errors.rejectValue("testingKit12BatchNumber", null, "Ce numéro de lot existe déjà pour un autre Kit !");
//                    }
//                }
//            }
//            if (form.getLabTestingKitBatchNumber() != null) {
//                TestingKit existingTestingKit = UtilFunctions.service().getTestingKitByBatchNumber(form.getLabTestingKitBatchNumber());
//                if (existingTestingKit != null) {
//                    if (form.getLabTestingKitId() == null || (!existingTestingKit.getId().equals(form.getLabTestingKitId()))) {
//                        errors.rejectValue("labTestingKitBatchNumber", null, "Ce numéro de lot existe déjà pour un autre Kit !");
//                    }
//                }
//            }
        }
    }
}
