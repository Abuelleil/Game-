package model.game;

import model.pieces.Piece;

public class Cell {

    private Piece piece;

    public Cell() {

    }

    public Cell(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}
