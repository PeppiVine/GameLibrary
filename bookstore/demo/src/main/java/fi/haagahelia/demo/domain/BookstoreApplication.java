package fi.haagahelia.demo.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import fi.haagahelia.demo.domain.Book;
import fi.haagahelia.demo.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repo, CategoryRepository categoryRepo) {
        return (args) -> {
            // Kategoriat
            Category c1 = categoryRepo.save(new Category("Fiction"));
            Category c2 = categoryRepo.save(new Category("Action"));
            Category c3 = categoryRepo.save(new Category("Fantasy"));

            repo.save(new Book("Kirjan nimi 1", "Kirjailija 1", "1234", 2026));
            repo.save(new Book("Kirjan nimi 2", "Kirjailija 2", "1235", 2025));

        };
    }
}
