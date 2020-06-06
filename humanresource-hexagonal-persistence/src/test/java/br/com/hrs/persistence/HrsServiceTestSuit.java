package br.com.hrs.persistence;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        // Jdbc/JPA Repositories Test
        "br.com.hrs.persistence.repository.jdbc",
        "br.com.hrs.persistence.repository.jpa",
})
public class HrsServiceTestSuit{

}