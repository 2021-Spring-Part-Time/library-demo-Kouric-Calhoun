package org.wecancodeit.librarydemo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    private Campus campus;
    @ManyToMany
    private Collection<Author> authors;

    public Long getId() {
        return id;
    }

    public Book(){

    }

    public Book(String title, String descriptions, Campus campus, Author...authors) {
        this.title=title;
        this.description=descriptions;
        this.campus= campus;
        this.authors= new ArrayList<>(Arrays.asList(authors));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
