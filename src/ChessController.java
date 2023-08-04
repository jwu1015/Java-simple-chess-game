import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessController implements ActionListener, MouseListener {
    private ChessModel model;
    private ChessView view;

    public ChessController(ChessModel m, ChessView v){
        this.model=m;
        this.view=v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(view.getNewGame())) {
            doNewGame();
        }
        else if (command.equals(view.getResign())) {
            doResign();
        } else if(command.equals(view.getDrawText())){
            if(view.isDrawPressed()) {
                doDraw();
            } else{
                view.setDrawPressed(true);
                if(model.getCurrentPlayer()){
                    view.setMessageText("White wants a draw || Click draw to accept.");
                }else{
                    view.setMessageText("Black wants a draw || Click draw to accept.");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (model.getGameOver())
            view.setMessageText("Click \"New Game\" to start a new game.");
        else {
            int col = (e.getX() - 5) / 70;
            int row = (e.getY() - 5) / 70;
            if (col >= 0 && col < 8 && row >= 0 && row < 8) {
                if(model.getSelectedPiece()==null&&model.getBoard().getBoard()[row][col].getPiece()!=null&&model.getBoard().getBoard()[row][col].getPiece().getColor()==model.getCurrentPlayer()){
                    model.setSelectedPiece(model.getBoard().getBoard()[row][col].getPiece());
                    if(model.getCurrentPlayer()){
                        view.setMessageText("White "+(model.getSelectedPiece().toString()));
                    }else{
                        view.setMessageText("Black "+(model.getSelectedPiece().toString()));
                    }
                } else {
                    try {
                        Tile temp=model.getSelectedPiece().getTile(model.getBoard());
                        view.setDrawPressed(false);
                        model.makeMove(model.getCurrentPlayer(), model.getSelectedPiece().getTile(model.getBoard()), model.getBoard().getBoard()[row][col]);
                        if(temp.getPiece()==null) {
                            model.setCurrentPlayer(!model.getCurrentPlayer());
                            model.setSelectedPiece(null);
                            if(model.getCurrentPlayer()){
                                if(model.getBoard().getKing(true).inCheck(model.getBoard())){
                                    view.setMessageText("Check! || White's turn");
                                }else{
                                    view.setMessageText("White's turn");
                                }
                            }else{
                                if(model.getBoard().getKing(false).inCheck(model.getBoard())){
                                    view.setMessageText("Check! || Black's turn");
                                }else{
                                    view.setMessageText("Black's turn");
                                }
                            }
                            model.setGameOver();
                            if(model.getGameOver()){
                                if(model.getCurrentPlayer()) {
                                    gameOver("Checkmate! || Black wins! || Click new game!");
                                }
                                else {
                                    gameOver("Checkmate! || White wins! || Click new game!");
                                }
                            }else if(model.getDraw()){
                                gameOver("Stalemate! || Click new game to start a new game!");
                            }
                        } else{
                            view.setMessageText("Please make a valid move.");
                            model.setSelectedPiece(null);
                        }
                    } catch(NullPointerException exception){
                        return;
                    }
                }
                view.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void doResign(){
        if (model.getGameOver()) {
            view.setMessageText("There is no game in progress.");
            return;
        }
        if (model.getCurrentPlayer()) {
            gameOver("White resigns || Black wins.");
        }
        else {
            gameOver("Black resigns || White wins.");
        }
        model.setIsGameOver(true);
    }

    public void gameOver(String str) {
        view.setMessageText(str);
        view.getNewGameButton().setEnabled(true);
        view.getResignButton().setEnabled(false);
        view.getDrawButton().setEnabled(false);
    }

    public void doNewGame() {
        if (!model.getGameOver()) {
            view.setMessageText("Finish the current game first!");
            return;
        }
        model.setBoard(new Board());
        view.setMessageText("White:  Make your move.");
        model.setCurrentPlayer(true);
        model.setSelectedPiece(null);
        model.setIsGameOver(false);
        model.setDraw(false);
        view.getNewGameButton().setEnabled(false);
        view.getDrawButton().setEnabled(true);
        view. getResignButton().setEnabled(true);
        view.repaint();
    }

    public void doDraw(){
        if (model.getGameOver()) {
            view.setMessageText("There is no game in progress.");
            return;
        }
        gameOver("Draw!");
        model.setIsGameOver(true);
    }

}
