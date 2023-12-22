package dev.selestrel.spring.repositories;

import dev.selestrel.spring.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByNameStartsWith(String startString);
}
