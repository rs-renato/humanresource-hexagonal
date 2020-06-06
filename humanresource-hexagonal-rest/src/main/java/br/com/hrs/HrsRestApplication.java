package br.com.hrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("br.com.hrs.rest")
public class HrsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrsRestApplication.class, args);
    }
}
