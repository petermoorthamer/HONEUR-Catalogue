package com.jnj.honeur.catalogue.service;

import com.jnj.honeur.catalogue.model.Notebook;
import com.jnj.honeur.catalogue.model.SharedNotebook;
import com.jnj.honeur.catalogue.model.Study;
import com.jnj.honeur.catalogue.repository.NotebookRepository;
import com.jnj.honeur.catalogue.repository.SharedNotebookRepository;
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
    private NotebookRepository notebookRepository;
    private SharedNotebookRepository sharedNotebookRepository;

    public StudyService(@Autowired StudyRepository studyRepository,
                        @Autowired NotebookRepository notebookRepository,
                        @Autowired SharedNotebookRepository sharedNotebookRepository) {
        this.studyRepository = studyRepository;
        this.notebookRepository = notebookRepository;
        this.sharedNotebookRepository = sharedNotebookRepository;
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
        // Make sure the notebook study link is set
        for(Notebook notebook:study.getNotebooks()) {
            notebook.setStudy(study);
        }
        return studyRepository.save(study);
    }


    public Optional<Notebook> findNotebookById(Long id) {
        return notebookRepository.findById(id);
    }

    public SharedNotebook save(final SharedNotebook sharedNotebook) {
        return sharedNotebookRepository.save(sharedNotebook);
    }

}
