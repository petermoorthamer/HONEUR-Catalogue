package com.jnj.honeur.catalogue.repository;

import com.jnj.honeur.catalogue.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for saving and retrieving notebooks
 * @author Peter Moorthamer
 */
@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {

}
