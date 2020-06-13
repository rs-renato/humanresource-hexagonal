package br.com.hrs.core;


import br.com.hrs.core.config.annotation.EnableHrsCore;
import org.springframework.context.annotation.Configuration;


@Configuration
/*@ComponentScan({
        "br.com.hrs.core.repository",
        "br.com.hrs.core.usecase",
        "br.com.hrs.core.validator",
})*/
@EnableHrsCore(mockRepository = true)
public class HrsBuildConfiguration {
    // Development Build : Unit Test  -> Core <-  Mocks
}