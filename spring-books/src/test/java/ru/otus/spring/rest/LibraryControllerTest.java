package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring.service.LibraryService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.spring.rest.LibraryController.DEFAULT_BOOK;

@WebMvcTest
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Test
    void listBooks() throws Exception {
        given(libraryService.getAllBooks())
                .willReturn(List.of(DEFAULT_BOOK));
        MvcResult mvcResult = mockMvc.perform(get("/books")).andExpect(status().isOk()).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();

        assertThat(responseBody).contains("<td>" + DEFAULT_BOOK.getTitle() + "</td>");

    }

    @Test
    void handleDeleteUser() throws Exception {
        mockMvc.perform(get("/delete_book?id=1"));
        verify(libraryService, times(1)).deleteBookById(1L);
    }

    @Test
    void addBook() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/add_book")).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).contains("<input id=\"title-input\" name=\"title\" type=\"text\" value=\"" + DEFAULT_BOOK.getTitle() + "\"/>");
        assertThat(content).contains("<input id=\"author-input\" name=\"author\" type=\"text\" value=\"" + DEFAULT_BOOK.getAuthor().getId() + "\"/>");
        assertThat(content).contains("<input id=\"genre-input\" name=\"genre\" type=\"text\" value=\"" + DEFAULT_BOOK.getGenre().getId() + "\"/>");
    }

    @Test
    void editBook() throws Exception {
        given(libraryService.getBookById(Mockito.anyLong()))
                .willReturn(Optional.of(DEFAULT_BOOK));
        MvcResult mvcResult = mockMvc.perform(get("/edit_book?id=1")).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).contains("<input id=\"title-input\" name=\"title\" type=\"text\" value=\"" + DEFAULT_BOOK.getTitle() + "\"/>");
        assertThat(content).contains("<input id=\"author-input\" name=\"author\" type=\"text\" value=\"" + DEFAULT_BOOK.getAuthor().getId() + "\"/>");
        assertThat(content).contains("<input id=\"genre-input\" name=\"genre\" type=\"text\" value=\"" + DEFAULT_BOOK.getGenre().getId() + "\"/>");
    }

    @Test
    void saveBook() throws Exception {
// setting fields for the NewObject
        Mockito.when(libraryService.saveBook(Mockito.anyLong(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(DEFAULT_BOOK);

        mockMvc.perform(MockMvcRequestBuilders.post("/edit_book")
                .content(asJsonString(DEFAULT_BOOK))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/books"));

        verify(libraryService, times(1)).saveBook(DEFAULT_BOOK.getId(), DEFAULT_BOOK.getTitle(), DEFAULT_BOOK.getAuthor().getId(), DEFAULT_BOOK.getGenre().getId());
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}