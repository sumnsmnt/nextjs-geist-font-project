package com.example.catalogservice.controller;

import com.example.catalogservice.model.Movie;
import com.example.catalogservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping
  public List<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    Optional<Movie> movie = movieService.getMovieById(id);
    return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Movie createMovie(@RequestBody Movie movie) {
    return movieService.createMovie(movie);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
    return ResponseEntity.noContent().build();
  }
}
