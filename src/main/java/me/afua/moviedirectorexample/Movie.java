package me.afua.moviedirectorexample;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @NotEmpty()
    private String title;

    private long year;

    @Lob
    private String description;

    //Set up the relationship = many movies have ONE director
    @ManyToOne()
    private Director theDirector;
    //Note: The Director is an individual object here


    public Movie() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Director getTheDirector() {
        return theDirector;
    }

    public void setTheDirector(Director theDirector) {
        this.theDirector = theDirector;
    }
}
