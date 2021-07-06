package org.openmrs.module.hivscreening;

import org.openmrs.Location;
import org.openmrs.module.hivscreening.utils.UtilFunctions;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "TestingKit")
@Table(name = "hiv_screening_testing_kit")
public class TestingKit extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testing_kit_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "batch_number", nullable = false, length = 20, unique = true)
    private String batchNumber;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated = new Date();

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "count_usage", nullable = false)
    private Integer countUsage = 0;

    @Transient
    private Integer kitCountMax;

    public TestingKit() {
        kitCountMax = 0;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        id = integer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getCountUsage() {
        return countUsage;
    }

    public void setCountUsage(Integer countUsage) {
        this.countUsage = countUsage;
    }

    public Integer getKitCountMax() {
        if (getName() != null) {
            if (UtilFunctions.getTesting1KitsNames().contains(getName())) {
                kitCountMax = UtilFunctions.getTesting1MaxCount(getName());
            } else if (UtilFunctions.getTesting2KitsNames().contains(getName())) {
                kitCountMax = UtilFunctions.getTesting2MaxCount(getName());
            } else if (UtilFunctions.getLabTestingKitsNames().contains(getName())){
                kitCountMax = UtilFunctions.getLabTestingMaxCount(getName());
            }
        }
        return kitCountMax;
    }

    public void setKitCountMax(Integer kitCountMax) {
        this.kitCountMax = kitCountMax;
    }

}
