package com.test.technical;

import com.test.technical.config.BaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@EnableScheduling
@Import({ BaseConfiguration.class })
@SpringBootApplication(scanBasePackages = { "com.test.technical" })
public class BaseApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("es", "EC"));
        SpringApplication.run(BaseApplication.class, args);  // ✅ CORREGIDO
    }
}
