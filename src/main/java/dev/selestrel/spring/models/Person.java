package dev.selestrel.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {

    private int id;

    @Size(min = 2, max = 200, message = "Name length should be between 2 and 200 characters")
    private String name;

    @NotNull(message = "Year of birth should not be empty")
    @Min(value = 1900, message = "Year of birth should not be before 1900")
    @Max(value = 2017, message = "Year of birth should not be after 2017 as we don't give books to person under 6")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String name, int yearOfBirth) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
