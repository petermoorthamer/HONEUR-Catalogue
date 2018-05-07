package com.jnj.honeur.catalogue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

/**
 * Represents a notebook that is shared via HSS
 * @author Peter Moorthamer
 */
@Entity
@Table(name="SHARED_NOTEBOOK")
public class SharedNotebook {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    @Column(name = "SHARED_ORGANIZATION_IDS")
    private String sharedOrganizationIds;
    @ManyToOne
    @JsonIgnore
    private Notebook notebook;

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

    public String getSharedOrganizationIds() {
        return sharedOrganizationIds;
    }
    public void setSharedOrganizationIds(String sharedOrganizationIds) {
        this.sharedOrganizationIds = sharedOrganizationIds;
    }

    public Notebook getNotebook() {
        return notebook;
    }
    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(id == null) { return false; }
        SharedNotebook notebook = (SharedNotebook) o;
        return Objects.equals(id, notebook.id);
    }

    @Override
    public int hashCode() {
        if(id == null) { return super.hashCode(); }
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SharedNotebook{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", sharedOrganizationIds='" + sharedOrganizationIds + '\'' +
                '}';
    }
}
