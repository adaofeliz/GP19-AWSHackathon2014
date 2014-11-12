package org.gp19.analysis.service.dto;

/**
 * Created on 11/11/14.
 */
public class TermDto {

    public String label;
    public String source;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TermDto)) return false;

        TermDto termDto = (TermDto) o;

        if (!label.equals(termDto.label)) return false;
        if (!source.equals(termDto.source)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = label.hashCode();
        result = 31 * result + source.hashCode();
        return result;
    }
}
