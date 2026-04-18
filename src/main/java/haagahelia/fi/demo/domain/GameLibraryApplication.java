package haagahelia.fi.demo.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import haagahelia.fi.demo.domain.*;

@SpringBootApplication
public class GameLibraryApplication {
        private final GameRepository gameRepository;

        GameLibraryApplication(GameRepository gameRepository) {
                this.gameRepository = gameRepository;
        }

        public static void main(String[] args) {
                SpringApplication.run(GameLibraryApplication.class, args);
        }

        @Bean
        public CommandLineRunner demo(GameRepository repo,
                        AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
                return (args) -> {
                        if (gameRepository.count() == 0) {

                                repo.save(new Game("Life is Strange", 2015, "Adventure", "Dontnod Entertaiment", 10,
                                                ""));
                                repo.save(new Game("Life is Strange 2", 2018, "Adventure", "DontnodEntertaiment", 8,
                                                ""));
                                repo.save(new Game("Red dead redemption ", 2010, "Action-adventure", "Rockstar", 9,
                                                ""));
                                repo.save(new Game("Red dead redemption 2", 2018, "Action-adventure", "Rockstar", 11,
                                                ""));
                                repo.save(new Game("Gta 5 ", 2013, "Action- adventure", "Rockstar", 10, ""));
                                repo.save(new Game("Kindom Come Deliverence 2 ", 2025, "Action-role",
                                                "Warhorse Studios", 11, ""));
                                repo.save(new Game("God of war ", 2018, "Action-adventure", "Santa MonicaStudios", 10,
                                                ""));
                                repo.save(new Game("God if war Ragnarök ", 2022, "Action-adventure",
                                                "Santa Monica Studio", 10, ""));
                                repo.save(new Game("Stray ", 2022, "Indie Action-adventure", "BlueTwelwe", 9,
                                                ""));
                                repo.save(new Game("Blue prince ", 2025, "puzzle-game", "Dogubomb", 8, ""));
                                repo.save(new Game("It takes two", 2021, "Co-op", "Hazelight Studios", 10,
                                                ""));
                                repo.save(new Game("Split Fiction", 2025, "Co-op", "Hazelight Studios", 10,
                                                ""));
                                repo.save(new Game("Skyrim", 2011, "Action-role", "Bethesda Gaming Studios",
                                                11, ""));
                                repo.save(new Game("The Witcher 3", 2015, "Action-role", "CD Projekt Red",
                                                10, ""));
                                repo.save(new Game("Cyberpunk 2077", 2022, "Action-role", "CD Projekt Red",
                                                10, ""));
                        }

                        if (appUserRepository.findByUsername("user").isEmpty()) {
                                appUserRepository.save(new AppUser(
                                                "user", passwordEncoder.encode("user"), "user@user.fi", "USER"));
                        }

                        if (appUserRepository.findByUsername("admin").isEmpty()) {
                                appUserRepository.save(new AppUser(
                                                "admin", passwordEncoder.encode("admin"), "admin@admin.fi", "ADMIN"));
                        }

                };
        }
}
