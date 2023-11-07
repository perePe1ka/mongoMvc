package ru.laba.mongomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laba.mongomvc.models.Books;
import ru.laba.mongomvc.models.Genres;
import ru.laba.mongomvc.repos.BookRepos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/books")
public class GenresController {

    private BookRepos bookRepos;

    @Autowired
    public GenresController(BookRepos bookRepos) {
        this.bookRepos = bookRepos;
    }

    @GetMapping("/{bookId}/genres")
    public ResponseEntity<Genres> getBookGenre(@PathVariable String bookId) {
        Optional<Books> book = bookRepos.findById(bookId);
        return book.map(b -> ResponseEntity.ok(b.getGenre())).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/genres/all")
    public ResponseEntity<List<Genres>> getAllGenres() {
        List<Genres> genres = bookRepos.findAll().stream()
                .map(Books::getGenre)
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(genres);
    }


    @DeleteMapping("/{bookId}/genres/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable String bookId, @PathVariable String genreId) {
        Optional<Books> book = bookRepos.findById(bookId);
        if (book.isPresent() && book.get().getGenre().getId().equals(genreId)) {
            bookRepos.deleteById(bookId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}




