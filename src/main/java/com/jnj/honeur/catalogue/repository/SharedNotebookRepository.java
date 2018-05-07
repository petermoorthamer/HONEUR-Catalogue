package com.jnj.honeur.catalogue.repository;

import com.jnj.honeur.catalogue.model.SharedNotebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for saving and retrieving shared notebooks
 * @author Peter Moorthamer
 */

@Repository
public interface SharedNotebookRepository extends JpaRepository<SharedNotebook, Long>  {

}
