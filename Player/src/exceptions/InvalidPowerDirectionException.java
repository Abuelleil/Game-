package exceptions;

import model.game.Direction;
import model.pieces.Piece;

@SuppressWarnings("serial")
public class InvalidPowerDirectionException extends InvalidPowerUseException {

    private Direction direction;

    public InvalidPowerDirectionException( Piece trigger, Direction direction) {
        super( trigger);
        this.direction = direction;
    }

    public InvalidPowerDirectionException(String s, Piece trigger, Direction direction) {
        super(s, trigger);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

}
