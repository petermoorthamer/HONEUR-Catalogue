package com.jnj.honeur.catalogue.controller;

import com.jnj.honeur.catalogue.model.Notebook;
import com.jnj.honeur.catalogue.model.SharedNotebook;
import com.jnj.honeur.catalogue.model.Study;
import com.jnj.honeur.catalogue.service.StudyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * REST API for retrieving and saving Study records of the HONEUR Study Catalogue
 * @author Peter Moorthamer
 */

@Controller
public class StudyCatalogueController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyCatalogueController.class);

    private StudyService studyService;

    public StudyCatalogueController(
            @Autowired StudyService studyService) {
        this.studyService = studyService;
    }

    @RequestMapping(value = "/studies", method = RequestMethod.GET)
    public ResponseEntity<Object> getStudies() {
        try {
            List<Study> studies = studyService.findAll();
            return new ResponseEntity<>(studies, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>("The list of studies cannot be retrieved!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/studies/{studyId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getStudy(@PathVariable Long studyId)  {
        try {
            final Optional<Study> study = studyService.findById(studyId);
            return study.<ResponseEntity<Object>>map(study1 -> new ResponseEntity<>(study1, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>("The study cannot be retrieved!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/studies")
    public ResponseEntity<Object> createStudy(@RequestBody Study study) {
        try {
            Study savedStudy = studyService.save(study);
            return ResponseEntity.created(new URI("/studies/" + savedStudy.getId())).build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>("The study cannot be saved!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/studies")
    public ResponseEntity<Object> saveStudy(@RequestBody Study study) {
        try {
            Study updatedStudy = studyService.save(study);
            LOGGER.debug("Updated study: " + updatedStudy);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>("The study cannot be saved!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/studies/{studyId}/{notebookId}/{sharedNotebookUuid}")
    public ResponseEntity<Object> saveSharedNotebook(@PathVariable Long studyId, @PathVariable Long notebookId, @PathVariable String sharedNotebookUuid, @RequestBody SharedNotebook sharedNotebook) {
        try {
            final Optional<Notebook> notebook = studyService.findNotebookById(notebookId);
            if(!notebook.isPresent()) {
                throw new IllegalArgumentException("No notebook found with ID " + notebookId);
            }
            sharedNotebook.setNotebook(notebook.get());
            SharedNotebook savedSharedNotebook = studyService.save(sharedNotebook);
            return ResponseEntity.created(new URI("/studies/" + studyId + "/" + notebookId + "/" + savedSharedNotebook.getId())).build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>("The shared notebook cannot be saved!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
