package com.cursor.library.controllers;

import com.cursor.library.models.Book;
import com.cursor.library.models.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

public class BookControllerTest extends BaseControllerTest {

    @Test
    public void createBookTest() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setName("Cool createBookDto");
        bookDto.setDescription("Cool description");
        bookDto.setNumberOfWords(100500);
        bookDto.setRating(10);
        bookDto.setYearOfPublication(2020);
        bookDto.setAuthors(Arrays.asList("author1", "author2"));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsString(bookDto));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Book book = OBJECT_MAPPER.readValue(
                result.getResponse().getContentAsString(),
                Book.class
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getBookId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
