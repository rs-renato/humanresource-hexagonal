package br.com.hrs.service;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        // Jdbc/JPA Repositories Test
        "br.com.hrs.service.repository.jdbc",
        "br.com.hrs.service.repository.jpa",
})
public class HrsServiceTestSuit{

}