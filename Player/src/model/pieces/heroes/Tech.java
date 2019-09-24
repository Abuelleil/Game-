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
import model.pieces.sidekicks.SideKick;

@SuppressWarnings("unused")
public class Tech extends ActivatablePowerHero {

    public Tech(Player player, Game game, String name) {
        super(player, game, name);
    }

    public boolean validCell(int I, int J) {
        if (this.getGame().getCellAt(I, J).getPiece() == null) {
            return true;
        } else
            return false;
    }

    public boolean valid(Point newPos) {
        return validCell(newPos.x, newPos.y);
    }

    public boolean validup(int I) {
        if (I > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validdown(int J) {
        if (J < 6) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
        switch (r) {
            case DOWNLEFT:
                this.moveDownLeft();
                break;
            case DOWNRIGHT:
                this.moveDownRight();
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

    public boolean passive(Piece target) {
        if (target instanceof Speedster || target instanceof SideKick) {
            return false;
        } else {
            return true;
        }
    }

    public boolean occupied(int I, int J) {
        if (this.getGame().getCellAt(I, J).getPiece() != null)
            return true;
        else
            return false;
    }
    // public boolean enemy(Piece target) {
    // if(target.getOwner()!=this.getOwner()) return true;
    // else return false;
    // }

    public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException,
            InvalidPowerTargetException, InvalidPowerDirectionException, WrongTurnException, OccupiedCellException {
        super.usePower(d, target, newPos);
        if ((this.isPowerUsed())) {
            throw new PowerAlreadyUsedException("ali ali msh fara7 omak howa heya mara wahda", this);
        }
        if (newPos == null) {
            if (target.getOwner() != this.getOwner()) {
                if (target instanceof Armored) {
                    if (((Armored) target).isArmorUp()) {
                        ((Armored) target).setArmorUp(false);
                    } else {
                        throw new InvalidPowerTargetException("el sheild asn wa2e3 ya raye2", this, target);
                    }
                } else if (target instanceof ActivatablePowerHero) {
                    if (!((ActivatablePowerHero) target).isPowerUsed()) {
                        ((ActivatablePowerHero) target).setPowerUsed(true);
                    } else {
                        throw new InvalidPowerTargetException("ba7 hena ya negm el power kha", this, target);
                    }
                }
            } else if (target.getOwner() == this.getOwner()) {
                if (target instanceof ActivatablePowerHero && !(target instanceof Armored)) {
                    if (((ActivatablePowerHero) target).isPowerUsed()) {
                        ((ActivatablePowerHero) target).setPowerUsed(false);
                    } else {
                        throw new InvalidPowerTargetException("lsa masta5demhash", this, target);
                    }
                }
                if (target instanceof Armored) {
                    if (!((Armored) target).isArmorUp()) {
                        ((Armored) target).setArmorUp(true);
                    } else {
                        throw new InvalidPowerTargetException("lsa mawa2a3housh", this, target);
                    }
                }
            }
        } else {
            if (this.getOwner() == target.getOwner()) {
                if (valid(newPos)) {
                    this.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                    this.getGame().getCellAt(newPos.x, newPos.y).setPiece(target);
                    target.setPosI(newPos.x);
                    target.setPosJ(newPos.y);
                } else {
                    throw new InvalidPowerTargetException("yasta 3eib", this, target);
                }
            } else {
                throw new InvalidPowerTargetException("yabni fata7 ba2a da msh ma3ak", this, target);
            }
        }
        this.setPowerUsed(true);
    }
}
