package org.gp19.analysis.service.dto;

/**
 * Created on 18/10/14.
 */
public class ErrorDto {

    public String error;
    public String status;
    public int code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorDto)) return false;

        ErrorDto errorDto = (ErrorDto) o;

        if (code != errorDto.code) return false;
        if (!error.equals(errorDto.error)) return false;
        if (!status.equals(errorDto.status)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = error.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + code;
        return result;
    }
}
