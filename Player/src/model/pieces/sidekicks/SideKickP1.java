package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP1 extends SideKick {

    public SideKickP1(Game game, String name) {
        super(game.getPlayer1(), game, name);
    }
    @Override
    public void move(Direction r)throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
        switch (r) {
            case UPLEFT:
                this.moveUpLeft();
                break;
            case UPRIGHT:
                this.moveUpRight();
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

}
