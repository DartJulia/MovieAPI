package com.webapi.WebApiLab25;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapi.WebApiLab25.entity.Movie;
import com.webapi.WebApiLab25.jpa.MovieRepository;

@RestController
public class HomeController {

	@Autowired
	MovieRepository mr;

	// returns all movies
	@GetMapping("/")
	public List<Movie> getAll() {

		return mr.findAll();
	}

	// returns all from a specific category
	@GetMapping("category")
	public List<Movie> getAllCat(@RequestParam("category") String category) {

		return mr.findByCategory(category);
	}

// returns one random movie pick from list
	@GetMapping("random")
	public Optional<Movie> getRandom() {

		Random rand = new Random();
		long l = (long) rand.nextInt(11) + 1;
		return mr.findById(l);
	}

	// Returns a random movie pick from a specific category, requires user to input
	// a category
	@GetMapping("catrandom")
	public Movie getRandomFromCat(@RequestParam("category") String category) {

		Random rand = new Random();

		List<Movie> mov = mr.findByCategory(category);
		int l = rand.nextInt(mov.size() - 1) + 1;
		return mov.get(l);

	}

//returns a list of all movie categories in API
	@GetMapping("allcats")
	public ArrayList<String> getAllCats() {
		ArrayList<String> cats = new ArrayList<>();
		List<Movie> movies = mr.findAll();
		String cat = "";
		for (int i = 0; i < movies.size(); i++) {
			cat = movies.get(i).getCategory();
			if (!cats.contains(cat)) {
			cats.add(cat);
			}
		}
		return cats;
	}

// returns info on movie based on title entered
	@GetMapping("info")
	public List<Movie> getByTitle(@RequestParam("title") String title) {
		return mr.findByTitle(title);
	}

	// returns info on movie based on matching keyword
	@GetMapping("search")
	public List<Movie> getByKeyword(@RequestParam("keyword") String keyword) {

		return mr.findByTitleContaining(keyword);
	}

// returns random number of movies based on number user inputs
	@GetMapping("randlist")
	public List<Movie> getRandomByNum(@RequestParam("num") int num) {
		List<Movie> movies = mr.findAll();
		ArrayList<Movie> list = new ArrayList<>();
		Random rand = new Random();
		for (int i = 1; i <= num; i++) {
			list.add(movies.get(rand.nextInt(i) + 1));
			
		}
		return list;
	}




}
