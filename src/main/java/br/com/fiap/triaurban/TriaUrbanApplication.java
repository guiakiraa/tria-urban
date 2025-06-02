package br.com.fiap.triaurban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableCaching
@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class TriaUrbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriaUrbanApplication.class, args);
    }

}
