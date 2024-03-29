package com.libraryapp.rest;

import com.libraryapp.entities.Book;
import com.libraryapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> listeLivres() {
        List<Book> livres = bookService.findAll();
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLivre(@RequestBody Book livre) {
        try {
            bookService.save(livre);
            return new ResponseEntity<>("Livre créé avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la création du livre", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getLivre(@PathVariable Long bookId) {
        Book livre = bookService.findById(bookId);
        if (livre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livre, HttpStatus.OK);
    }

    @PutMapping("/edit/{bookId}")
    public ResponseEntity<?> editLivre(@PathVariable Long bookId, @RequestBody Book livre) {
        Book existingLivre = bookService.findById(bookId);
        if (existingLivre == null) {
            return new ResponseEntity<>("Livre non trouvé", HttpStatus.NOT_FOUND);
        }
        try {
            existingLivre.setTitle(livre.getTitle());
            existingLivre.setAuthor(livre.getAuthor());
            existingLivre.setReleaseYear(livre.getReleaseYear());
            existingLivre.setEdition(livre.getEdition());
            existingLivre.setReturnDate(livre.getReturnDate());
            existingLivre.setStartReservationDate(livre.getStartReservationDate());
            existingLivre.setEndReservationDate(livre.getEndReservationDate());
            existingLivre.setTimesExtended(livre.getTimesExtended());
            existingLivre.setReadyForPickup(livre.getReadyForPickUp());
            existingLivre.setReservedByUser(livre.getReservedByUser());
            existingLivre.setTheUser(livre.getTheUser());

            Book updatedLivre = bookService.save(existingLivre);
            return new ResponseEntity<>(updatedLivre, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la mise à jour du livre", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<?> deleteLivre(@PathVariable Long bookId) {
        Book livre = bookService.findById(bookId);
        if (livre == null) {
            return new ResponseEntity<>("Livre non trouvé", HttpStatus.NOT_FOUND);
        }
        bookService.deleteById(bookId);
        return new ResponseEntity<>("Livre supprimé avec succès", HttpStatus.NO_CONTENT);
    }
}
