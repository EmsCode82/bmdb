package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.business.JsonResponse;
import com.bmdb.business.MovieGenre;
import com.bmdb.db.ActorRepository;
import com.bmdb.db.MovieGenreRepository;

@RestController
@RequestMapping("/moviegenres")
public class MovieGenreController {
	@Autowired
	private MovieGenreRepository movieGenreRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<MovieGenre> moviegenres = movieGenreRepo.findAll();
		if (moviegenres.size() > 0) {
			jr = JsonResponse.getInstance(moviegenres);
		} else {
			jr = JsonResponse.getErrorInstance("No Movie Genres Found.");
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		Optional<MovieGenre> moviegenre = movieGenreRepo.findById(id);
		if (moviegenre.isPresent()) {
			jr = JsonResponse.getInstance(moviegenre.get());
		} else {
			jr = JsonResponse.getErrorInstance("No Movie Genre found for Id." + id);
		}
		return jr;
	}
	// Disclaimer - This method may not follow strict API Style Guide rules
	@GetMapping("/by-movie-id")
	public JsonResponse listByMovieId(@RequestParam int movieId) {
		JsonResponse jr = null;
		List<MovieGenre> moviegenres = movieGenreRepo.findAllByMovieId(movieId);
		if (moviegenres.size() > 0) {
			jr = JsonResponse.getInstance(moviegenres);
		} else {
			jr = JsonResponse.getErrorInstance("No Movie Genres Found for movie id: ."+movieId);
		}
		return jr;
	}

	// 'create' method
	@PostMapping("/")
	public JsonResponse createMovieGenre(@RequestBody MovieGenre mg) {
		JsonResponse jr = null;

		try {
			mg = movieGenreRepo.save(mg);
			jr = JsonResponse.getInstance(mg);
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating movie genre: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

	@PutMapping("/")
	public JsonResponse updateMovieGenre(@RequestBody MovieGenre mg) {
		JsonResponse jr = null;

		try {
			mg = movieGenreRepo.save(mg);
			jr = JsonResponse.getInstance(mg);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating movie genre: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

	@DeleteMapping("/{id}")
	public JsonResponse deleteMovieGenre(@PathVariable int id) {
		JsonResponse jr = null;

		try {
			movieGenreRepo.deleteById(id);
			jr = JsonResponse.getInstance("Movie Genre id: " + id + " deleted successfully");
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting movie genre: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}
}
