package com.person;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 启动程序
 *
 * @author sk
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class PersonalApplication implements ApplicationRunner
{
    public static void main(String[] args)
    {
        SpringApplication.run(PersonalApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/start.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
