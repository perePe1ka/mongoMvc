package ru.laba.mongomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laba.mongomvc.models.AggregationResult;
import ru.laba.mongomvc.models.Books;
import ru.laba.mongomvc.repos.BookRepos;
import ru.laba.mongomvc.services.AggregationService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepos bookRepos;

    @Autowired
    private AggregationService aggregationService;

    @GetMapping
    public List<Books> getAllBooks() {
        return bookRepos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable String id) {
        Optional<Books> book = bookRepos.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        Books createdBook = bookRepos.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable String id, @RequestBody Books updatedBook) {
        Optional<Books> existingBook = bookRepos.findById(id);

        if (existingBook.isPresent()) {
            updatedBook.setId(id);
            Books savedBook = bookRepos.save(updatedBook);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        Optional<Books> existingBook = bookRepos.findById(id);

        if (existingBook.isPresent()) {
            bookRepos.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/performAggregation")
    public List<AggregationResult> performAggregation() {
        return aggregationService.performAggregation();
    }

}
