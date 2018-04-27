package com.jnj.honeur.catalogue.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a study record in the HONEUR Study Catalogue
 * @author Peter Moorthamer
 */

@Entity
@Table(name="STUDY")
public class Study implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
    @Column(name = "NUMBER", nullable = false, unique = true)
    private String number;
    private String description;
    private String acknowledgments;
    @Column(name = "LEAD_USERID")
    private Long leadUserId;
    @Column(name = "COLLABORATING_ORGANIZATION_IDS")
    private String collaboratingOrganizationIds;
    @Column(name = "CREATOR_USERID")
    private Long creatorUserId;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDate;
    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    private Set<Notebook> notebooks;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcknowledgments() {
        return acknowledgments;
    }
    public void setAcknowledgments(String acknowledgments) {
        this.acknowledgments = acknowledgments;
    }

    public Long getLeadUserId() {
        return leadUserId;
    }
    public void setLeadUserId(Long leadUserId) {
        this.leadUserId = leadUserId;
    }

    public String getCollaboratingOrganizationIds() {
        return collaboratingOrganizationIds;
    }
    public void setCollaboratingOrganizationIds(String collaboratingOrganizationIds) {
        this.collaboratingOrganizationIds = collaboratingOrganizationIds;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }
    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Calendar getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<Notebook> getNotebooks() {
        return notebooks;
    }
    public void setNotebooks(Set<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(id == null) { return false; }
        Study study = (Study) o;
        return Objects.equals(id, study.id);
    }

    @Override
    public int hashCode() {
        if(id == null) { return super.hashCode(); }
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
