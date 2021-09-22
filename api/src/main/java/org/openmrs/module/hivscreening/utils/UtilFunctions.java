package org.openmrs.module.hivscreening.utils;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.hivscreening.api.HivScreeningRegisterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilFunctions {
    public static Location getUserLocation() {
        if (Context.getUserContext().getLocation() != null) {
            return Context.getUserContext().getLocation();
        }
        return Context.getLocationService().getDefaultLocation();
    }

    public static List<String> getTestingKitsNames(String kits) {
        String getPropertyValue = getPropertyGlobalProperties(kits);
        List<String> kitNames = new ArrayList<String>();
        if (getPropertyValue != null) {
            String[] kitList = getPropertyValue.split(",");
            for (String kit : kitList) {
                kitNames.add(kit.split(":")[0]);
            }
        }
        return kitNames;
    }

    public static List<String> getTesting1KitsNames() {
        return getTestingKitsNames("test1Kits");
    }

    public static List<String> getLabTestingKitsNames() {
        return getTestingKitsNames("labTest2Kits");
    }

    public static List<String> getTesting2KitsNames() {
        return getTestingKitsNames("test2AndLabTest3Kits");
    }

    public static Integer getTestingKitMaxCount(String kitName, String testType) {
        String getPropertyValue = getPropertyGlobalProperties(testType);
//        System.out.println("------------------------------------- get Max count : " + getPropertyValue);
        if (getPropertyValue != null) {
            String[] kitList = getPropertyValue.split(",");
            for (String kit : kitList) {
                String[] data = kit.split(":");
//                System.out.println("------------------------------------- get Max count : " + data[0]);
                if (data[0].equals(kitName)) {
                    return Integer.parseInt(data[1]);
                }
            }
        }
        return 0;
    }

    public static Integer getTesting1MaxCount(String kitName) {
        return getTestingKitMaxCount(kitName, "test1Kits");
    }

    public static Integer getTesting2MaxCount(String kitName) {
        return getTestingKitMaxCount(kitName, "test2AndLabTest3Kits");
    }

    public static Integer getLabTestingMaxCount(String kitName) {
        return getTestingKitMaxCount(kitName, "labTest2Kits");
    }

    public static String getPropertyGlobalProperties(String property) {
        String value = Context.getAdministrationService().getGlobalProperty("hivScreening." + property);
        if (!value.isEmpty()) {
            return value;
        }
        return null;
    }

    public static Map<Integer, String> getServices() {
        Map<Integer, String> services = new HashMap<Integer, String>();
        services.put(0,"Cancérologie");
        services.put(1,"CDI");
        services.put(2,"CDT");
        services.put(3,"Chirurgie générale et digestive");
        services.put(4,"Chirurgie pédiatrique");
        services.put(5,"Consultations externes");
        services.put(6,"CPN");
        services.put(7,"Dermatologie");
        services.put(8,"Diabétologie");
        services.put(9,"Dispensaire");
        services.put(10,"Gastro-entérologie");
        services.put(11,"Gynécologie-obstétrique");
        services.put(12,"Hématologie");
        services.put(13,"Hospitalisation");
        services.put(14,"Laboratoire");
        services.put(15,"Maternité");
        services.put(16,"Médecine générale");
        services.put(17,"Néonatalogie");
        services.put(18,"Néphrologie");
        services.put(19,"Neurologie ");
        services.put(20,"Nutrition");
        services.put(21,"O.R.L");
        services.put(22,"Odonto-stomatologique");
        services.put(23,"Ophtalmologie");
        services.put(24,"Pédiatrie");
        services.put(25,"Pneumologie (PPH)");
        services.put(26,"Post-natal");
        services.put(27,"Réanimation");
//        services.put(28,"Rééducation et réadaptation fonctionnelles");
        services.put(28,"Rhumatologie");
        services.put(29,"SMIT");
        services.put(30,"Traumatologie et chirurgie orthopédique");
        services.put(31,"Urgences");
        services.put(32,"Urologie");
        services.put(33,"CAT");
        services.put(34,"OBC/ONG");
        return services;
    }

    public static HivScreeningRegisterService service() {
        return Context.getService(HivScreeningRegisterService.class);
    }
}
