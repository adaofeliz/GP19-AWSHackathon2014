package org.gp19.analysis.service.dto;

import java.util.HashSet;

/**
 * Created on 11/11/14.
 */
public class WebStatusDto {

    public HashSet<OptionDto> options;
    public HashSet<DataSourceDto> sources;
    public HashSet<TermDto> activeTerms;
    public HashSet<TermDto> inactiveTerms;

}
