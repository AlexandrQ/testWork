package com.testWork.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.testWork.utils.dateUtils.LocalDateDeserializer;
import com.testWork.utils.dateUtils.LocalDateSerializer;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate releaseDate;
    private BigInteger budget;
    private BigInteger grossWorldwide;

    public Movie() {}

    public Movie(String name, LocalDate releaseDate, BigInteger budget, BigInteger grossWorldwide) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.grossWorldwide = grossWorldwide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(budget, movie.budget) && Objects.equals(grossWorldwide, movie.grossWorldwide);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", budget=" + budget +
                ", grossWorldwide=" + grossWorldwide +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, budget, grossWorldwide);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigInteger getBudget() {
        return budget;
    }

    public void setBudget(BigInteger budget) {
        this.budget = budget;
    }

    public BigInteger getGrossWorldwide() {
        return grossWorldwide;
    }

    public void setGrossWorldwide(BigInteger grossWorldwide) {
        this.grossWorldwide = grossWorldwide;
    }
}
