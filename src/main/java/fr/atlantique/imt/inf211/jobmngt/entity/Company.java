package fr.atlantique.imt.inf211.jobmngt.entity;
// Generated 28 févr. 2025, 21:16:57 by Hibernate Tools 5.6.15.Final


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/*
 * Company generated by hbm2java
 */
@Entity
@Table(name = "company"
        , schema = "public"
)
@JsonIgnoreProperties(value = {"userapp"}, allowSetters = true) // Ignore the joboffers field when serializing Company objects
@NamedQueries({
        @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
        @NamedQuery(name = "Company.findByMail", query = "SELECT c FROM Company c WHERE c.mail = :mail"),
        @NamedQuery(name = "Company.findByDenomination", query = "SELECT c FROM Company c WHERE c.denomination = :denomination"),
        @NamedQuery(name = "Company.findByDescription", query = "SELECT c FROM Company c WHERE c.description = :description")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mail")
public class Company implements java.io.Serializable {


    private String mail;
    private UserApp userapp;
    private String denomination;
    private String description;
    private Set<JobOffer> jobOffers = new HashSet<JobOffer>(0);

    public Company() {
    }


    public Company(UserApp userapp, String denomination) {
        this.userapp = userapp;
        this.denomination = denomination;
    }

    public Company(UserApp userapp, String denomination, String description, Set<JobOffer> jobOffers) {
        this.userapp = userapp;
        this.denomination = denomination;
        this.description = description;
        this.jobOffers = jobOffers;
    }

    @Id
    @Column(name = "mail", unique = true, nullable = false)
    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public UserApp getUserapp() {
        return this.userapp;
    }

    public void setUserapp(UserApp userapp) {
        this.userapp = userapp;
    }


    @Column(name = "denomination", nullable = false)
    public String getDenomination() {
        return this.denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }


    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Set<JobOffer> getJoboffers() {
        return this.jobOffers;
    }

    public void setJoboffers(Set<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }



}


