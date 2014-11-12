package org.gp19.analysis.service.delegate;

import org.gp19.analysis.service.dto.DataSourceDto;
import org.gp19.analysis.service.dto.OptionDto;
import org.gp19.analysis.service.dto.TermDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 11/11/14.
 */

@Service
public class AnalysisServiceDelegate {

    public List<TermDto> retrieveActiveTerms(
            List<TermDto> activeTerms,
            List<TermDto> inactiveTerms,
            List<DataSourceDto> sources,
            List<OptionDto> options) {
        return null;
    }

    public List<OptionDto> getServiceOptions() {
        return null;
    }

    public List<DataSourceDto> getServiceSources() {
        return null;
    }
}
