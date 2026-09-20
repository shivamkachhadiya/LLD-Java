package Book_My_Show;

import java.util.ArrayList;
import java.util.List;

public class Show {
    int showId;
    Movie movie;
    Screen screen;
    int showStartTime;
    List<Integer> bookSeatIds=new ArrayList<>();

    public int getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public List<Integer> getBookSeatIds() {
        return bookSeatIds;
    }

    public int getShowStartTime() {
        return showStartTime;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setShowStartTime(int showStartTime) {
        this.showStartTime = showStartTime;
    }

    public void setBookSeatIds(List<Integer> bookSeatIds) {
        this.bookSeatIds = bookSeatIds;
    }
}
