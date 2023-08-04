import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bishop extends Piece {
    public Bishop(boolean color) {
        super(color);
        if (color) {
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_white_bishop.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_black_bishop.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isMovableTile(Board board, Tile start, Tile end) {
        if (end.getPiece() != null && (end.getPiece().getColor()==this.getColor())) {
            return false;
        }

        if(isPathObstructed(board,start,end)){
            return false;
        }

        int difX = Math.abs(start.getXCoordinate() - end.getXCoordinate());
        int difY = Math.abs(start.getYCoordinate() - end.getYCoordinate());
        if(difX != difY){
            return false;
        }
        else if(isPathObstructed(board, start,end)){
            return false;
        }else{
            return true;
        }
    }


    public boolean isPathObstructed(Board board, Tile start, Tile end) {

        int xDirection = Integer.signum(start.getXCoordinate() - end.getXCoordinate());
        int yDirection = Integer.signum(start.getYCoordinate() - end.getYCoordinate());

        if(xDirection<0&&yDirection<0){
            for(int i =1; i < Math.abs(end.getXCoordinate() - start.getXCoordinate()); i++){
                if(start.getXCoordinate()+i>7||start.getYCoordinate()+i>7){
                    return false;
                }
                if(board.getPiece(start.getXCoordinate()+i, start.getYCoordinate()+i)!=null){
                    return true;
                }
            }
        }
        else if(xDirection<0&&yDirection>0){
            for(int i = 1; i <Math.abs(end.getXCoordinate() - start.getXCoordinate()); i++){
                if(start.getXCoordinate()+i>7||start.getYCoordinate()-i<0) {
                    return false;
                }
                if(board.getPiece(start.getXCoordinate()+i, start.getYCoordinate()-i)!=null){
                    return true;
                }
            }
        }
        else if(xDirection>0&&yDirection<0){
            for(int i=1;i<Math.abs(start.getXCoordinate()-end.getXCoordinate());i++){
                if(start.getXCoordinate()-i<0||start.getYCoordinate()+i>7){
                    return false;
                }
                if(board.getPiece(start.getXCoordinate()-i, start.getYCoordinate()+i)!=null){
                    return true;
                }
            }
        }
        else if(xDirection>0&&yDirection>0){
            for(int i=1;i<Math.abs(start.getXCoordinate()-end.getXCoordinate());i++){
                if(start.getXCoordinate()-i<0||start.getYCoordinate()-i<0){
                    return false;
                }
                if(board.getPiece(start.getXCoordinate()-i, start.getYCoordinate()-i)!=null){
                    return true;
                }
            }
        }
        return false;

    }
}


