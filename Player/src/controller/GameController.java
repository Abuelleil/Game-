package controller;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import view.BB;
import view.DCB;
import view.GUI;

public class GameController implements ActionListener {

    private Game game;
    private GUI gui;
    private String player1N;
    private String player2N;
    private Piece selectedPiece;
    private boolean teleport;
    private boolean power;
    private Piece selectedDead;
    private Piece selectedForTP;

    public GameController() {
        this.gui = new GUI(this);
        teleport = false;
        power = false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()) {
            case "close window":
                gui.dispose();
                break;
            case "NEW GAME yasta":
                start();
                break;
            case "Empty":
                if(selectedPiece.getClass().getSimpleName().equals("Tech") && power && teleport) {
                    try {
                        ((Tech)selectedPiece).usePower(null, selectedForTP, new Point(((BB)e.getSource()).getXcor(), ((BB)e.getSource()).getYcor()));
                        selectedPiece = null;
                        power = false;
                        teleport = false;
                    } catch (PowerAlreadyUsedException | InvalidPowerTargetException | InvalidPowerDirectionException
                            | OccupiedCellException | WrongTurnException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                        selectedForTP = null;
                        power = false;
                        teleport = false;
                    }
                    if (game.checkWinner()) {
                        gui.endgame();
                    } else {
                        gui.Game();
                    }
                }
                break;
            case "dead":
                selectedDead = ((DCB)e.getSource()).getPiece();
                break;
            case "next":
                entername();
                break;
            case "end game":
                gui.ending();
                break;
            case "Piece":
                try {
                    clickedPiece(e);
                } catch (OccupiedCellException | WrongTurnException e1) {
                    selectedPiece = null;
                    gui.dispError(e1);
                }
                break;
            case "power":
                power = (power) ? false : true;
                System.out.println(power);
                if (selectedPiece.getClass().getSimpleName().equals("Tech")) {
                    gui.techOptions();
                }
                break;
            case "teleport":
                teleport = true;
                System.out.println("Hoba");
                break;
            case "UP":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.UP);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                } else if (selectedPiece != null && power) {
                    castPower(e);
                }
                break;
            case "DOWN":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.DOWN);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                }else if (selectedPiece != null && power) {
                    System.out.println("Cast yasta");
                    castPower(e);
                }
                break;
            case "LEFT":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.LEFT);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                }else if (selectedPiece != null && power) {
                    castPower(e);
                }
                break;
            case "RIGHT":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.RIGHT);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                }else if (selectedPiece != null && power) {
                    castPower(e);
                }
                break;
            case "UPLEFT":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.UPLEFT);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                }else if (selectedPiece != null && power) {
                    castPower(e);
                }
                break;
            case "UPRIGHT":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.UPRIGHT);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                }else if (selectedPiece != null && power) {
                    castPower(e);
                }
                break;
            case "DOWNLEFT":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.DOWNLEFT);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                }else if (selectedPiece != null && power) {
                    castPower(e);
                }
                break;
            case "DOWNRIGHT":
                if (selectedPiece != null && !power) {
                    try {
                        selectedPiece.move(Direction.DOWNRIGHT);
                        gui.Game();
                        selectedPiece = null;
                        if (game.checkWinner()) {
                            gui.endgame();
                        } else {
                            gui.Game();
                        }
                    } catch (OccupiedCellException | WrongTurnException | UnallowedMovementException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                    }
                }else if (selectedPiece != null && power) {
                    castPower(e);
                }
                break;
            default:
                break;
        }

    }


    public void entername() {
        this.game = new Game(new Player(gui.getPl1().getText()), new Player(gui.getPl2().getText()));
        this.gui.Game();

    }

    public void start() {
        this.gui.nameselect();
    }


    public Game getGame() {
        return game;
    }

    public void clickedPiece(ActionEvent e) throws OccupiedCellException, WrongTurnException {
        System.out.println("Hena");
        if (selectedPiece == null) {
            this.selectedPiece = ((BB) e.getSource()).getPiece();
        }
        else if(selectedPiece.getClass().getSimpleName().equals("Tech") && power && !teleport) {
            try {
                ((Tech)selectedPiece).usePower(null, ((BB)e.getSource()).getPiece(), null);
                gui.Game();
                selectedPiece = null;
                power = false;
            } catch (PowerAlreadyUsedException | InvalidPowerTargetException | InvalidPowerDirectionException e1) {
                gui.dispError(e1);
                selectedPiece = null;
                power = false;
            }
        }
        else if(selectedPiece.getClass().getSimpleName().equals("Tech") && power && teleport) {
            selectedForTP = ((BB)e.getSource()).getPiece();
        }
    }


    public Direction getDirectionEmpty(ActionEvent e) {
        if (selectedPiece.getPosI() > ((BB) e.getSource()).getXcor()) {
            if (selectedPiece.getPosJ() > ((BB) e.getSource()).getYcor()) {
                return Direction.UPLEFT;
            } else if (selectedPiece.getPosJ() < ((BB) e.getSource()).getYcor()) {
                return Direction.UPRIGHT;
            } else {
                return Direction.UP;
            }
        } else if (selectedPiece.getPosI() < ((BB) e.getSource()).getXcor()) {
            if (selectedPiece.getPosJ() > ((BB) e.getSource()).getYcor()) {
                return Direction.DOWNLEFT;
            } else if (selectedPiece.getPosJ() < ((BB) e.getSource()).getYcor()) {
                return Direction.DOWNRIGHT;
            } else {
                return Direction.DOWN;
            }
        } else {
            if (selectedPiece.getPosJ() > ((BB) e.getSource()).getYcor()) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        }
    }

    public Direction getDirectionPiece(ActionEvent e) {
        if (selectedPiece.getPosI() > ((BB) e.getSource()).getPiece().getPosI()) {
            if (selectedPiece.getPosJ() > ((BB) e.getSource()).getPiece().getPosJ()) {
                return Direction.UPLEFT;
            } else if (selectedPiece.getPosJ() < ((BB) e.getSource()).getPiece().getPosJ()) {
                return Direction.UPRIGHT;
            } else {
                return Direction.UP;
            }
        } else if (selectedPiece.getPosI() < ((BB) e.getSource()).getPiece().getPosI()) {
            if (selectedPiece.getPosJ() > ((BB) e.getSource()).getPiece().getPosJ()) {
                return Direction.DOWNLEFT;
            } else if (selectedPiece.getPosJ() < ((BB) e.getSource()).getPiece().getPosJ()) {
                return Direction.DOWNRIGHT;
            } else {
                return Direction.DOWN;
            }
        } else {
            if (selectedPiece.getPosJ() > ((BB) e.getSource()).getPiece().getPosJ()) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        }
    }

    public void castPower(ActionEvent e) {
        switch (selectedPiece.getClass().getSimpleName()) {
            case "Super":
                try {
                    try {
                        ((Super) selectedPiece).usePower(directionStringToEnum(e), null, null);
                    } catch (OccupiedCellException | WrongTurnException e1) {
                        gui.dispError(e1);
                        gui.Game();
                        selectedPiece = null;
                    }
                } catch (PowerAlreadyUsedException | InvalidPowerTargetException | InvalidPowerDirectionException e1) {
                    gui.dispError(e1);
                    power = false;
                    selectedPiece = null;
                }
                if (game.checkWinner()) {
                    gui.endgame();
                } else {
                    gui.Game();
                }
                selectedPiece = null;
                break;
            case "Ranged":
                try {
                    try {
                        ((Ranged) selectedPiece).usePower(directionStringToEnum(e), null, null);
                        gui.Game();
                        selectedPiece = null;
                        power = false;
                    } catch (OccupiedCellException | WrongTurnException e1) {
                        gui.dispError(e1);
                        gui.Game();
                        selectedPiece = null;
                    }
                } catch (PowerAlreadyUsedException | InvalidPowerTargetException | InvalidPowerDirectionException e1) {
                    gui.dispError(e1);
                    power = false;
                    selectedPiece = null;
                }
                if (game.checkWinner()) {
                    gui.endgame();
                } else {
                    gui.Game();
                }
                selectedPiece = null;
                break;
            case "Medic":
                if(selectedDead != null) {
                    try {
                        ((Medic)selectedPiece).usePower(directionStringToEnum(e), selectedDead, null);
                        gui.Game();
                        selectedPiece = null;
                        power = false;
                    } catch (PowerAlreadyUsedException | InvalidPowerTargetException | InvalidPowerDirectionException
                            | OccupiedCellException | WrongTurnException e1) {
                        gui.dispError(e1);
                        selectedPiece = null;
                        power = false;
                    }
                }if (game.checkWinner()) {
                gui.endgame();
            } else {
                gui.Game();
            }
                break;
        }
        power = false;
    }

    public Direction directionStringToEnum(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "UP":
                return Direction.UP;
            case "DOWN":
                return Direction.DOWN;
            case "UPLEFT":
                return Direction.UPLEFT;
            case "UPRIGHT":
                return Direction.UPRIGHT;
            case "DOWNLEFT":
                return Direction.DOWNLEFT;
            case "DOWNRIGHT":
                return Direction.DOWNRIGHT;
            case "LEFT":
                return Direction.LEFT;
            default:
                return Direction.RIGHT;
        }
    }
    public static void main(String[] args) {
        new GameController();
    }
}
