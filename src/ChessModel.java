import java.awt.*;

public class ChessModel {

    private Board board;
    private Piece selectedPiece;
    private boolean currentPlayer;
    private boolean isGameOver;
    private boolean isDraw;

    public ChessModel(){
        board=new Board();
        currentPlayer=true;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Piece pieceAt(int row, int col) {
        return board.getPiece(row,col);
    }

    public void makeMove(boolean player, Tile start, Tile end){
        if(start.getPiece().getColor()==player){
            start.getPiece().move(board,start,end);
        }
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean getGameOver(){
        return isGameOver;
    }
    public void setIsGameOver(boolean b){
        isGameOver=b;
    }
    public void setGameOver() {
        if(board.getKing(true).isInMateOrStalemate(board)||board.getKing(false).isInMateOrStalemate(board)){
            if(board.getKing(true).inCheck(board)||board.getKing(false).inCheck(board)){
                isGameOver=true;
            } else{
                isDraw=true;
            }
        }
    }
    public boolean getDraw(){
        return isDraw;
    }
    public void setDraw(boolean b){
        isDraw=b;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

}
