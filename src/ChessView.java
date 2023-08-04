import java.awt.*;

import javax.swing.*;

public class ChessView extends JPanel{
    private ChessModel model;
    private ChessController controller;
    private JButton newGame;
    private JButton resign;
    private JButton draw;
    private JLabel message;
    private boolean drawPressed;


    public ChessView(ChessModel m){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(600,600));
        this.setBounds(0,0,600,650);
        this.model=m;
        this.controller=new ChessController(model, this);
        this.addMouseListener(controller);


        resign = new JButton("Resign");
        resign.addActionListener(controller);

        newGame = new JButton("New Game");
        newGame.addActionListener(controller);

        draw = new JButton("Draw");
        draw.addActionListener(controller);

        message = new JLabel("",JLabel.CENTER);
        message.setFont(new  Font("Serif", Font.BOLD, 14));
        message.setForeground(Color.black);

        newGame.setBounds(30, 570, 120, 30);
        resign.setBounds(210, 570, 120, 30);
        draw.setBounds(380,570,120,30);
        message.setBounds(95, 600, 350, 30);
        this.add(newGame);
        this.add(resign);
        this.add(draw);
        this.add(message);
    }

    public void paintComponent(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == col % 2) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(5 + col*70, 5 + row*70, 70, 70);
                if(!(model.getBoard().getBoard()[row][col].isEmpty(row,col))){
                    model.pieceAt(row,col).draw(g,5+col*70,5+row*70);
                }
            }
        }
    }

    public String getNewGame() {
        return newGame.getText();
    }

    public JButton getNewGameButton() {
        return newGame;
    }

    public String getResign() {
        return resign.getText();
    }

    public JButton getResignButton() {
        return resign;
    }

    public String getDrawText() {
        return draw.getText();
    }

    public JButton getDrawButton() {
        return draw;
    }

    public boolean isDrawPressed() {
        return drawPressed;
    }

    public void setDrawPressed(boolean drawPressed) {
        this.drawPressed = drawPressed;
    }

    public void setMessageText(String text){
        message.setText(text);
    }
}
