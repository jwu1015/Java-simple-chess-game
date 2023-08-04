import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Queen extends Piece{
    public Queen(boolean color){
        super(color);
        if(color){
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_white_queen.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_black_queen.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isMovableTile(Board board,Tile start, Tile end) {
        if (end.getPiece() != null && (end.getPiece().getColor()==this.getColor())) {
            return false;
        }
        if(new Rook(this.getColor()).isMovableTile(board, start, end)||new Bishop(this.getColor()).isMovableTile(board, start, end)){
            return true;
        }
        return false;
    }
}
