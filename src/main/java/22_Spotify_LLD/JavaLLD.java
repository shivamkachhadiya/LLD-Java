import java.util.*;

class Song {
    private int songId;
    private String songName;
    private String singer;
    private double time;

    Song(int i, String sn, String sr, double time) {
        this.songId = i;
        this.songName = sn;
        this.singer = sr;
        this.time = time;
    }

    public int getId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }

    public String getSinger() {
        return singer;
    }

    public double getTime() {
        return time;
    }
}

class PlayList {
    private int playListId;
    private String name;
    ArrayList<Song> song_arr = new ArrayList<>();
    private int MAX_SONGS = 200;

    PlayList(int id, String name) {
        this.playListId = id;
        this.name = name;
    }

    void addSong(Song song) {
        if (song_arr.size() >= MAX_SONGS) {
            System.out.println("Playlist is full.");
            return;
        }
        song_arr.add(song);
    }

    void removeSong(Song sg) {
        for (int i = 0; i < song_arr.size(); i++) {
            if (sg.getId() == song_arr.get(i).getId()) {
                song_arr.remove(i);
                return;
            }
        }
    }

    void renamePlayList(String rename) {
        this.name = rename;
    }

    void displaySongs() {
        for (Song song : song_arr) {
            System.out.println(
                song.getSongName() + " - " +
                song.getSinger() + " - " +
                song.getTime() + " sec"
            );
        }
    }

    Song getSong(int index) {
        return song_arr.get(index);
    }

    int getSize() {
        return song_arr.size();
    }
}

class User {
    private int userId;
    private String name;
    ArrayList<PlayList> playlist_arr = new ArrayList<>();

    User(int id, String name) {
        this.userId = id;
        this.name = name;
    }

    PlayList createPlayList(int id, String name) {
        PlayList playlist = new PlayList(id, name);
        playlist_arr.add(playlist);
        return playlist;
    }
}

// ==================== STRATEGY ====================

interface PlaybackStrategy {
    int getNextIndex(PlayList playlist, int currentIndex);
}

class NormalPlayback implements PlaybackStrategy {
    public int getNextIndex(PlayList playlist, int currentIndex) {
        return currentIndex + 1;
    }
}

class ShufflePlayback implements PlaybackStrategy {
    public int getNextIndex(PlayList playlist, int currentIndex) {
        Random random = new Random();
        int size = playlist.getSize();
        return random.nextInt(size);
    }
}

class RepeatPlayback implements PlaybackStrategy {
    public int getNextIndex(PlayList playlist, int currentIndex) {
        return currentIndex;
    }
}

// ==================== STATE ====================

interface PlayerState {
    void play(Player player);
    void pause(Player player);
    void stop(Player player);
}

class PlayingState implements PlayerState {
    public void play(Player player) {
        System.out.println("Already Playing..");
    }

    public void pause(Player player) {
        System.out.println("Paused...");
        player.setState(new PausedState());
    }

    public void stop(Player player) {
        System.out.println("Stopped..");
        player.setState(new StoppedState());
    }
}

class PausedState implements PlayerState {
    public void play(Player player) {
        System.out.println("Resuming...");
        player.setState(new PlayingState());
    }

    public void pause(Player player) {
        System.out.println("Already Paused..");
    }

    public void stop(Player player) {
        System.out.println("Stopped..");
        player.setState(new StoppedState());
    }
}

class StoppedState implements PlayerState {
    public void play(Player player) {
        System.out.println("Playing...");
        player.setState(new PlayingState());
    }

    public void pause(Player player) {
        System.out.println("Cannot pause. Player is stopped.");
    }

    public void stop(Player player) {
        System.out.println("Already Stopped..");
    }
}

// ==================== OBSERVER ====================

interface Observer {
    void update(Song song);
}

class PlayHistory implements Observer {
    private ArrayList<Song> history = new ArrayList<>();

    public void update(Song song) {
        history.add(song);
        System.out.println("History updated: " + song.getSongName());
    }
}

// ==================== FACTORY ====================

class PlaybackStrategyFactory {
    static PlaybackStrategy create(String type) {
        if (type.equals("NORMAL")) {
            return new NormalPlayback();
        }

        if (type.equals("SHUFFLE")) {
            return new ShufflePlayback();
        }

        if (type.equals("REPEAT")) {
            return new RepeatPlayback();
        }

        throw new IllegalArgumentException("Invalid playback type");
    }
}

// ==================== PLAYER ====================

class Player {
    private PlayList currentPlaylist;
    private int currentIndex;
    private PlaybackStrategy strategy;
    private PlayerState state;
    private ArrayList<Observer> observers = new ArrayList<>();

    Player(PlaybackStrategy strategy) {
        this.strategy = strategy;
    }

    void play(PlayList playlist) {
        currentPlaylist = playlist;
        currentIndex = 0;
        state = new PlayingState();
        playCurrentSong();
    }

    void next() {
        currentIndex = strategy.getNextIndex(
            currentPlaylist,
            currentIndex
        );
        playCurrentSong();
    }

    void playCurrentSong() {
        Song currentSong = currentPlaylist.getSong(currentIndex);

        System.out.println(
            "Playing: " +
            currentSong.getSongName() +
            " - " +
            currentSong.getSinger()
        );

        notifyObservers(currentSong);
    }

    void setState(PlayerState state) {
        this.state = state;
    }

    void pause() {
        state.pause(this);
    }

    void stop() {
        state.stop(this);
    }

    void play() {
        state.play(this);
    }

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void notifyObservers(Song song) {
        for (Observer observer : observers) {
            observer.update(song);
        }
    }
}

// ==================== MAIN ====================

public class JavaLLD {
    public static void main(String[] args) {

        Song s1 = new Song(1, "Perfect", "Ed Sheeran", 263);
        Song s2 = new Song(2, "Believer", "Imagine Dragons", 204);
        Song s3 = new Song(3, "Shape of You", "Ed Sheeran", 234);

        User user = new User(101, "Shivam");

        PlayList p1 = user.createPlayList(501, "My Favorites");

        p1.addSong(s1);
        p1.addSong(s2);
        p1.addSong(s3);

        PlaybackStrategy strategy =
            PlaybackStrategyFactory.create("NORMAL");

        Player player = new Player(strategy);

        PlayHistory history = new PlayHistory();

        player.addObserver(history);

        player.play(p1);
        player.next();
        player.next();

        player.pause();
        player.play();
        player.stop();
    }
}