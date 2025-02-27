package fr.atlantique.imt.inf211.jobmngt.entity;
// Generated Jan 27, 2025, 11:08:19 AM by Hibernate Tools 5.6.15.Final


import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
 import jakarta.persistence.GeneratedValue;import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Qualificationlevel generated by hbm2java
 */
@Entity
@Table(name="qualificationlevel"
    ,schema="public"
)
public class QualificationLevel  implements java.io.Serializable {


     private int id;
     private String label;

    public QualificationLevel() {
    }

    public QualificationLevel(int id, String label) {
       this.id = id;
       this.label = label;
    }
   
     @Id
	@SequenceGenerator(name = "QUALIFICATIONLEVEL_ID_GENERATOR", sequenceName = "QUALIFICATIONLEVEL_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "QUALIFICATIONLEVEL_ID_GENERATOR") 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="label", nullable=false, length=50)
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }




}


