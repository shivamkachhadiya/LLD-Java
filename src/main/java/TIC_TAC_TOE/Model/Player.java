package TIC_TAC_TOE.Model;

public class Player {
    public String name;
    public PlayingPiece playingPiece;

    public Player(String name,PlayingPiece playingPiece){
        this.name=name;
        this.playingPiece=playingPiece;
    }
    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
    }
    public PlayingPiece getPlayingPiece(){
        return playingPiece;
    }
    public void setPlayingPiece(PlayingPiece playingPiece){
        this.playingPiece=playingPiece;
    }
}
