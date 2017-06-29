/**
 * This file was generated by the Jeddict
 */
package org.eethar.platform.core.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author superyass
 */
@Entity
public class DCase implements IGenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String name;

    @Basic
    private String description;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @ElementCollection
    private List<String> caseType;

    @OneToOne(targetEntity = NGO.class)
    private NGO NGO;

    @OneToMany(targetEntity = Donation.class)
    private List<Donation> donations;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<String> getCaseType() {
        return this.caseType;
    }

    public void setCaseType(List<String> caseType) {
        this.caseType = caseType;
    }

    public NGO getNGO() {
        return this.NGO;
    }

    public void setNGO(NGO NGO) {
        this.NGO = NGO;
    }

    public List<Donation> getDonations() {
        return this.donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

}
