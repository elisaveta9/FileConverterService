package model;

import java.util.ArrayList;

public class Film {
    private String name;
    private int productionYear;
    private String director;
    private ArrayList<String> screenwriters = new ArrayList<>();
    private ArrayList<String> producers = new ArrayList<>();
    private ArrayList<String> genres = new ArrayList<>();

    public Film() {
    }

    public Film(String name, int productionYear, String director, ArrayList<String> screenwriters, ArrayList<String> producers, ArrayList<String> genres) {
        this.name = name;
        this.productionYear = productionYear;
        this.director = director;
        this.screenwriters = screenwriters;
        this.producers = producers;
        this.genres = genres;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Название фильма: " + name +
                "\nГод выпуска: " + productionYear +
                "\nРежиссер=" + director +
                "\nСценаристы:\n");
        for (String screenwriter : screenwriters)
            result.append("\t").append(screenwriter).append("\n");
        result.append("Продюссеры:\n");
        for (String producer : producers)
            result.append("\t").append(producer).append("\n");
        result.append("Жанры:\n");
        for (String genre : genres)
            result.append("\t").append(genre).append("\n");
        return result.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getScreenwriters() {
        return screenwriters;
    }

    public void addScreenwriter(String name){
        screenwriters.add(name);
    }

    public ArrayList<String> getProducers() {
        return producers;
    }

    public void addProducer(String producer){
        producers.add(producer);
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void addGenre(String genre){
        genres.add(genre);
    }
}
