package model.pieces.heroes;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;

public class Speedster extends NonActivatablePowerHero {

    public Speedster(Player player, Game game, String name) {
        super(player, game, name);
    }
    public int warpI(int x) {
        int z=6;
        if (x > 6) {
            x = x - z - 1;
            return x;
        } else if (x < 0) {
            x = x + z + 1;
            return x;
        } else {
            return x;
        }
    }

    public int warpJ(int y) {
        int z=5;
        if (y > 5) {
            y = y - z - 1;
            return y;
        } else if (y < 0) {
            y = y + z + 1;
            return y;
        } else {
            return y;
        }
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

    public void moveDown() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(2, 0, Direction.DOWN);
    }

    public void moveUp() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(-2, 0, Direction.UP);
    }

    public void moveRight() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(0, 2, Direction.RIGHT);
    }

    public void moveLeft() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(0, -2, Direction.LEFT);
    }

    public void moveUpLeft() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(-2, -2, Direction.UPLEFT);
    }

    public void moveUpRight() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(-2, 2, Direction.UPRIGHT);
    }

    public void moveDownRight() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(2, 2, Direction.DOWNRIGHT);
    }

    public void moveDownLeft() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(2, -2, Direction.DOWNLEFT);
    }

    public void helperMove(int x, int y, Direction r) throws OccupiedCellException {
        if (this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y)).getPiece() != null) {
            if (this.getOwner() == this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y))
                    .getPiece().getOwner()) {
                throw new OccupiedCellException("ana ma3ak ya7wal", this, r);
            } else {
                if (this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y))
                        .getPiece() instanceof Armored) {
                    if (((Armored) this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y))
                            .getPiece()).isArmorUp()) {
                        this.attack(this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y))
                                .getPiece());
                    } else {
                        this.attack(this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y))
                                .getPiece());
                        this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y)).setPiece(this);
                        this.getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
                        this.setPosI(warpI(this.getPosI() + x));
                        this.setPosJ(warpJ(this.getPosJ() + y));
                    }
                } else {
                    this.attack(
                            this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y)).getPiece());
                    this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y)).setPiece(this);
                    this.getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
                    this.setPosI(warpI(this.getPosI() + x));
                    this.setPosJ(warpJ(this.getPosJ() + y));
                }
            }
        } else {
            this.getGame().getCellAt(warpI(this.getPosI() + x), warpJ(this.getPosJ() + y)).setPiece(this);
            this.getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
            this.setPosI(warpI(this.getPosI() + x));
            this.setPosJ(warpJ(this.getPosJ() + y));
        }
        this.getGame().switchTurns();
    }

}

