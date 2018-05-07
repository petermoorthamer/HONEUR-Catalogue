package com.jnj.honeur.catalogue.repository;

import com.jnj.honeur.catalogue.model.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for saving and retrieving study records of the HONEUR Study Catalogue
 * @author Peter Moorthamer
 */
@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    String BASE_QUERY = "SELECT DISTINCT s FROM Study s left join fetch s.notebooks n left join fetch n.sharedNotebooks ";

    @Query(BASE_QUERY + " WHERE s.id = :id")
    @Override
    Optional<Study> findById(@Param("id") Long id);

    @Query(BASE_QUERY)
    @Override
    List<Study> findAll();

}
