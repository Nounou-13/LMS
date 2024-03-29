package com.libraryapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.libraryapp.DAO.BookRepository;
import com.libraryapp.entities.Book;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testFindAll() {
        Book book1 = new Book("Title1", "Author1", 2000, 1);
        Book book2 = new Book("Title2", "Author2", 2001, 2);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> foundBooks = bookService.findAll();

        assertEquals(2, foundBooks.size());
        assertEquals("Title1", foundBooks.get(0).getTitle());
        assertEquals("Author1", foundBooks.get(0).getAuthor());
        assertEquals(2000, foundBooks.get(0).getReleaseYear());
        assertEquals(1, foundBooks.get(0).getEdition());
        assertEquals("Title2", foundBooks.get(1).getTitle());
        assertEquals("Author2", foundBooks.get(1).getAuthor());
        assertEquals(2001, foundBooks.get(1).getReleaseYear());
        assertEquals(2, foundBooks.get(1).getEdition());
    }
}
