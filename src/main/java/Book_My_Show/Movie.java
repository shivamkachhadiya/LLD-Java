package Book_My_Show;

public class Movie {
    int movieId;
    String movieName;
    int movieDurationInMinutes;

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieDurationInMinutes(int movieDurationInMinutes) {
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getMovieDurationInMinutes() {
        return movieDurationInMinutes;
    }
}
