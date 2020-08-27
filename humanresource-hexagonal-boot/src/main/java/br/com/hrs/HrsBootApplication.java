package br.com.hrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("br.com.hrs.boot")
public class HrsBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrsBootApplication.class, args);
    }
}
