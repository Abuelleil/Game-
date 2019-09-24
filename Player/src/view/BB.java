package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.pieces.Piece;

public class BB extends JButton {
    private Piece piece;
    private int xcor;
    private int ycor;

    public BB(String message, Piece p) {
        super(message);
        this.piece = p;
    }

    public BB(ImageIcon i, Piece p) {
        super(i);
        this.piece = p;
    }

    public BB(String message, int x, int y) {
        super(message);
        this.xcor = x;
        this.ycor = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getYcor() {
        return ycor;
    }

    public void setYcor(int ycor) {
        this.ycor = ycor;
    }

    public int getXcor() {
        return xcor;
    }

    public void setXcor(int xcor) {
        this.xcor = xcor;
    }
}
