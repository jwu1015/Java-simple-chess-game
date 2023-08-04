import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Pawn extends Piece {

    public Pawn(boolean color) {
        super(color);
        if (color) {
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_white_pawn.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_black_pawn.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean isMovableTile(Board board, Tile start, Tile end) {
        if (end.getPiece() != null && (end.getPiece().getColor() == this.getColor())) {
            return false;
        }
        if (this.getColor() && Math.abs(end.getXCoordinate() - start.getXCoordinate()) == 1) {
            if (end.getYCoordinate() == start.getYCoordinate() && end.getXCoordinate() - start.getXCoordinate() < 0 && end.getPiece() == null) {
                return true;
            }
            if (Math.abs(start.getYCoordinate() - end.getYCoordinate()) == 1 && end.getXCoordinate() - start.getXCoordinate() < 0 && end.getPiece() != null) {
                return true;
            }
        }
        if (!this.getColor() && Math.abs(end.getXCoordinate() - start.getXCoordinate()) == 1) {
            if (end.getYCoordinate() == start.getYCoordinate() && end.getXCoordinate() - start.getXCoordinate() > 0 && end.getPiece() == null) {
                return true;
            }
            if (Math.abs(start.getYCoordinate() - end.getYCoordinate()) == 1 && end.getXCoordinate() - start.getXCoordinate() > 0 && end.getPiece() != null) {
                return true;
            }
        }
        if ((!this.getColor() && start.getXCoordinate() == 1) || (this.getColor() && start.getXCoordinate() == 6)) {
            if (Math.abs(end.getXCoordinate() - start.getXCoordinate()) == 2) {
                if (end.getYCoordinate() == start.getYCoordinate() && end.getPiece() == null) {
                    if (this.getColor()) {
                        return board.getPiece(start.getXCoordinate()-1, start.getYCoordinate()) == null;
                    } else {
                        return board.getPiece(start.getXCoordinate()+1, start.getYCoordinate()) == null;
                    }
                }
            }
        }
        return false;
    }
}
