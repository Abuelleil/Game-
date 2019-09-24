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

public class Super extends ActivatablePowerHero {

    public Super(Player player, Game game, String name) {
        super(player, game, name);
    }
    public boolean exceedsI() {
        if(this.getPosI()>=5||this.getPosI()<=1) {
            return true;
        }else return false;
    }
    public boolean exceedsJ() {
        if(this.getPosJ()>=4||this.getPosJ()<=2) {
            return true;
        }else return false;
    }
    public boolean friendly(Piece Target) {
        if(Target.getOwner() == this.getOwner()) {
            return true;
        }else return false;
    }
    public Piece Target(int I, int J) {
        return this.getGame().getCellAt(I, J).getPiece();
    }
    public boolean valid(int I, int J) {
        if(!(I > 6 || I < 0) && !(J > 5 || J < 0)) {
            if(this.getGame().getCellAt(I, J).getPiece()!=null && !friendly(this.getGame().getCellAt(I, J).getPiece())) {
                return true;
            }else
                return false;
        }else return false;

    }
    public void move(Direction r)throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
        switch (r) {
            case DOWN:
                this.moveDown();
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
            default:
                throw new UnallowedMovementException("Unallowed Movement ya captain", this, r);
        }
    }
    public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerTargetException, InvalidPowerDirectionException, OccupiedCellException {
        super.usePower(d, target, newPos);
        if (!(this.isPowerUsed())) {
            switch (d) {
                case DOWN:
                    if (this.valid(this.getPosI() + 1, this.getPosJ()))
                        this.attack(Target(this.getPosI() + 1, this.getPosJ()));
                    if (this.valid(this.getPosI() + 2, this.getPosJ()))
                        this.attack(Target(this.getPosI() + 2, this.getPosJ()));
                    this.setPowerUsed(true);
                    this.getGame().switchTurns();
                    break;
                case LEFT:
                    if (this.valid(this.getPosI(), this.getPosJ() - 1))
                        this.attack(Target(this.getPosI(), this.getPosJ() - 1));
                    if (this.valid(this.getPosI(), this.getPosJ() - 2))
                        this.attack(Target(this.getPosI(), this.getPosJ() - 2));
                    this.setPowerUsed(true);
                    this.getGame().switchTurns();
                    break;
                case RIGHT:
                    if (this.valid(this.getPosI(), this.getPosJ()+1))
                        this.attack(Target(this.getPosI(), this.getPosJ() + 1));
                    if (this.valid(this.getPosI(), this.getPosJ() + 2))
                        this.attack(Target(this.getPosI(), this.getPosJ() + 2));
                    this.setPowerUsed(true);
                    this.getGame().switchTurns();
                    break;
                case UP:
                    if (this.valid(this.getPosI() - 1, this.getPosJ()))
                        this.attack(Target(this.getPosI() - 1, this.getPosJ()));
                    if (this.valid(this.getPosI() - 2, this.getPosJ()))
                        this.attack(Target(this.getPosI() - 2, this.getPosJ()));
                    this.setPowerUsed(true);
                    this.getGame().switchTurns();
                    break;
                default:
                    throw new InvalidPowerDirectionException(
                            "bos howa el 3adi en el super beylawsh bs heya rules bedan eltezem bel directions", this, d);
            }
        }else {
            throw new PowerAlreadyUsedException("ali ali msh fara7 omak howa heya mara wahda",this);
        }
        this.setPowerUsed(true);
    }
}
