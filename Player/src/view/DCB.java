package view;


import javax.swing.JButton;

import model.pieces.Piece;

public class DCB extends JButton{

    private Piece piece;

    public DCB(String name, Piece piece) {
        super(name);
        this.setPiece(piece);

    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
