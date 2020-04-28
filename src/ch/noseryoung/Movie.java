package ch.noseryoung;

import java.sql.Time;

public class Movie {
    String name;
    String genre;
    String director;
    Time play_time;

    public Movie(String name, String genre, String director, Time play_time) {
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.play_time = play_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Time getPlay_time() {
        return play_time;
    }

    public void setPlay_time(Time play_time) {
        this.play_time = play_time;
    }
}
