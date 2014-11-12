package org.gp19.analysis.service.controller;

import org.gp19.analysis.service.delegate.TermsDelegate;
import org.gp19.analysis.service.dto.DataSourceDto;
import org.gp19.analysis.service.dto.OptionDto;
import org.gp19.analysis.service.dto.TermDto;
import org.gp19.analysis.service.dto.WebStatusDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 11/11/14.
 */

@RestController
@RequestMapping("/*")
public class ServiceWebController {

    private static final String COOKIE_WEB_OPTIONS = "gp19-web-options";
    private static final String COOKIE_WEB_SOURCES = "gp19-web-sources";

    @Resource
    private TermsDelegate termsDelegate;

    @RequestMapping(value = "/web-status", method = RequestMethod.GET)
    public WebStatusDto getWebStatus(HttpServletRequest httpServletRequest)
            throws Exception {

        WebStatusDto webStatusDto = new WebStatusDto();

        // Retrieve Session Options
        Object sessionOptions = httpServletRequest.getSession().getAttribute(COOKIE_WEB_OPTIONS);
        if (sessionOptions != null && sessionOptions instanceof List) {
            webStatusDto.options = (List<OptionDto>) sessionOptions;
        }

        // Retrieve Session Sources
        Object sessionSources = httpServletRequest.getSession().getAttribute(COOKIE_WEB_SOURCES);
        if (sessionSources != null && sessionOptions instanceof List) {
            webStatusDto.sources = (List<DataSourceDto>) sessionSources;
        }

        return webStatusDto;
    }

    @RequestMapping(value = "/web-status", method = RequestMethod.POST)
    public WebStatusDto updateWebStatus(@RequestBody WebStatusDto webStatusDto, HttpServletRequest httpServletRequest)
            throws Exception {

        WebStatusDto newWebStatusDto = new WebStatusDto();

        // Active Terms
        newWebStatusDto.activeTerms =
                termsDelegate.retrieveActiveTerms(
                        webStatusDto.activeTerms,
                        webStatusDto.inactiveTerms,
                        webStatusDto.sources,
                        webStatusDto.options);

        // Inactive Terms
        newWebStatusDto.inactiveTerms = webStatusDto.inactiveTerms;

        // User Options
        newWebStatusDto.options = webStatusDto.options;
        httpServletRequest.getSession().setAttribute(COOKIE_WEB_OPTIONS, newWebStatusDto.options); // Saving Options

        // User Options
        newWebStatusDto.sources = webStatusDto.sources;
        httpServletRequest.getSession().setAttribute(COOKIE_WEB_SOURCES, newWebStatusDto.sources); // Saving Sources

        return newWebStatusDto;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MOCK DATA! - NOT FOR PRODUCTION /////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/web-status/mock", method = RequestMethod.GET)
    public WebStatusDto getWebStatusMock()
            throws Exception {

        WebStatusDto webStatusDto = new WebStatusDto();

        ArrayList<TermDto> activeTerms = new ArrayList<>();

        // Active Terms
        TermDto term1 = new TermDto();
        term1.label = "ebola";
        term1.source = "user";
        activeTerms.add(term1);
        TermDto term2 = new TermDto();
        term2.label = "virus";
        term2.source = "user";
        activeTerms.add(term2);
        TermDto term3 = new TermDto();
        term3.label = "fever";
        term3.source = "computed";
        activeTerms.add(term3);

        webStatusDto.activeTerms = activeTerms;

        ArrayList<TermDto> inactiveTerms = new ArrayList<>();

        // Inactive Terms
        TermDto term4 = new TermDto();
        term4.label = "nurse";
        term4.source = "computed";
        inactiveTerms.add(term4);
        TermDto term5 = new TermDto();
        term5.label = "virus";
        term5.source = "computed";
        inactiveTerms.add(term5);
        TermDto term6 = new TermDto();
        term6.label = "fever";
        term6.source = "computed";
        inactiveTerms.add(term6);

        webStatusDto.inactiveTerms = inactiveTerms;

        // Sources
        ArrayList<DataSourceDto> dataSourceDtos = new ArrayList<>();

        DataSourceDto dataSourceDto1 = new DataSourceDto();
        dataSourceDto1.id = 1L;
        dataSourceDto1.label = "Twitter";
        dataSourceDto1.enabled = Boolean.FALSE;
        dataSourceDtos.add(dataSourceDto1);

        DataSourceDto dataSourceDto2 = new DataSourceDto();
        dataSourceDto2.id = 2L;
        dataSourceDto2.label = "Google";
        dataSourceDto2.enabled = Boolean.TRUE;
        dataSourceDtos.add(dataSourceDto2);

        webStatusDto.sources = dataSourceDtos;

        // Options

        ArrayList<OptionDto> optionDtos = new ArrayList<>();

        OptionDto optionDto1 = new OptionDto();
        optionDto1.id = 1L;
        optionDto1.label = "Algorithm A";
        optionDto1.enabled = Boolean.TRUE;
        optionDtos.add(optionDto1);
        OptionDto optionDto2 = new OptionDto();
        optionDto2.id = 2L;
        optionDto2.label = "Option B";
        optionDto2.enabled = Boolean.FALSE;
        optionDtos.add(optionDto2);

        webStatusDto.options = optionDtos;

        return webStatusDto;
    }

    @RequestMapping(value = "/web-status/error/mock", method = RequestMethod.GET)
    public WebStatusDto getWebStatusError()
            throws Exception {
        throw new Exception("This is an Mock Error");
    }
}
