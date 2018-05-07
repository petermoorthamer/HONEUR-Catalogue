package com.jnj.honeur.catalogue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a notebook linked to a study record in the HONEUR Study Catalogue
 * @author Peter Moorthamer
 */

@Entity
@Table(name="NOTEBOOK")
public class Notebook implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String externalId;
    private String name;
    private String url;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDate;
    @ManyToOne
    @JoinColumn(name = "STUDY_ID", nullable = false)
    @JsonIgnore
    private Study study;
    @OneToMany(mappedBy = "notebook", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    private Set<SharedNotebook> sharedNotebooks;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Calendar getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Study getStudy() {
        return study;
    }
    public void setStudy(Study study) {
        this.study = study;
    }

    public Set<SharedNotebook> getSharedNotebooks() {
        return sharedNotebooks;
    }
    public void setSharedNotebooks(Set<SharedNotebook> sharedNotebooks) {
        this.sharedNotebooks = sharedNotebooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(id == null) { return false; }
        Notebook notebook = (Notebook) o;
        return Objects.equals(id, notebook.id);
    }

    @Override
    public int hashCode() {
        if(id == null) { return super.hashCode(); }
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", externalId='" + externalId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", study=" + study +
                '}';
    }
}
