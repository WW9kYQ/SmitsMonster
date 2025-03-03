package fr.atlantique.imt.inf211.jobmngt.entity;
// Generated 28 févr. 2025, 21:16:57 by Hibernate Tools 5.6.15.Final


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Offermessage generated by hbm2java
 */
@Entity
@Table(name = "offermessage"
        , schema = "public"
)
public class OfferMessage implements java.io.Serializable {


    private int idmess;
    private JobOffer joboffer;
    private Message message;
    private Set<Application> applications = new HashSet<Application>(0);

    public OfferMessage() {
    }


    public OfferMessage(Message message) {
        this.message = message;
    }

    public OfferMessage(JobOffer joboffer, Message message, Set<Application> applications) {
        this.joboffer = joboffer;
        this.message = message;
        this.applications = applications;
    }

    @GenericGenerator(name = "OffermessageIdGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "message"))
    @Id
    @SequenceGenerator(name = "OFFERMESSAGE_ID_GENERATOR", sequenceName = "OFFERMESSAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFFERMESSAGE_ID_GENERATOR")


    @Column(name = "idmess", unique = true, nullable = false)
    public int getIdmess() {
        return this.idmess;
    }

    public void setIdmess(int idmess) {
        this.idmess = idmess;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idoffer")
    public JobOffer getJoboffer() {
        return this.joboffer;
    }

    public void setJoboffer(JobOffer joboffer) {
        this.joboffer = joboffer;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Message getMessage() {
        return this.message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appmessdest", schema = "public", joinColumns = {
            @JoinColumn(name = "idmessoffer", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "iddestapp", nullable = false, updatable = false)})
    public Set<Application> getApplications() {
        return this.applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }


}


