package fr.atlantique.imt.inf211.jobmngt.entity;
// Generated 28 févr. 2025, 21:16:57 by Hibernate Tools 5.6.15.Final


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Userapp generated by hbm2java
 */
@Entity
@Table(name = "userapp"
        , schema = "public"
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mail")
public class UserApp implements java.io.Serializable {


    private String mail;
    private String password;
    private String city;
    @JsonManagedReference
    private Company company;
    @JsonManagedReference
    private Candidate candidate;

    public UserApp() {
    }


    public UserApp(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public UserApp(String mail, String password, String city, Candidate candidate, Company company) {
        this.mail = mail;
        this.password = password;
        this.city = city;
        this.candidate = candidate;
        this.company = company;
    }

    @Id
    @SequenceGenerator(name = "USERAPP_ID_GENERATOR", sequenceName = "USERAPP_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERAPP_ID_GENERATOR")


    @Column(name = "mail", unique = true, nullable = false)
    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "city")
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userapp")
    public Candidate getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userapp")
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


}


