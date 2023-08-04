public class Board {
    //instance vars
    public Tile[][] board;

    //constructor
    public Board(){
        board = new Tile[8][8];
        for(int rows=0;rows<8;rows++){
            for(int cols=0;cols<8;cols++){
                board[rows][cols]=new Tile(rows,cols,null);
            }
        }
        board[0][0] = new Tile(0, 0, new Rook(false));
        board[0][1] = new Tile(0, 1, new Knight(false));
        board[0][2] = new Tile(0, 2, new Bishop(false));
        board[0][3] = new Tile(0, 3, new Queen(false));
        board[0][4] = new Tile(0, 4, new King(false));
        board[0][5] = new Tile(0, 5, new Bishop(false));
        board[0][6] = new Tile(0, 6, new Knight(false));
        board[0][7] = new Tile(0, 7, new Rook(false));



        for(int cols=0;cols<8;cols++){
            board[1][cols]=new Tile(1,cols,new Pawn(false));
        }
        board[7][0] = new Tile(7, 0, new Rook(true));
        board[7][1] = new Tile(7, 1, new Knight(true));
        board[7][2] = new Tile(7, 2, new Bishop(true));
        board[7][3] = new Tile(7, 3, new Queen(true));
        board[7][4] = new Tile(7, 4, new King(true));
        board[7][5] = new Tile(7, 5, new Bishop(true));
        board[7][6] = new Tile(7, 6, new Knight(true));
        board[7][7] = new Tile(7, 7, new Rook(true));

        for(int cols=0;cols<8;cols++){
            board[6][cols]=new Tile(6,cols,new Pawn(true));
        }
    }
    public Board(Board temp){
        board = new Tile[8][8];
        for(int row=0;row<8;row++){
            for(int col=0;col<8;col++){
                Piece piece =temp.getBoard()[row][col].getPiece();
                if(piece!=null) {
                    if(piece instanceof Pawn){
                        board[row][col]=new Tile(row, col,new Pawn(piece.getColor()));
                    } else if(piece instanceof Knight){
                        board[row][col]=new Tile(row, col,new Knight(piece.getColor()));
                    }else if(piece instanceof Bishop){
                        board[row][col]=new Tile(row, col,new Bishop(piece.getColor()));
                    }else if(piece instanceof Rook){
                        board[row][col]=new Tile(row, col,new Rook(piece.getColor()));
                    }else if(piece instanceof Queen){
                        board[row][col]=new Tile(row, col,new Queen(piece.getColor()));
                    } else if (piece instanceof King){
                        board[row][col]=new Tile(row, col,new King(piece.getColor()));
                    }
                } else{
                    board[row][col]=new Tile(row, col,null);
                }
            }
        }
    }

    public Piece getPiece(int row, int col){
        return board[row][col].getPiece();
    }

    public King getKing(boolean color){
        Piece king=null;
        for(Tile[] tiles: board){
            for(Tile tile:tiles){
                if(tile.getPiece() instanceof King && tile.getPiece().getColor()==color){
                    king= tile.getPiece();
                }
            }
        }
        return (King)king;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void print(){
        for(int row=0;row<8;row++){
            for(int col=0;col<8;col++){
                if(board[row][col].getPiece()==null){
                    System.out.print("null ");
                }else {
                    System.out.print(board[row][col].getPiece().toString());
                }
            }
            System.out.println();
        }
    }
}
