package org.gp19.analysis.test;

import org.gp19.analysis.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created on 06/11/14.
 */

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class) // Web Application base config file
public class AppInitializationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext context;

    @Test(description = "This test will test if the IoC framework is loading without errors.")
    public void testSpringApplicationContextLoad() throws Exception {
        Assert.assertNotNull(context);
    }
}
