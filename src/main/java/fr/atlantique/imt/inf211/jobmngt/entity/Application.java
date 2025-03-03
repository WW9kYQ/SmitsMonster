package fr.atlantique.imt.inf211.jobmngt.entity;
// Generated 28 févr. 2025, 21:16:57 by Hibernate Tools 5.6.15.Final


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Application generated by hbm2java
 */
@Entity
@Table(name = "application"
        , schema = "public"
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@NamedQueries({
        @NamedQuery(name = "Application.findByFieldAndQualif", query = "SELECT a FROM Application a Where :field MEMBER OF a.fields and a.qualificationlevel = :qualificationLevel"),
        @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a order by a.id ASC"),
        @NamedQuery(name = "Application.findById", query = "SELECT a FROM Application a WHERE a.id = :id"),
})

public class Application implements java.io.Serializable {


    private int id;
    private Candidate candidate;
    private QualificationLevel qualificationlevel;
    private String cv;
    private Date appdate;
    private Set<ApplicationMessage> applicationMessages = new HashSet<ApplicationMessage>(0);
    private Set<Field> fields = new HashSet<Field>(0);
    private Set<OfferMessage> offerMessages = new HashSet<OfferMessage>(0);

    public Application() {
    }


    public Application(int id, String cv, Date appdate) {
        this.id = id;
        this.cv = cv;
        this.appdate = appdate;
    }

    public Application(int id, Candidate candidate, QualificationLevel qualificationlevel, String cv, Date appdate, Set<ApplicationMessage> applicationMessages, Set<Field> fields, Set<OfferMessage> offerMessages) {
        this.id = id;
        this.candidate = candidate;
        this.qualificationlevel = qualificationlevel;
        this.cv = cv;
        this.appdate = appdate;
        this.applicationMessages = applicationMessages;
        this.fields = fields;
        this.offerMessages = offerMessages;
    }

    @Id
    @SequenceGenerator(name = "APPLICATION_ID_GENERATOR", sequenceName = "APPLICATION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICATION_ID_GENERATOR")


    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher")
    public Candidate getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualification")
    public QualificationLevel getQualificationlevel() {
        return this.qualificationlevel;
    }

    public void setQualificationlevel(QualificationLevel qualificationlevel) {
        this.qualificationlevel = qualificationlevel;
    }


    @Column(name = "cv", nullable = false)
    public String getCv() {
        return this.cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "appdate", nullable = false, length = 13)
    public Date getAppdate() {
        return this.appdate;
    }

    public void setAppdate(Date appdate) {
        this.appdate = appdate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    public Set<ApplicationMessage> getApplicationmessages() {
        return this.applicationMessages;
    }

    public void setApplicationmessages(Set<ApplicationMessage> applicationMessages) {
        this.applicationMessages = applicationMessages;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "applicationfields", schema = "public", joinColumns = {
            @JoinColumn(name = "appid", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "idfield", nullable = false, updatable = false)})
    public Set<Field> getFields() {
        return this.fields;
    }
    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appmessdest", schema = "public", joinColumns = {
            @JoinColumn(name = "iddestapp", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "idmessoffer", nullable = false, updatable = false)})
    public Set<OfferMessage> getOffermessages() {
        return this.offerMessages;
    }

    public void setOffermessages(Set<OfferMessage> offerMessages) {
        this.offerMessages = offerMessages;
    }


}


