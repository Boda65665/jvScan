package org.example.jvscan.Configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.example.jvscan")
public class JvScanApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvScanApplication.class, args);
    }

}
