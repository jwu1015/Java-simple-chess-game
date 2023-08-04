import java.awt.*;

public class Tile {
    //instance vars
    private int XCoordinate;
    private int YCoordinate;

    //constructor
    private Piece piece;
    public Tile(int X, int Y, Piece piece){
        this.XCoordinate=X;
        this.YCoordinate=Y;
        this.piece=piece;
    }

    //getters and setters
    public int getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(int XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public int getYCoordinate() {
        return YCoordinate;
    }

    public void setYCoordinate(int YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //methods
    public boolean isEmpty(int x, int y){
        if(piece!=null){
            return false;
        }
        return true;
    }

}
