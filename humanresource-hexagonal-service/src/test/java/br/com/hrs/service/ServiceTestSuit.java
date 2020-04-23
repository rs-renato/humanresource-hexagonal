package br.com.hrs.service;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        // business test (services)
        "br.com.hrs.service.repository"
})
public class ServiceTestSuit {

}