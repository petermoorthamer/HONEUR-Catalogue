package com.jnj.honeur.catalogue.service;

import com.jnj.honeur.catalogue.model.Study;
import com.jnj.honeur.catalogue.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for saving and retrieving study records of the HONEUR Study Catalogue
 * @author Peter Moorthamer
 */
@Service
public class StudyService {

    private StudyRepository studyRepository;

    public StudyService(@Autowired StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Optional<Study> findById(Long id) {
        return studyRepository.findById(id);
    }

    public List<Study> findAll() {
        return studyRepository.findAll();
    }

    public List<Study> findByCriteria(final Study probe) {
        return studyRepository.findAll(Example.of(probe));
    }

    public Study save(final Study study) {
        return studyRepository.save(study);
    }

}
