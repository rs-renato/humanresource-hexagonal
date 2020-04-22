package br.com.hrs.core;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        // business test (services)
        "br.com.hrs.core.service"
})
public class CoreTestSuit {

}