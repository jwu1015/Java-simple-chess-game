import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class King extends Piece{
    private boolean hasMoved;
    private boolean canCastle;
    public King(boolean color){
        super(color);
        if(color){
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_white_king.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            try {
                setImage(ImageIO.read(new File("/Users/danielwu/Downloads/Sem. 2 Final/images/75px_black_king.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean isMovableTile(Board board, Tile start, Tile end) {
        if (end.getPiece() != null && (end.getPiece().getColor()==this.getColor())) {
            if(!(end.getPiece() instanceof Rook)) {
                return false;
            }
        }
        if(Math.abs(end.getXCoordinate() - start.getXCoordinate()) > 1 || Math.abs(end.getYCoordinate() - start.getYCoordinate()) > 1) {
            if(canCastle(board,start,end)){
                canCastle=true;
                return true;
            }
            return false;
        }
        return !(end.getPiece() instanceof Rook);
    }
    public boolean inCheck(Board board){
        for(int rows=0;rows<8;rows++){
            for(int cols=0;cols<8;cols++) {
                Piece piece=board.getBoard()[rows][cols].getPiece();
                if(piece!=null&&piece.getColor()!=this.getColor()&& piece.isMovableTile(board, piece.getTile(board), this.getTile(board))){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canCastle(Board board, Tile start, Tile end){
        if(!hasMoved&&getColor()&&(start.getXCoordinate()==7&&start.getXCoordinate()==end.getXCoordinate())&&end.getPiece() instanceof Rook&&!((Rook) end.getPiece()).getHasMoved()){
            if(end.getXCoordinate()==7&&end.getYCoordinate()==7){
                return board.getPiece(7, 6) == null && board.getPiece(7, 5) == null;
            } else if(end.getXCoordinate()==7&&end.getYCoordinate()==0){
                return board.getPiece(7, 1) == null && board.getPiece(7, 2) == null && board.getPiece(7, 3) == null;
            }
        }else if(!hasMoved&&!getColor()&&(start.getXCoordinate()==0&&start.getXCoordinate()==end.getXCoordinate())&&end.getPiece() instanceof Rook&&!((Rook) end.getPiece()).getHasMoved()){
            if(end.getXCoordinate()==0&&end.getYCoordinate()==7){
                return board.getPiece(0, 6) == null && board.getPiece(0, 5) == null;
            } else if(end.getXCoordinate()==0&&end.getYCoordinate()==0){
                return board.getPiece(0, 1) == null && board.getPiece(0, 2) == null && board.getPiece(7, 3) == null;
            }
        }
        return false;
    }

    public boolean isInMateOrStalemate(Board board) {
        for (int rows = 0; rows < 8; rows++) {
            for (int cols = 0; cols < 8; cols++) {
                if (board.getPiece(rows, cols)!=null&&board.getPiece(rows, cols).getColor() == this.getColor()) {
                    if (board.getPiece(rows, cols).hasLegalMoves(board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isCanCastle() {
        return canCastle;
    }
    public boolean isHasMoved() {
        return hasMoved;
    }
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }


}
