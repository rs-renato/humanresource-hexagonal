package br.com.hrs.boot.config;

import br.com.hrs.core.config.annotation.EnableHrsCore;
import br.com.hrs.persistence.config.annotation.EnableHrsPersistence;
import br.com.hrs.persistence.config.annotation.PersistenceType;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHrsCore
@EnableHrsPersistence(type = PersistenceType.JPA)
public class HrsBuildConfiguration {

}