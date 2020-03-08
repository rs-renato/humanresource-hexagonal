package br.com.hrs.core;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        // business test (services)
        "br.com.hrs.core.business.service",
        // model tests (domain)
        "br.com.hrs.core.model",
        // use case tests (inbound adapter)
        "br.com.hrs.core.port.inbound"
})
public class CoreTestSuit {

}
