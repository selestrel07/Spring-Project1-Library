package dev.selestrel.spring.controllers;

import dev.selestrel.spring.models.Book;
import dev.selestrel.spring.models.Person;
import dev.selestrel.spring.services.BooksService;
import dev.selestrel.spring.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(@RequestParam(name = "page", required = false) Integer page,
                        @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(name = "sort_by_year", required = false) Boolean sortByYear, Model model) {


        model.addAttribute("books", booksService.findAll(page, booksPerPage, sortByYear));

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        if (book.getOwner() != null) {
            model.addAttribute("person", book.getOwner());
        } else {
            model.addAttribute("person", new Person());
            model.addAttribute("people", peopleService.findAll());
        }

        return "books/show";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.assign(id, person);

        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/unassign")
    public String unassign(@PathVariable("id") int id) {
        booksService.release(id);

        return "redirect:/books/" + id;
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        booksService.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);

        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBook() {
        return "/books/search";
    }

    @PostMapping("/search")
    public String searchBook(@RequestParam(value = "title") String title, Model model) {
        model.addAttribute("books", booksService.findAllByTitleStartString(title));
        return "books/search";
    }
}
