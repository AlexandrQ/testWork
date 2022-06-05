package com.testWork.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.testWork.dao.MovieMapper;
import com.testWork.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MovieController {

    private static final String GET_ALL_MOVIES_ERR_MSG = "Error while loading movies.";
    private static final String GET_ALL_MOVIES_ERR_MSG_FOR_USER = GET_ALL_MOVIES_ERR_MSG + " Please try again or contact to tech support.";
    private static final String USER_ERR_MSG_PARAM = "userErrorMsg";
    private static final String NEW_MOVIE_ERR_MSG = "Error while creating new movie.";
    private static final String NEW_MOVIE_DATE_ERR_MSG_FOR_USER = NEW_MOVIE_ERR_MSG + " Please, pass the valid date.";
    private static final String NEW_MOVIE_NUMBER_ERR_MSG_FOR_USER = NEW_MOVIE_ERR_MSG + " Please, pass the valid budget and gross worldwide values.";
    private static final String UPDATE_MOVIE_ERR_MSG_FOR_USER = "There is no movie for update";

    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());

    @Autowired
    MovieMapper movieMapper;

    @GetMapping("/")
    public String getAllMovies(Model model) {
        String jsonMovies = "";
        ObjectWriter objectWriter = new ObjectMapper().writer();
        try {
            jsonMovies = objectWriter.writeValueAsString(movieMapper.getAllMovies());
        } catch (JsonProcessingException e) {
            model.addAttribute(USER_ERR_MSG_PARAM, GET_ALL_MOVIES_ERR_MSG_FOR_USER);
            LOGGER.log(Level.SEVERE, GET_ALL_MOVIES_ERR_MSG, e);
        }
        model.addAttribute("jsonMoviesModels", jsonMovies);
        return "moviesPage";
    }

    @PostMapping("/movie/add")
    public String addMovie(@RequestParam String name,
                           @RequestParam String date,
                           @RequestParam String budget,
                           @RequestParam String grossWorldwide,
                           Model model) {
        try {
            Movie movie = new Movie(name, LocalDate.parse(date), new BigInteger(budget), new BigInteger(grossWorldwide));
            movieMapper.insertMovie(movie);
        } catch (DateTimeParseException e) {
            model.addAttribute(USER_ERR_MSG_PARAM, NEW_MOVIE_DATE_ERR_MSG_FOR_USER);
            LOGGER.log(Level.WARNING, NEW_MOVIE_ERR_MSG, e);
        }  catch (NumberFormatException e) {
            model.addAttribute(USER_ERR_MSG_PARAM, NEW_MOVIE_NUMBER_ERR_MSG_FOR_USER);
            LOGGER.log(Level.WARNING, NEW_MOVIE_ERR_MSG, e);
        }
        return "moviesPage";
    }

    @PostMapping("/movie/{id}/update")
    public String updateMovie(@PathVariable(value = "id") long id,
                              @RequestParam String name,
                              @RequestParam String date,
                              @RequestParam String budget,
                              @RequestParam String grossWorldwide,
                              Model model) {
        Movie dbMovie = movieMapper.getMovieById(id);
        if(Objects.nonNull(dbMovie)) {
            dbMovie.setName(name);
            dbMovie.setReleaseDate(LocalDate.parse(date));
            dbMovie.setBudget(new BigInteger(budget));
            dbMovie.setGrossWorldwide(new BigInteger(grossWorldwide));
            movieMapper.updateMovie(dbMovie);
        } else {
            model.addAttribute(USER_ERR_MSG_PARAM, UPDATE_MOVIE_ERR_MSG_FOR_USER);
        }
        return "moviesPage";
    }

    @PostMapping("/movie/{id}/delete")
    public String deleteMovie(@PathVariable(value = "id") long id,
                              Model model) {
        movieMapper.deleteMovie(id);
        return "moviesPage";
    }

}
