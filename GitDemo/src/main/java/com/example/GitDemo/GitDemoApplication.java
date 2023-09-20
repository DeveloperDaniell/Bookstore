package com.example.GitDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.example.GitDemo.domain.Book;
import com.example.GitDemo.domain.BookRepository;

@SpringBootApplication
public class GitDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitDemoApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner bookDemo(BookRepository repository) {
        return (args) -> {
            
            repository.save(new Book("A Tale of Two Cities", "Charles Dickens", 1859, "123456789", 10.5));
            repository.save(new Book("Moby Dick", "Herman Melville", 1851, "987654321", 12.5));
            
            // Tulosta kaikki kirjat (vain testaamista varten)
            for (Book book : repository.findAll()) {
                System.out.println(book);
            }
        };
    }
}
