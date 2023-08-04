import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Knight extends Piece{
    public Knight(boolean color){
        super(color);
        if(color){
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_white_knight.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_black_knight.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isMovableTile(Board board,Tile start, Tile end) {
        if (end.getPiece() != null && (end.getPiece().getColor()==this.getColor())) {
            return false;
        }

        if(Math.abs(end.getXCoordinate() - start.getXCoordinate()) == 2 && Math.abs(end.getYCoordinate() - start.getYCoordinate()) == 1){
            return true;
        }

        if(Math.abs(end.getXCoordinate() - start.getXCoordinate()) == 1 && Math.abs(end.getYCoordinate() - start.getYCoordinate()) == 2){
            return true;
        }

        return false;
    }
}
