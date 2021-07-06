package org.openmrs.module.hivscreening.extension.html;

import org.openmrs.api.context.Context;
import org.openmrs.module.web.extension.LinkExt;

public class HivScreeningRegisterGutterLink extends LinkExt {
    @Override
    public String getLabel() {
        return Context.getMessageSourceService().getMessage("hivscreening.gutterTitle");
    }

    @Override
    public String getUrl() {
        return "module/hivscreening/manage.form";
    }

    @Override
    public String getRequiredPrivilege() {
        return "Manage Hiv Screening Register";
    }
}
