package TIC_TAC_TOE;

import TIC_TAC_TOE.Model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Deque<Player>players;
    Board gameBoard;

    Game(){
        initalizeGame();
    }
    public void initalizeGame(){
        //creating 2 palyers
        players=new LinkedList<>();

        PlayingPieceX crossPiece=new PlayingPieceX();
        Player player1=new Player("player 1",crossPiece);

        PlayingPieceO noughtPiece=new PlayingPieceO();
        Player player2=new Player("player 2",noughtPiece);

        players.add(player1);
        players.add(player2);

        gameBoard=new Board(3);
    }

    public String startGame(){
        boolean noWinner=true;
        while(noWinner){
            //take out player whose turn is and also put back in list back
            Player playerTurn=players.removeFirst();

            //get the free space from board
            gameBoard.printBoard();
            List<Pair<Integer,Integer>> freeSpaces=gameBoard.getFreeCells();

            //if free space is not available loop set as false and return tie
            if(freeSpaces.isEmpty()){
                noWinner=false;
                continue;
            }

            //if free space availa ble
            System.out.println("Player : "+playerTurn.name+"enter row and column..");
            Scanner inputScanner=new Scanner(System.in);
            String s=inputScanner.nextLine();
            String[] values=s.split(",");
            int inputRow=Integer.valueOf(values[0]);
            int inputColumn=Integer.valueOf(values[1]);

            //piece add
            boolean pieceAddSuccessfully=gameBoard.addPiece(inputRow,inputColumn,playerTurn.playingPiece);
            if(!pieceAddSuccessfully){
                System.out.println("incorrect position try again");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);

            boolean winner=isThereWinner(inputRow,inputColumn,playerTurn.playingPiece.pieceType);
            if(winner){
                return playerTurn.name;
            }
        }
        return "tie";
    }

    public boolean isThereWinner(int row,int col,PieceType pieceType){
        boolean rowMatch=true;
        boolean columnMatch=true;
        boolean diagonalMatch=true;
        boolean antiDiagonalMatch=true;

        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[row][i]==null||gameBoard.board[row][i].pieceType!=pieceType){
                rowMatch=false;
            }
        }

        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[i][col]==null||gameBoard.board[i][col].pieceType!=pieceType){
                columnMatch=false;
            }
        }

        for(int i=0,j=0;i<gameBoard.size;i++,j++){
            if(gameBoard.board[i][j]==null||gameBoard.board[i][j].pieceType!=pieceType){
                diagonalMatch=false;
            }
        }

        for(int i=0,j=gameBoard.size-1;i<gameBoard.size;i++,j--){
            if(gameBoard.board[i][j]==null||gameBoard.board[i][j].pieceType!=pieceType){
                antiDiagonalMatch=false;
            }
        }

        return rowMatch||columnMatch||diagonalMatch||antiDiagonalMatch;
    }
}
