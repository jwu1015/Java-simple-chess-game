import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Piece {
    //instance vars
    private boolean color;
    private BufferedImage image;

    //getters and setters
    public Piece(boolean color){
        this.color =color;
    }

    public boolean getColor() {
        return color;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


    //methods
    public void move(Board board,Tile start, Tile end){
        if(this.isMovableTile(board,start,end)&& !inCheckAfterMove(board, start, end) && !(end.getPiece() instanceof King)){
                if(start.getPiece() instanceof King&&((King) start.getPiece()).isCanCastle()&&!(((King) start.getPiece()).isHasMoved())){
                    ((King) start.getPiece()).setHasMoved(true);
                    if(getColor()&& end.getPiece().getTile(board).getYCoordinate()==7){
                        board.getBoard()[7][6].setPiece(start.getPiece());
                        start.setPiece(null);
                        board.getBoard()[7][5].setPiece(end.getPiece());
                        end.setPiece(null);
                    }else if(getColor()&& end.getPiece().getTile(board).getYCoordinate()==0){
                        board.getBoard()[7][2].setPiece(start.getPiece());
                        start.setPiece(null);
                        board.getBoard()[7][3].setPiece(end.getPiece());
                        end.setPiece(null);
                    }else if(!getColor()&& end.getPiece().getTile(board).getYCoordinate()==7){
                        board.getBoard()[0][6].setPiece(start.getPiece());
                        start.setPiece(null);
                        board.getBoard()[0][5].setPiece(end.getPiece());
                        end.setPiece(null);
                    }else if(!getColor()&& end.getPiece().getTile(board).getYCoordinate()==0){
                        board.getBoard()[7][2].setPiece(start.getPiece());
                        start.setPiece(null);
                        board.getBoard()[7][3].setPiece(end.getPiece());
                        end.setPiece(null);
                    }
                    return;
                }
                end.setPiece(start.getPiece());
                start.setPiece(null);
                if((end.getPiece() instanceof Pawn && end.getPiece().getColor()&&end.getPiece().getTile(board).getXCoordinate()==0)||(end.getPiece() instanceof Pawn && !end.getPiece().getColor()&&end.getPiece().getTile(board).getXCoordinate()==7)){
                    end.setPiece(new Queen(end.getPiece().getColor()));
                }
        }
    }

    public abstract boolean isMovableTile(Board board, Tile start, Tile end);

    public void draw(Graphics g,int x, int y) {
        Graphics2D g2=(Graphics2D)g;
        g2.drawImage(getImage(),null,x,y);
    }

    public Tile getTile(Board board){
        for(int rows=0;rows<8;rows++) {
            for (int cols = 0; cols < 8; cols++) {
                if(board.getBoard()[rows][cols].getPiece()!=null&&board.getBoard()[rows][cols].getPiece().equals(this)){
                    return board.getBoard()[rows][cols];
                }
            }
        }
        return null;
    }

    public boolean inCheckAfterMove(Board board, Tile start, Tile end){
        Board temp = new Board(board);
        if(temp.getPiece(start.getXCoordinate(), start.getYCoordinate()).isMovableTile(temp,temp.getBoard()[start.getXCoordinate()][start.getYCoordinate()],temp.getBoard()[end.getXCoordinate()][end.getYCoordinate()])){
                if(start.getPiece() instanceof Pawn){
                    temp.getBoard()[end.getXCoordinate()][end.getYCoordinate()].setPiece(new Pawn(start.getPiece().getColor()));
                } else if(start.getPiece() instanceof Knight){
                    temp.getBoard()[end.getXCoordinate()][end.getYCoordinate()].setPiece(new Knight(start.getPiece().getColor()));
                }else if(start.getPiece() instanceof Bishop){
                    temp.getBoard()[end.getXCoordinate()][end.getYCoordinate()].setPiece(new Bishop(start.getPiece().getColor()));
                }else if(start.getPiece() instanceof Rook){
                    temp.getBoard()[end.getXCoordinate()][end.getYCoordinate()].setPiece(new Rook(start.getPiece().getColor()));
                }else if(start.getPiece() instanceof Queen){
                    temp.getBoard()[end.getXCoordinate()][end.getYCoordinate()].setPiece(new Queen(start.getPiece().getColor()));
                } else if (start.getPiece() instanceof King){
                    temp.getBoard()[end.getXCoordinate()][end.getYCoordinate()].setPiece(new King(start.getPiece().getColor()));
                }
            temp.getBoard()[start.getXCoordinate()][start.getYCoordinate()].setPiece(null);
        }else{
            return false;
        }
        if(temp.getKing(temp.getPiece(end.getXCoordinate(), end.getYCoordinate()).getColor()).inCheck(temp)){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasLegalMoves(Board board){
        for(int rows=0;rows<8;rows++) {
            for (int cols = 0; cols < 8; cols++) {
                if(isMovableTile(board,this.getTile(board),board.getBoard()[rows][cols])&& !inCheckAfterMove(board, this.getTile(board), board.getBoard()[rows][cols]) &&!(board.getBoard()[rows][cols].getPiece() instanceof King)){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        if(this instanceof Pawn){
            return "pawn ";
        } else if(this instanceof Bishop){
            return "bishop ";
        } else if(this instanceof Knight){
            return "knight ";
        } else if(this instanceof Queen){
            return "queen ";
        } else if(this instanceof Rook){
            return "rook ";
        } else if(this instanceof King){
            return "king ";
        }  else{
            return" ";
        }
    }
}
