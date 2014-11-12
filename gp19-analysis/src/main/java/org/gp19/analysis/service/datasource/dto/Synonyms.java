package org.gp19.analysis.service.datasource.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "Term-array-array"
})
public class Synonyms {

    @JsonProperty("Term-array-array")
    private org.gp19.analysis.service.datasource.dto.TermArrayArray TermArrayArray;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The TermArrayArray
     */
    @JsonProperty("Term-array-array")
    public org.gp19.analysis.service.datasource.dto.TermArrayArray getTermArrayArray() {
        return TermArrayArray;
    }

    /**
     * @param TermArrayArray The Term-array-array
     */
    @JsonProperty("Term-array-array")
    public void setTermArrayArray(org.gp19.analysis.service.datasource.dto.TermArrayArray TermArrayArray) {
        this.TermArrayArray = TermArrayArray;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}