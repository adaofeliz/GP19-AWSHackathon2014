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
        "Term-array"
})
public class TermArrayArray {

    @JsonProperty("Term-array")
    private org.gp19.analysis.service.datasource.dto.TermArray TermArray;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The TermArray
     */
    @JsonProperty("Term-array")
    public org.gp19.analysis.service.datasource.dto.TermArray getTermArray() {
        return TermArray;
    }

    /**
     * @param TermArray The Term-array
     */
    @JsonProperty("Term-array")
    public void setTermArray(org.gp19.analysis.service.datasource.dto.TermArray TermArray) {
        this.TermArray = TermArray;
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