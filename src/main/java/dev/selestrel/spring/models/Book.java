package dev.selestrel.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;
    private int personId;
    @NotNull(message = "Name should not be null")
    @Size(min = 2, max = 100, message = "Name length should be between 2 and 100")
    private String name;
    @NotNull(message = "Author name should not be null")
    @Size(min = 2, max = 200, message = "Author name length should be between 2 and 200 characters")
    private String author;
    @Max(value = 2024, message = "Adding book from future not allowed")
    private int year;

    public Book(int id, int personId, String name, String author, int year) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
