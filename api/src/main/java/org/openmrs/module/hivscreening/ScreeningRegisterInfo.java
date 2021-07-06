package org.openmrs.module.hivscreening;

import org.openmrs.Location;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "ScreeningRegisterInfo")
@Table(name = "hiv_screening_screening_register_info")
public class ScreeningRegisterInfo extends AbstractObject {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_info_id")
    private Integer id;

    @Column(name = "screening_site_type", nullable = false)
    private String screeningSiteType;

    @OneToMany(mappedBy = "registerInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HivScreening> hivScreenings;

    @ManyToOne
    @JoinColumn(name = "testing1_kit", nullable = false)
    private TestingKit testing1Kit;

    @ManyToOne
    @JoinColumn(name = "lab_testing_kit")
    private TestingKit labTesting2Kit;

    @ManyToOne
    @JoinColumn(name = "testing2_kit", nullable = false)
    private TestingKit testing2Kit;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated = new Date();

    @Column(name = "screening_post", nullable = false)
    private String screeningPost;

    @Column(name = "register_code", nullable = false)
    private String registerCode;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        id = integer;
    }

    public String getScreeningSiteType() {
        return screeningSiteType;
    }

    public void setScreeningSiteType(String screeningSiteType) {
        this.screeningSiteType = screeningSiteType;
    }

    public Set<HivScreening> getHivScreenings() {
        return hivScreenings;
    }

    public void setHivScreenings(Set<HivScreening> hivScreenings) {
        this.hivScreenings = hivScreenings;
    }

    public TestingKit getLabTesting2Kit() {
        return labTesting2Kit;
    }

    public void setLabTesting2Kit(TestingKit labTesting2Kit) {
        this.labTesting2Kit = labTesting2Kit;
    }

    public TestingKit getTesting1Kit() {
        return testing1Kit;
    }

    public void setTesting1Kit(TestingKit testingKit) {
        this.testing1Kit = testingKit;
    }

    public TestingKit getTesting2Kit() {
        return testing2Kit;
    }

    public void setTesting2Kit(TestingKit testing2Kit) {
        this.testing2Kit = testing2Kit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getScreeningPost() {
        return screeningPost;
    }

    public void setScreeningPost(String screeningPost) {
        this.screeningPost = screeningPost;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }
}
