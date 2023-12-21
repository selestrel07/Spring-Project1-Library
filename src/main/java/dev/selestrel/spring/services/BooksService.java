package dev.selestrel.spring.services;

import dev.selestrel.spring.models.Book;
import dev.selestrel.spring.models.Person;
import dev.selestrel.spring.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAll(Integer page, Integer booksPerPage, Boolean sortByDate) {
        PageRequest pageRequest;
        Sort sort = sortByDate != null && sortByDate ? Sort.by("year") : null;
        List<Book> books;
        if (page != null && booksPerPage != null) {
            pageRequest = PageRequest.of(page, booksPerPage);
            pageRequest = sort != null
                    ? pageRequest.withSort(sort)
                    : pageRequest;
            books = booksRepository.findAll(pageRequest).getContent();
        } else if (sort != null) {
            books = booksRepository.findAll(sort);
        } else {
            books = booksRepository.findAll();
        }

        return books;
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = findOne(id);
        if (book != null) {
            book.setOwner(person);
        }
    }

    @Transactional
    public void release(int id) {
        Book book = findOne(id);
        book.setOwner(null);
    }
}
