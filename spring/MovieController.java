package com.example.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "movie")
public class MovieController {
	
	
	@Autowired
	private MovieService movieS;
	private static Map<Integer,Movie> movies = new HashMap<>();
	private static int counter = 3;
	
	static {
		movies.put(1, new Movie("Homot Shel Tikva",9.2f,"rafi menahem","https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY67_CR0,0,45,67_AL_.jpg"));
		movies.put(2, new Movie("Hasandak",9.1f,"michael itay","https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY67_CR1,0,45,67_AL_.jpg"));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Movie> getAllMovies() {
		return movies.values();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public int addMovie(@RequestBody Movie movie) {
		if (movie != null) {
			movies.put(counter++,movie);
			return 1;
		}
		return -1;
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public int removeMovie(@PathVariable int id) {
		if(movies.remove(id) != null) {
			return 1;
		}
		return -1;
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Movie getMovieById(@PathVariable int id) {
		return movies.get(id);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public int updateMovie(@PathVariable int id,@RequestBody Movie movie) {
		if(movies.containsKey(id)) {
			if(movie != null) {
				movies.put(id,movie);
				return 1;
			}
		}
		return -1;
		
	}
	
	
}
