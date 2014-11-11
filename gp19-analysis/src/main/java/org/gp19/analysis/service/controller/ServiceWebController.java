package org.gp19.analysis.service.controller;

import org.gp19.analysis.service.delegate.TermsDelegate;
import org.gp19.analysis.service.dto.DataSourceDto;
import org.gp19.analysis.service.dto.OptionDto;
import org.gp19.analysis.service.dto.WebStatusDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
}
