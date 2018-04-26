package me.afua.moviedirectorexample;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String fullName;

    @OneToMany(mappedBy = "theDirector")
    Set<Movie> myMovies;

    public Director() {
        myMovies = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Movie> getMyMovies() {
        return myMovies;
    }

    public void setMyMovies(Set<Movie> myMovies) {
        this.myMovies = myMovies;
    }
}
