package Book_My_Show;

import Book_My_Show.Controllers.MovieController;
import Book_My_Show.Controllers.TheatreController;
import Book_My_Show.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {

    MovieController movieController;
    TheatreController theatreController;

    BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheatreController();
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();
        bookMyShow.userFlow();
    }

    private void initialize() {
        createMovies();
        createTheatres();
    }

    private void createMovies() {
        Movie avengers = new Movie();
        avengers.setMovieId(1);
        avengers.setMovieName("AVENGERS");
        avengers.setMovieDurationInMinutes(128);

        Movie bahubali = new Movie();
        bahubali.setMovieId(2);
        bahubali.setMovieName("BAHUBALI");
        bahubali.setMovieDurationInMinutes(190);

        movieController.addMovie(avengers, City.BANGALORE);
        movieController.addMovie(avengers, City.DELHI);
        movieController.addMovie(bahubali, City.BANGALORE);
        movieController.addMovie(bahubali, City.DELHI);
    }

    private void createTheatres() {

        Movie avengerMovie = movieController.getMovieByName("AVENGERS");
        Movie baahubali = movieController.getMovieByName("BAHUBALI");

        Theatre inoxTheatre = new Theatre();
        inoxTheatre.setTheatreId(1);
        inoxTheatre.setScreen(createScreen());
        inoxTheatre.setCity(City.BANGALORE);

        List<Show> inoxShows = new ArrayList<>();
        Show inoxMorningShow = createShows(1, inoxTheatre.getScreen().get(0), avengerMovie, 8);
        Show inoxEveningShow = createShows(2, inoxTheatre.getScreen().get(0), baahubali, 16);
        inoxShows.add(inoxMorningShow);
        inoxShows.add(inoxEveningShow);
        inoxTheatre.setShows(inoxShows);

        Theatre pvrTheatre = new Theatre();
        pvrTheatre.setTheatreId(2);
        pvrTheatre.setScreen(createScreen());
        pvrTheatre.setCity(City.DELHI);

        List<Show> pvrShows = new ArrayList<>();
        Show pvrMorningShow = createShows(3, pvrTheatre.getScreen().get(0), avengerMovie, 13);
        Show pvrEveningShow = createShows(4, pvrTheatre.getScreen().get(0), baahubali, 20);
        pvrShows.add(pvrMorningShow);
        pvrShows.add(pvrEveningShow);
        pvrTheatre.setShows(pvrShows);

        theatreController.addTheatres(inoxTheatre, City.BANGALORE);
        theatreController.addTheatres(pvrTheatre, City.DELHI);
    }

    private List<Screen> createScreen() {
        List<Screen> screens = new ArrayList<>();
        Screen screen1 = new Screen();
        screen1.setScreenId(1);
        screen1.setSeats(createSeats());
        screens.add(screen1);
        return screens;
    }

    private Show createShows(int showId, Screen screen, Movie movie, int showStartTime) {
        Show show = new Show();
        show.setShowId(showId);
        show.setScreen(screen);
        show.setMovie(movie);
        show.setShowStartTime(showStartTime);
        return show;
    }

    private List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= 40; i++) {
            Seat seat = new Seat();
            seat.setId(i);
            seat.setSc(SeatCatagory.SILVER);
            seats.add(seat);
        }

        for (int i = 41; i <= 70; i++) {
            Seat seat = new Seat();
            seat.setId(i);
            seat.setSc(SeatCatagory.GOLD);
            seats.add(seat);
        }

        for (int i = 71; i <= 100; i++) {
            Seat seat = new Seat();
            seat.setId(i);
            seat.setSc(SeatCatagory.PLATINUM);
            seats.add(seat);
        }

        return seats;
    }

    /*
     * USER FLOW (END TO END)
     */
    private void userFlow() {

        System.out.println("User logged in");

        // 1. User selects city
        City selectedCity = City.BANGALORE;
        System.out.println("Selected City: " + selectedCity);

        // 2. Fetch movie object
        String movieName = "BAHUBALI";
        Movie selectedMovie = movieController.getMovieByName(movieName);
        if (selectedMovie == null) {
            System.out.println("Movie not found: " + movieName);
            return;
        }
        System.out.println("Selected Movie: " + selectedMovie.getMovieName());

        // 3. Show theatres and running shows in city
        Map<Theatre, List<Show>> theatreVsShows = theatreController.getAllShow(selectedMovie, selectedCity);

        if (theatreVsShows == null || theatreVsShows.isEmpty()) {
            System.out.println("No shows available for " + movieName + " in " + selectedCity);
            return;
        }

        System.out.println("Theatres available:");
        theatreVsShows.keySet().forEach(t -> System.out.println(" - Theatre ID: " + t.getTheatreId()));

        // 4. User selects theatre & show
        Map.Entry<Theatre, List<Show>> entry = theatreVsShows.entrySet().iterator().next();
        Theatre selectedTheatre = entry.getKey();
        List<Show> runningShows = entry.getValue();

        System.out.println("Selected Theatre ID: " + selectedTheatre.getTheatreId());

        if (runningShows == null || runningShows.isEmpty()) {
            System.out.println("No running shows found.");
            return;
        }

        Show selectedShow = runningShows.get(0);
        System.out.println("Selected Show Start Time: " + selectedShow.getShowStartTime() + ":00");

        // 5. User selects seats
        int seatNumber = 30;
        System.out.println("Selected Seat Number: " + seatNumber);

        List<Integer> bookedSeats = selectedShow.getBookSeatIds();
        if (bookedSeats == null) {
            bookedSeats = new ArrayList<>();
            selectedShow.setBookSeatIds(bookedSeats);
        }

        // 6. Booking + Payment
        if (!bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);

            Booking booking = new Booking();
            List<Seat> myBookedSeats = new ArrayList<>();

            if (selectedShow.getScreen() != null && selectedShow.getScreen().getSeats() != null) {
                for (Seat seat : selectedShow.getScreen().getSeats()) {
                    if (seat.getId() == seatNumber) {
                        myBookedSeats.add(seat);
                    }
                }
            }

            booking.setShow(selectedShow);
            booking.setSeatList(myBookedSeats);

            System.out.println("BOOKING SUCCESSFUL");
            System.out.println("Booked Seat: " + seatNumber + " at Theatre ID: " + selectedTheatre.getTheatreId());
        } else {
            System.out.println("BOOKING FAILED: Seat already booked!");
        }
    }
}