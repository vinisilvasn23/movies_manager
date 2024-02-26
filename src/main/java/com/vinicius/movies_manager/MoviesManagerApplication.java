package com.vinicius.movies_manager;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Properties;

@SpringBootApplication
public class MoviesManagerApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();

        SpringApplication app = new SpringApplication(MoviesManagerApplication.class);
        app.setDefaultProperties(getProperties(dotenv));
        app.run(args);
    }

    private static Properties getProperties(Dotenv dotenv) {
        Properties props = new Properties();
        props.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        props.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
        props.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        props.setProperty("secretkey", dotenv.get("SECRET_KEY"));
        return props;
    }
}