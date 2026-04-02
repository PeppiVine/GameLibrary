package fi.haagahelia.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fi.haagahelia.demo.domain.BookController;
import fi.haagahelia.demo.domain.BookRestController;

@SpringBootTest(classes = fi.haagahelia.demo.domain.BookstoreApplication.class)

public class BookStoreApplicationTests {
    @Autowired
    private BookController bookController;

    @Autowired
    private BookRestController bookRestController;

    @Test
    void contextLoads() {
        assertThat(bookController).isNotNull();
        assertThat(bookRestController).isNotNull();
    }
}
