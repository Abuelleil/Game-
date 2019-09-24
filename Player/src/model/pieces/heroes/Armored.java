package model.pieces.heroes;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.sidekicks.SideKick;

@SuppressWarnings("unused")
public class Armored extends NonActivatablePowerHero {

    private boolean armorUp;

    public Armored(Player player, Game game, String name) {
        super(player, game, name);
        this.armorUp =true;
    }
    public Armored(Player player, Game game, String name, boolean armorUp) {
        super(player, game, name);
        this.armorUp = true;
    }

    @Override
    public void move(Direction r)throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
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

    public boolean isArmorUp() {
        return armorUp;
    }

    public void setArmorUp(boolean armorUp) {
        this.armorUp = armorUp;
    }
}

