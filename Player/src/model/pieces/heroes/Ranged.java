package model.pieces.heroes;
import java.awt.Point;

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

public class Ranged extends ActivatablePowerHero {

    public Ranged(Player player, Game game, String name) {
        super(player, game, name);
    }

    @Override
    public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
        switch (r) {
            case DOWN:
                this.moveDown();
                break;
            case DOWNLEFT:
                this.moveDownLeft();
                break;
            case DOWNRIGHT:
                this.moveDownRight();
                break;
            case LEFT:
                this.moveLeft();
                break;
            case RIGHT:
                this.moveRight();
                break;
            case UP:
                this.moveUp();
                break;
            case UPLEFT:
                this.moveUpLeft();
                break;
            case UPRIGHT:
                this.moveUpRight();
                break;
            default:
                throw new UnallowedMovementException("Unallowed Movement ya captain", this, r);
        }
    }

    public boolean nohit(Direction d) throws InvalidPowerDirectionException {
        boolean flag = true;
        int x = 0;
        int z;
        switch (d) {
            case DOWN:
                for (z = this.getPosI() + 1; z < 6; z++) {
                    if (this.getGame().getCellAt(z, this.getPosJ()) == null) {
                        x++;
                    }
                }
                if (x == Math.abs(z - this.getPosI())) {
                    flag = true;
                } else {
                    flag = false;
                }

            case LEFT:
                for (z = this.getPosJ() - 1; z > 0; z--) {
                    if (this.getGame().getCellAt(this.getPosI(), z - 1) != null) {

                        x++;
                    }
                }
                if (x == Math.abs(z - this.getPosJ())) {
                    flag = true;
                } else {
                    flag = false;
                }
            case RIGHT:
                for (z = this.getPosJ() + 1; z < 5; z++) {
                    if (this.getGame().getCellAt(this.getPosI(), z) != null) {
                        x++;
                    }
                }
                if (x == Math.abs(z - this.getPosJ())) {
                    flag = true;
                } else {
                    flag = false;
                }
                break;
            case UP:
                for (z = this.getPosI() - 1; z > 0; z--) {
                    if (this.getGame().getCellAt(z, this.getPosJ()) != null) {
                        x++;
                    }
                }
                if (x == Math.abs(z - this.getPosI())) {
                    flag = true;
                } else {
                    flag = false;
                }
            default:
//			throw new InvalidPowerDirectionException("msh mn hena", this, d);
        }
        return flag;
    }

    public boolean validI(Direction r) {
        if (r.equals(Direction.DOWN) && this.getPosI() == 6) {
            return false;
        } else if (r.equals(Direction.UP) && this.getPosI() == 0) {
            return false;
        } else
            return true;
    }

    public boolean validJ(Direction r) {
        if (r.equals(Direction.RIGHT) && this.getPosJ() == 5) {
            return false;
        } else if (r.equals(Direction.LEFT) && this.getPosJ() == 0) {
            return false;
        } else
            return true;
    }

    public Piece findTarget( Direction d) throws InvalidPowerDirectionException {
        switch(d) {
            case UP:
                for(int i = getPosI() - 1;i >= 0; i--) {
                    if(getGame().getCellAt(i, getPosJ()).getPiece() != null) {
                        if(getGame().getCellAt(i, getPosJ()).getPiece().getOwner() != getOwner()) {
                            return getGame().getCellAt(i, getPosJ()).getPiece();
                        }
                        else {
                            return null;
                        }
                    }
                }
                break;
            case DOWN:
                for(int i = getPosI() + 1;i < 7; i++) {
                    if(getGame().getCellAt(i, getPosJ()).getPiece() != null) {
                        if(getGame().getCellAt(i, getPosJ()).getPiece().getOwner() != getOwner()) {
                            return getGame().getCellAt(i, getPosJ()).getPiece();
                        }
                        else {
                            return null;
                        }
                    }
                }
                break;
            case LEFT:
                for(int i = getPosJ() - 1;i >= 0; i--) {
                    if(getGame().getCellAt(getPosI(), i).getPiece() != null) {
                        if(getGame().getCellAt(getPosI(), i).getPiece().getOwner() != getOwner()) {
                            return getGame().getCellAt(getPosI(), i).getPiece();
                        }
                        else {
                            return null;
                        }
                    }
                }
                break;
            case RIGHT:
                for(int i = getPosJ() + 1;i < 6; i++) {
                    if(getGame().getCellAt(getPosI(), i).getPiece() != null) {
                        if(getGame().getCellAt(getPosI(), i).getPiece().getOwner() != getOwner()) {
                            return getGame().getCellAt(getPosI(), i).getPiece();
                        }
                        else {
                            return null;
                        }
                    }
                }
                break;
            default:
                throw new InvalidPowerDirectionException(
                        "enta betestad 3asafir yala ? khlas et3amit ?", this, d);
        }
        return null;
    }

    public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException,
            InvalidPowerTargetException, InvalidPowerDirectionException, OccupiedCellException {
        super.usePower(d, target, newPos);
        if (!(this.isPowerUsed())) {
            Piece tbt = findTarget(d);
            if(tbt == null) {
                throw new InvalidPowerDirectionException(
                        "enta betestad 3asafir yala ? khlas et3amit ?", this, d);
            }
            else {
                attack(tbt);
                setPowerUsed(true);
            }

        } else {
            throw new PowerAlreadyUsedException("ali ali msh fara7 omak howa heya mara wahda", this);
        }
        this.getGame().switchTurns();
    }
}
