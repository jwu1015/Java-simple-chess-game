import javax.swing.*;
import java.awt.*;

public class ChessGame extends JFrame{
    private ChessController controller;
    private ChessView view;
    private ChessModel model;
    public ChessGame(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(570, 650);
        this.setResizable(false);
        model=new ChessModel();
        view=new ChessView(model);
        controller=new ChessController(model,view);
        this.setContentPane(view);
    }
}
