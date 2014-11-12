package org.gp19.analysis.service.datasource;

import org.gp19.analysis.service.datasource.dto.Synonyms;
import org.gp19.analysis.service.dto.TermDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created on 11/11/14.
 */

@Component
public class SynonymsDataSource {

    @Resource
    private RestTemplate restTemplate;

    private static String SYNONYMS_URL = "http://watson.kmi.open.ac.uk/API/term/synonyms?term=";

    public HashSet<TermDto> getValidTerms(HashSet<TermDto> activeTerms, HashSet<TermDto> inactiveTerms) {
        HashSet<TermDto> validTerms = new HashSet<>();


        for (TermDto termDto : activeTerms) {

            try {
                // Prepare acceptable media type
                List<MediaType> acceptableMediaTypes = new ArrayList<>();
                acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

                // Prepare header
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(acceptableMediaTypes);
                HttpEntity<String> entity = new HttpEntity<>(headers);

                // Send the request as GET
                ResponseEntity<Synonyms> synonymsDataResponseEntity =
                        restTemplate.exchange(SYNONYMS_URL + termDto.label, HttpMethod.GET, entity, Synonyms.class);

                Synonyms synonyms = synonymsDataResponseEntity.getBody();

                if (synonyms != null
                        && synonyms.getTermArrayArray() != null
                        && synonyms.getTermArrayArray().getTermArray() != null
                        && synonyms.getTermArrayArray().getTermArray().getTerm() != null) {
                    for (String term : synonyms.getTermArrayArray().getTermArray().getTerm()) {
                        TermDto newTerm = new TermDto();
                        newTerm.label = term;
                        newTerm.source = "synonyms";
                        validTerms.add(newTerm);

                    }
                }
            } catch (Exception e) {
                // do nothing.
            }
        }
        return validTerms;
    }
}
