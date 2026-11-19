package com.example.tourists.config;

import com.example.tourists.model.Tourist;
import com.example.tourists.repository.TouristRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seedTourists(TouristRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Tourist("Aarav", "Sharma", "aarav@example.com", "+91-9000000001", "India", "Hyderabad"));
                repository.save(new Tourist("Mia", "Johnson", "mia@example.com", "+1-555-0102", "USA", "New York"));
                repository.save(new Tourist("Liam", "Chen", "liam@example.com", "+65-555-0103", "Singapore", "Singapore"));
            }
        };
    }
}
