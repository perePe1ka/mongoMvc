package ru.laba.mongomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laba.mongomvc.models.Books;
import ru.laba.mongomvc.models.Genres;
import ru.laba.mongomvc.repos.BookRepos;
import ru.laba.mongomvc.repos.GenresRepos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/books")
public class GenresController {
    @Autowired
    private BookRepos bookRepos;

    @Autowired
    private GenresRepos genresRepos;
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

//    @PostMapping("/{bookId}")
//    public ResponseEntity<Books> addGenreToBook(@PathVariable String bookId, @RequestBody Genres updatedGenre) {
//        Optional<Books> existingBook = bookRepos.findById(bookId);
//
//        if (existingBook.isPresent()) {
//            Books book = existingBook.get();
//            if (updatedGenre != null && updatedGenre.getId() != null) {
//                book.setGenre(updatedGenre);
//                Books savedBook = bookRepos.save(book);
//                return ResponseEntity.ok(savedBook);
//            } else {
//                return null;
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @GetMapping("/genres")
//    public ResponseEntity<Genres> getAllBookGenre(@PathVariable String bookId) {
//        Optional<Books> book = bookRepos.findById(bookId);
//        return book.map(b -> ResponseEntity.ok(b.getGenre())).orElseGet(() -> ResponseEntity.notFound().build());
//    }


}




