package fi.haagahelia.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import fi.haagahelia.demo.domain.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = fi.haagahelia.demo.domain.BookstoreApplication.class)
@AutoConfigureMockMvc

public class BookControllerSmokeTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void loginPageShouldReturnOk() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void indexShouldRedirectToLoginWhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    void indexShouldReturnOkForLoggedUser() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    void booklistShouldReturnOkForLoggedUser() throws Exception {
        mockMvc.perform(get("/booklist"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    void addPageShouldReturnOkForLoggedUser() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    void deleteShouldReturnOkForLoggedUser() throws Exception {
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    void deleteShouldRedirectForAdmin() throws Exception {
        Book toRemove = new Book("ToDelete", "Author", "DEL-1", 2026);
        toRemove.setCategory(categoryRepository.findById(1L).orElseThrow());
        long id = bookRepository.save(toRemove).getId();

        mockMvc.perform(get("/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    void editPageShouldReturnOkForLoggedUser() throws Exception {
        mockMvc.perform(get("/edit/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    void saveNewBookShouldRedirectToIndex() throws Exception {
        mockMvc.perform(post("/save")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("title", "Smoke Test Book")
                        .param("author", "Tester")
                        .param("isbn", "ST-999")
                        .param("publicationYear", "2026")
                        .param("category.id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

}
