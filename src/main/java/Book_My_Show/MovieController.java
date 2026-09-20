package Book_My_Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<City, List<Movie>> cityVsMovies;
    List<Movie>allMovies;

    public MovieController() {
        cityVsMovies = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    public void addMovie(Movie movie,City city){
        allMovies.add(movie);
        List<Movie>movies=cityVsMovies.getOrDefault(city,new ArrayList<>());
        movies.add(movie);
        cityVsMovies.put(city,movies);
    }

    List<Movie>getMoviesByCity(City city){
        return cityVsMovies.get(city);
    }

    Movie getMovieByName(String movieName){
        for(Movie m:allMovies){
            if(m.getMovieName()==movieName){
                return m;
            }
        }
        return null;
    }

}
