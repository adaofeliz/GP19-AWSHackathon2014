package org.gp19.analysis.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 11/11/14.
 */

@RestController
@RequestMapping("/*")
public class ServiceWebController {

    @RequestMapping(value = "/web-status", method = RequestMethod.GET)
    public void getWebStatus()
            throws Exception {

    }

    @RequestMapping(value = "/web-status", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateWebStatus()
            throws Exception {
    }
}
