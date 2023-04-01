package model;

import java.util.ArrayList;

public class Director {
    String name;
    ArrayList<Film> films = new ArrayList<>();

    public Director(String name, Film film){
        this.name = name;
        this.films.add(film);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film){
        this.films.add(film);
    }

}
