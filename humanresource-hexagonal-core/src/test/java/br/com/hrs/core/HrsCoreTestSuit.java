package br.com.hrs.core;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        "br.com.hrs.core.usecase"
})
@SuiteDisplayName("HRS Core Suite Test")
public class HrsCoreTestSuit {

}