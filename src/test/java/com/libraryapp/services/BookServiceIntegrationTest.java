package com.libraryapp.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.libraryapp.entities.Book;
import com.libraryapp.DAO.BookRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test") // Utilise le profil de test
public class BookServiceIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void testFindAll() {
        // Préparation des données
        Book book1 = new Book();
        book1.setTitle("Book 1");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        bookRepository.save(book2);

        // Exécution du test
        List<Book> books = bookService.findAll();

        // Vérification des résultats
        assertNotNull(books);
        assertTrue(books.size() >= 2);
    }
}
