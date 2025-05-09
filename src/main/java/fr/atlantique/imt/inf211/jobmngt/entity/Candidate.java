package fr.atlantique.imt.inf211.jobmngt.entity;
// Generated 28 févr. 2025, 21:16:57 by Hibernate Tools 5.6.15.Final


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Cascade;

/**
 * Candidate generated by hbm2java
 */
@Entity
@Table(name = "candidate"
        , schema = "public"
)
@NamedQueries({
        @NamedQuery(name = "Candidate.findAll", query = "SELECT c FROM Candidate c"),
        @NamedQuery(name = "Candidate.findByMail", query = "SELECT c FROM Candidate c WHERE c.mail = :mail")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mail")
@JsonIgnoreProperties(value = {"applications", "userapp"}, allowSetters = true)
// Ignore the joboffers field when serializing Company objects

public class Candidate implements java.io.Serializable {


    private String mail;
    private UserApp userapp;
    private String lastname;
    private String firstname;
    private Set<Application> applications = new HashSet<Application>(0);

    public Candidate() {
    }


    public Candidate(UserApp userapp, String lastname, String firstname) {
        this.userapp = userapp;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Candidate(UserApp userapp, String lastname, String firstname, Set<Application> applications) {
        this.userapp = userapp;
        this.lastname = lastname;
        this.firstname = firstname;
        this.applications = applications;
    }

    @GenericGenerator(name = "CandidateIdGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "userapp"))
    @Id
    @GeneratedValue(generator = "CandidateIdGenerator")


    @Column(name = "mail", unique = true, nullable = false)
    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public UserApp getUserapp() {
        return this.userapp;
    }

    public void setUserapp(UserApp userapp) {
        this.userapp = userapp;
    }


    @Column(name = "lastname", nullable = false)
    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Column(name = "firstname", nullable = false)
    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Set<Application> getApplications() {
        return this.applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "mail='" + mail + '\'' +
                ", userapp=" + userapp +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", applications=" + applications +
                '}';
    }
}


