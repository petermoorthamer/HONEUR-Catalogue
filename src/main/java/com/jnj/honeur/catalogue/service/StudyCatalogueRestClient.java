package com.jnj.honeur.catalogue.service;

import com.jnj.honeur.catalogue.model.Study;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * REST client for saving and retrieving studies of the HONEUR Study Catalogue via the REST API
 * @author Peter Moorthamer
 */
public class StudyCatalogueRestClient {

    private String apiUrl;

    public StudyCatalogueRestClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Study createStudy(final Study study) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Study> request = new HttpEntity<>(study);
        URI location = restTemplate.postForLocation(apiUrl, request);
        study.setId(parseId(location));
        return study;
    }

    public Study saveStudy(final Study study) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Study> request = new HttpEntity<>(study);
        ResponseEntity response = restTemplate.exchange(apiUrl, HttpMethod.PUT, request, ResponseEntity.class);
        System.out.println("saveStudy response: " + response);
        return study;
    }

    public Study findStudyById(final Long studyId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Study> studyResponse = restTemplate.getForEntity(apiUrl + "/" + studyId, Study.class);
        return studyResponse.getBody();
    }

    public List<Study> findAllStudies() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Study[]>  studiesResponse = restTemplate.getForEntity(apiUrl, Study[].class);
        Study[] studies = studiesResponse.getBody();
        if(studies == null || studies.length == 0) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(studies);
        }
    }

    private Long parseId(URI uri) {
        String path = uri.getPath();
        return Long.valueOf(path.substring(path.lastIndexOf("/") + 1));
    }

    public static void main(String[] args) {
        String apiUrl = "https://localhost:8448/studies";
        StudyCatalogueRestClient restClient = new StudyCatalogueRestClient(apiUrl);

        String studyNumber = "S2";

        final Study study = new Study();
        study.setName("HONEUR_" + studyNumber);
        study.setNumber(studyNumber);
        study.setDescription("Description for study HONEUR_" + studyNumber);
        study.setAcknowledgments("Acknowledgments for study HONEUR_" +studyNumber);
        study.setModifiedDate(Calendar.getInstance());

        Study createdStudy = restClient.createStudy(study);
        System.out.println("Created study: " + createdStudy);

        createdStudy.setAcknowledgments("Updated acknowledgments for study HONEUR_" + studyNumber);
        Study updatedStudy = restClient.saveStudy(study);
        System.out.println("Updated study: " + updatedStudy);

        Study retrievedStudy = restClient.findStudyById(createdStudy.getId());
        System.out.println("Retrieved study: " + retrievedStudy);

        List<Study> allStudies = restClient.findAllStudies();
        System.out.println("All studies: ");
        for(Study s:allStudies) {
            System.out.println(s);
        }
    }
}
