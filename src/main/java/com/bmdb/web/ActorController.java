package com.bmdb.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.business.JsonResponse;
import com.bmdb.business.MovieGenre;
import com.bmdb.db.ActorRepository;

@CrossOrigin
@RestController
@RequestMapping("/actors")
public class ActorController {
	@Autowired
	private ActorRepository actorRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<Actor> actors = actorRepo.findAll();
		if (actors.size() > 0) {
			jr = JsonResponse.getInstance(actors);
		} else {
			jr = JsonResponse.getErrorInstance("No Actors Found.");
		}
		return jr;
	}
	
	@GetMapping("/list/birthDate")
	public JsonResponse findByBirthDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)@PathVariable String birthDate) {
		LocalDate ld = LocalDate.parse(birthDate);
		JsonResponse jr = null;
		List<Actor> actors = actorRepo.findAllByBirthDateBefore(ld);
		if (actors.size() > 0) {
			jr = JsonResponse.getInstance(actors);
		} else {
			jr = JsonResponse.getErrorInstance("No Actors Found.");
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		Optional<Actor> actor = actorRepo.findById(id);
		if (actor.isPresent()) {
			jr = JsonResponse.getInstance(actor.get());
		} else {
			jr = JsonResponse.getErrorInstance("No Actor found for Id." + id);
		}
		return jr;
	}
	

	// 'create' method
	@PostMapping("/")
	public JsonResponse createActor(@RequestBody Actor a) {
		JsonResponse jr = null;

		try {
			a = actorRepo.save(a);
			jr = JsonResponse.getInstance(a);
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating actor: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

	@PutMapping("/")
	public JsonResponse updateActor(@RequestBody Actor a) {
		JsonResponse jr = null;

		try {
			a = actorRepo.save(a);
			jr = JsonResponse.getInstance(a);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating actor: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

	@DeleteMapping("/{id}")
	public JsonResponse deleteActor(@PathVariable int id) {
		JsonResponse jr = null;

		try {
			actorRepo.deleteById(id);
			jr = JsonResponse.getInstance("Actor id: " + id + " deleted successfully");
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting actor: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}
}
