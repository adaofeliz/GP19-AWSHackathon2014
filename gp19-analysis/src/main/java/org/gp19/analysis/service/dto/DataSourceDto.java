package org.gp19.analysis.service.dto;

/**
 * Created on 11/11/14.
 */
public class DataSourceDto {

    public String label;
    public Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataSourceDto)) return false;

        DataSourceDto that = (DataSourceDto) o;

        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
}
