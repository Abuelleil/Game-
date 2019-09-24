package model.game;


import java.util.ArrayList;
import java.util.Collections;

import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game {

    private final int payloadPosTarget = 6;
    private final int boardWidth = 6;
    private final int boardHeight = 7;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Cell[][] board;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        board = new Cell[boardHeight][boardWidth];
        assemblePieces();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getPayloadPosTarget() {
        return payloadPosTarget;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
    public void assemblePieces() {
        ArrayList<Integer> Locations = new ArrayList<>();
        ArrayList<Integer> Locations1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Locations.add(i);
            Locations1.add(i);
        }
        for(int i=0;i<boardHeight;i++) {
            for(int j=0;j<boardWidth;j++) {
                board[i][j]=new Cell();
            }
        }
        Collections.shuffle(Locations);
        Collections.shuffle(Locations1);
        board[4][5] = new Cell(new SideKickP1(this, "KingsGlaive"));
        this.getCellAt(4,5).getPiece().setPosI(4);
        this.getCellAt(4,5).getPiece().setPosJ(5);

        board[4][4] = new Cell(new SideKickP1(this, "KingsGlaive"));
        this.getCellAt(4,4).getPiece().setPosI(4);
        this.getCellAt(4,4).getPiece().setPosJ(4);

        board[4][3] = new Cell(new SideKickP1(this, "KingsGlaive"));
        this.getCellAt(4,3).getPiece().setPosI(4);
        this.getCellAt(4,3).getPiece().setPosJ(3);

        board[4][2] = new Cell(new SideKickP1(this, "KingsGlaive"));
        this.getCellAt(4,2).getPiece().setPosI(4);
        this.getCellAt(4,2).getPiece().setPosJ(2);

        board[4][1] = new Cell(new SideKickP1(this, "KingsGlaive"));
        this.getCellAt(4,1).getPiece().setPosI(4);
        this.getCellAt(4,1).getPiece().setPosJ(1);

        board[4][0] = new Cell(new SideKickP1(this, "KingsGlaive"));
        this.getCellAt(4,0).getPiece().setPosI(4);
        this.getCellAt(4,0).getPiece().setPosJ(0);

        board[5][Locations.get(0)] = new Cell(new Armored(player1, this, "Gladious"));
        board[5][Locations.get(0)].getPiece().setPosI(5);
        board[5][Locations.get(0)].getPiece().setPosJ(Locations.get(0));
        board[5][Locations.get(0)].getPiece().setPosJ(Locations.get(0));

        board[5][Locations.get(1)] = new Cell(new Medic(player1, this, "Lunafreya"));
        board[5][Locations.get(1)].getPiece().setPosI(5);
        board[5][Locations.get(1)].getPiece().setPosJ(Locations.get(1));
        board[5][Locations.get(1)].getPiece().setPosJ(Locations.get(1));

        board[5][Locations.get(2)] = new Cell(new Speedster(player1, this, "Ignis"));
        board[5][Locations.get(2)].getPiece().setPosI(5);
        board[5][Locations.get(2)].getPiece().setPosJ(Locations.get(2));
        board[5][Locations.get(2)].getPiece().setPosJ(Locations.get(2));

        board[5][Locations.get(3)] = new Cell(new Ranged(player1, this, "Prompto"));
        board[5][Locations.get(3)].getPiece().setPosI(5);
        board[5][Locations.get(3)].getPiece().setPosJ(Locations.get(3));
        board[5][Locations.get(3)].getPiece().setPosJ(Locations.get(3));

        board[5][Locations.get(4)] = new Cell(new Super(player1, this, "Noctis"));
        board[5][Locations.get(4)].getPiece().setPosI(5);
        board[5][Locations.get(4)].getPiece().setPosJ(Locations.get(4));
        board[5][Locations.get(4)].getPiece().setPosJ(Locations.get(4));

        board[5][Locations.get(5)] = new Cell(new Tech(player1, this, "Cindy"));
        board[5][Locations.get(5)].getPiece().setPosI(5);
        board[5][Locations.get(5)].getPiece().setPosJ(Locations.get(5));
        board[5][Locations.get(5)].getPiece().setPosJ(Locations.get(5));

        board[2][5] = new Cell(new SideKickP2(this,"Magiteck"));
        this.getCellAt(2,5).getPiece().setPosI(2);
        this.getCellAt(2,5).getPiece().setPosJ(5);

        board[2][4] = new Cell(new SideKickP2(this,"Magiteck"));
        this.getCellAt(2,4).getPiece().setPosI(2);
        this.getCellAt(2,4).getPiece().setPosJ(4);

        board[2][3] = new Cell(new SideKickP2(this,"Magiteck"));
        this.getCellAt(2,3).getPiece().setPosI(2);
        this.getCellAt(2,3).getPiece().setPosJ(3);

        board[2][2] = new Cell(new SideKickP2(this,"Magiteck"));
        this.getCellAt(2,2).getPiece().setPosI(2);
        this.getCellAt(2,2).getPiece().setPosJ(2);

        board[2][1] = new Cell(new SideKickP2(this,"Magiteck"));
        this.getCellAt(2,1).getPiece().setPosI(2);
        this.getCellAt(2,1).getPiece().setPosJ(1);

        board[2][0] = new Cell(new SideKickP2(this,"Magiteck"));
        this.getCellAt(2,0).getPiece().setPosI(2);
        this.getCellAt(2,0).getPiece().setPosJ(0);

        board[1][Locations1.get(0)] = new Cell(new Armored(player2, this, "Ledolas"));
        board[1][Locations1.get(0)].getPiece().setPosI(1);
        board[1][Locations1.get(0)].getPiece().setPosJ(Locations1.get(0));
        board[1][Locations1.get(0)].getPiece().setPosJ(Locations1.get(0));

        board[1][Locations1.get(1)] = new Cell(new Medic(player2, this, "Ardyn"));
        board[1][Locations1.get(1)].getPiece().setPosI(1);
        board[1][Locations1.get(1)].getPiece().setPosJ(Locations1.get(1));
        board[1][Locations1.get(1)].getPiece().setPosJ(Locations1.get(1));

        board[1][Locations1.get(2)] = new Cell(new Speedster(player2, this, "Aranea"));
        board[1][Locations1.get(2)].getPiece().setPosI(1);
        board[1][Locations1.get(2)].getPiece().setPosJ(Locations1.get(2));
        board[1][Locations1.get(2)].getPiece().setPosJ(Locations1.get(2));

        board[1][Locations1.get(3)] = new Cell(new Ranged(player2, this, "Verstael"));
        board[1][Locations1.get(3)].getPiece().setPosI(1);
        board[1][Locations1.get(3)].getPiece().setPosJ(Locations1.get(3));
        board[1][Locations1.get(3)].getPiece().setPosJ(Locations1.get(3));

        board[1][Locations1.get(4)] = new Cell(new Super(player2, this, "Ravus"));
        board[1][Locations1.get(4)].getPiece().setPosI(1);
        board[1][Locations1.get(4)].getPiece().setPosJ(Locations1.get(4));
        board[1][Locations1.get(4)].getPiece().setPosJ(Locations1.get(4));

        board[1][Locations1.get(5)] = new Cell(new Tech(player2, this, "Ironman"));
        board[1][Locations1.get(5)].getPiece().setPosI(1);
        board[1][Locations1.get(5)].getPiece().setPosJ(Locations1.get(5));
        board[1][Locations1.get(5)].getPiece().setPosJ(Locations1.get(5));
    }

    public Cell getCellAt(int i, int j) {
        return board[i][j];
    }

    public void switchTurns() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else if (currentPlayer == player2) {
            currentPlayer = player1;
        }
    }

    public boolean checkWinner() {
        if (player1.getPayloadPos() >= 6 || player2.getPayloadPos() >= 6) {
            return true;
        } else {
            return false;
        }
    }
}