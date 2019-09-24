package model.pieces.heroes;
import java.awt.Point;
import java.util.ArrayList;

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
public class Medic extends ActivatablePowerHero {

    public Medic(Player player, Game game, String name) {
        super(player, game, name);
    }

    public boolean validCell(int I, int J) {
        if (this.getGame().getCellAt(I, J).getPiece() == null) {
            return true;
        } else
            return false;
    }

    @Override
    public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
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

    public Piece valid(Piece x) {
        if (this.getGame().getCurrentPlayer().getDeadCharacters().contains(x)) {
            return x;
        } else
            return null;
    }

    public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException,
            InvalidPowerTargetException, InvalidPowerDirectionException, WrongTurnException, OccupiedCellException {
        super.usePower(d, target, newPos);
        if ((this.isPowerUsed())) {
            throw new PowerAlreadyUsedException("ali ali msh fara7 omak howa heya mara wahda", this);
        }
        if (target.getOwner() != this.getGame().getCurrentPlayer()) {
            throw new InvalidPowerTargetException("msh da ya habibi", this, target);
        }
        if (!(this.getOwner().getDeadCharacters().contains(target))) {
            throw new InvalidPowerTargetException("yabny enta a7wal maho 3ayesh odamak aho", this, target);
        }
        int x = this.getPosI();
        int y = this.getPosJ();
        switch (d) {
            case DOWN:
                x++;
                break;
            case DOWNLEFT:
                x++;
                y--;
                break;
            case DOWNRIGHT:
                x++;
                y++;
                break;
            case LEFT:
                y--;
                break;
            case RIGHT:
                y++;
                break;
            case UP:
                x--;
                break;
            case UPLEFT:
                x--;
                y--;
                break;
            case UPRIGHT:
                x--;
                y++;
                break;
        }
        if (validCell(warpI(x), warpJ(y))) {
            this.getOwner().getDeadCharacters().remove(target);
            this.getGame().getCellAt(warpI(x), warpJ(y)).setPiece(target);
            target.setPosI(warpI(x));
            target.setPosJ(warpJ(y));
        } else {
            throw new InvalidPowerTargetException("fih wahed wa2ef", this, target);
        }
        if(target instanceof ActivatablePowerHero) {
            ((ActivatablePowerHero) target).setPowerUsed(false);
        }
        this.setPowerUsed(true);
        this.getGame().switchTurns();

    }
}
