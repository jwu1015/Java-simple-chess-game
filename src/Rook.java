import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rook extends Piece{
    private boolean hasMoved;

    public Rook(boolean color){
        super(color);
        if(color){
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_white_rook.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_black_rook.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean isMovableTile(Board board,Tile start, Tile end) {
        if (end.getPiece() != null && (end.getPiece().getColor()==this.getColor())) {
            return false;
        }

        if(isObstructed(board,start,end)){
            return false;
        }

        if(Math.abs(start.getXCoordinate() - end.getXCoordinate()) * Math.abs(start.getYCoordinate() - end.getYCoordinate()) == 0){
            hasMoved=true;
            return true;
        }
        return false;
    }

    public boolean isObstructed(Board board,Tile start, Tile end){
        int xDirection = Integer.signum(start.getXCoordinate() - end.getXCoordinate());
        int yDirection = Integer.signum(start.getYCoordinate() - end.getYCoordinate());
        if(xDirection>0){
            for(int i = 1; i < Math.abs(start.getXCoordinate()-end.getXCoordinate());i++){
                if(board.getPiece(start.getXCoordinate()-i, start.getYCoordinate())!=null){
                    return true;
                }
            }
        } else if(xDirection<0){
            for(int i = 1; i < Math.abs(start.getXCoordinate()-end.getXCoordinate());i++){
                if(board.getPiece(start.getXCoordinate()+i, start.getYCoordinate())!=null){
                    return true;
                }
            }
        } else if(yDirection>0){
            for(int i = 1; i < Math.abs(end.getYCoordinate()-start.getYCoordinate());i++){
                if(board.getPiece(start.getXCoordinate(), start.getYCoordinate()-i)!=null){
                    return true;
                }
            }
        }else if(yDirection<0){
            for(int i = 1; i < Math.abs(end.getYCoordinate()-start.getYCoordinate());i++){
                if(board.getPiece(start.getXCoordinate(), start.getYCoordinate()+i)!=null){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean getHasMoved(){
        return hasMoved;
    }
}
