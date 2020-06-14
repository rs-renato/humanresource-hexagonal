package br.com.hrs.boot.config;

import br.com.hrs.core.config.annotation.EnableHrsCore;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHrsCore(loadMockRepository = true)
//@EnableHrsPersistence(type = PersistenceType.JDBC)
public class HrsBuildConfiguration {

//    @Import(HrsJpaConfiguration.class)
//    @ComponentScan("br.com.hrs.core.repository")
//    public class PersistenceConfiguration{}

//    public class CoreConfiguration{}
}