package ru.laba.mongomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laba.mongomvc.models.Authors;
import ru.laba.mongomvc.models.Books;
import ru.laba.mongomvc.repos.BookRepos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class AuthorController {
    @Autowired
    private BookRepos bookRepos;

    @GetMapping("/authors/all")
    public ResponseEntity<List<Authors>> getAllAuthors() {
        List<Authors> authors = bookRepos.findAll().stream()
                .map(Books::getAuthor)
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{bookId}/authors")
    public ResponseEntity<Authors> getBookAuthor(@PathVariable String bookId) {
        Optional<Books> book = bookRepos.findById(bookId);
        return book.map(b -> ResponseEntity.ok(b.getAuthor())).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{bookId}/authors/{authorId}")
//    public ResponseEntity<Void> deleteAuthor(@PathVariable String bookId, @PathVariable String authorId) {
//        Optional<Books> book = bookRepos.findById(bookId);
//        if (book.isPresent() && book.get().getAuthor().getId().equals(authorId)) {
//            bookRepos.deleteById(bookId);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
