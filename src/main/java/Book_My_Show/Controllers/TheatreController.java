package Book_My_Show.Controllers;

import Book_My_Show.Models.City;
import Book_My_Show.Models.Movie;
import Book_My_Show.Models.Show;
import Book_My_Show.Models.Theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    Map<City, List<Theatre>> cityVsTheatre;
    List<Theatre> allTheatres;

    public TheatreController() {
        cityVsTheatre = new HashMap<>();
        allTheatres = new ArrayList<>();
    }

    public void addTheatres(Theatre theatre, City city) {
        allTheatres.add(theatre);
        List<Theatre> theatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());

        // FIXED: Add theatre to the city's theatre list
        theatres.add(theatre);

        cityVsTheatre.put(city, theatres);
    }

    public Map<Theatre, List<Show>> getAllShow(Movie movie, City city) {
        Map<Theatre, List<Show>> result = new HashMap<>();

        // Get all theatres in the target city
        List<Theatre> theatresInCity = cityVsTheatre.getOrDefault(city, new ArrayList<>());

        for (Theatre theatre : theatresInCity) {
            List<Show> matchingShows = new ArrayList<>();

            if (theatre.getShows() != null) {
                for (Show show : theatre.getShows()) {
                    if (show.getMovie() != null && show.getMovie().getMovieId() == movie.getMovieId()) {
                        matchingShows.add(show);
                    }
                }
            }

            if (!matchingShows.isEmpty()) {
                result.put(theatre, matchingShows);
            }
        }

        return result;
    }
}