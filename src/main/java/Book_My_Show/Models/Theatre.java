package Book_My_Show.Models;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    int theatreId;
    String address;
    City city;
    List<Screen> screen=new ArrayList<>();
    List<Show>shows=new ArrayList<>();

    public int getTheatreId() {
        return theatreId;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreen() {
        return screen;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setScreen(List<Screen> screen) {
        this.screen = screen;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
