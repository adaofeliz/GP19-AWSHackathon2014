package org.gp19.analysis.service.delegate;

import org.gp19.analysis.service.datasource.SynonymsDataSource;
import org.gp19.analysis.service.dto.DataSourceDto;
import org.gp19.analysis.service.dto.OptionDto;
import org.gp19.analysis.service.dto.TermDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;

/**
 * Created on 11/11/14.
 */

@Service
public class AnalysisServiceDelegate {

    @Resource
    private SynonymsDataSource synonymsDataSource;

    // Data Sources
    private HashSet<DataSourceDto> dataSourceDtos;

    // Valid Data Sources
    private DataSourceDto synonymsSource;

    // Options
    private HashSet<OptionDto> optionDtos;

    // Valid Options
    // TODO

    public AnalysisServiceDelegate() {

        // Sources
        dataSourceDtos = new HashSet<>();

        synonymsSource = new DataSourceDto();
        synonymsSource.enabled = true;
        synonymsSource.label = "Synonyms";
        dataSourceDtos.add(synonymsSource);

        // Options
        optionDtos = new HashSet<OptionDto>();
    }


    public HashSet<TermDto> retrieveActiveTerms(
            HashSet<TermDto> activeTerms,
            HashSet<TermDto> inactiveTerms,
            HashSet<DataSourceDto> sources,
            HashSet<OptionDto> options) {

        HashSet<TermDto> termDtos = new HashSet<>();

        // Check the requested Sources
        if (sources.contains(synonymsSource)) {
            termDtos.addAll(synonymsDataSource.getValidTerms(activeTerms, inactiveTerms));
        }

        // TODO Check the requested Options

        return termDtos;
    }

    public HashSet<OptionDto> getServiceOptions() {
        return this.optionDtos;
    }

    public HashSet<DataSourceDto> getServiceSources() {
        return this.dataSourceDtos;
    }
}
