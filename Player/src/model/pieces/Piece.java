package model.pieces;

import exceptions.OccupiedCellException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.sidekicks.SideKick;

public abstract class Piece implements Movable {

    private String name;
    private Player owner;
    private Game game;
    private int posI;
    private int posJ;

    public Piece(Player player, Game game, String name) {
        this.owner = player;
        this.game = game;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public Game getGame() {
        return game;
    }

    public int getPosI() {
        return posI;
    }

    public void setPosI(int posI) {
        this.posI = posI;
    }

    public int getPosJ() {
        return posJ;
    }

    public void setPosJ(int posJ) {
        this.posJ = posJ;
    }

    public void moveDown() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(1, 0, Direction.DOWN);
    }

    public void moveUp() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(-1, 0, Direction.UP);
    }

    public void moveRight() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(0, 1, Direction.RIGHT);
    }

    public void moveLeft() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(0, -1, Direction.LEFT);
    }

    public void moveUpLeft() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(-1, -1, Direction.UPLEFT);
    }

    public void moveUpRight() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(-1, 1, Direction.UPRIGHT);
    }

    public void moveDownRight() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(1, 1, Direction.DOWNRIGHT);
    }

    public void moveDownLeft() throws WrongTurnException, OccupiedCellException {
        if (this.getOwner() != this.getGame().getCurrentPlayer())
            throw new WrongTurnException("estano dorko ya bahayem ya bahayem", this);
        helperMove(1, -1, Direction.DOWNLEFT);
    }

    public void helperMove(int x, int y, Direction r) throws OccupiedCellException {
        if (this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).getPiece() != null) {
            if (this.owner == this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).getPiece().getOwner()) {
                throw new OccupiedCellException("yasta ana ma3ak", this, r);
            } else {
                if (this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).getPiece() instanceof Armored) {
                    if (((Armored) this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).getPiece())
                            .isArmorUp()) {
                        this.attack(this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).getPiece());
                    } else {
                        this.attack(this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).getPiece());
                        this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).setPiece(getGame().getCellAt(this.posI, this.posJ).getPiece());
                        this.game.getCellAt(this.posI, this.posJ).setPiece(null);
                        this.setPosI(warpI(this.posI + x));
                        this.setPosJ(warpJ(this.posJ + y));
                    }
                } else {
                    this.attack(this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).getPiece());
                    this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).setPiece(getGame().getCellAt(this.posI, this.posJ).getPiece());
                    this.game.getCellAt(this.posI, this.posJ).setPiece(null);
                    this.setPosI(warpI(this.posI + x));
                    this.setPosJ(warpJ(this.posJ + y));
                }
            }
        } else {
            this.game.getCellAt(warpI(this.posI + x), warpJ(this.posJ + y)).setPiece(getGame().getCellAt(this.posI, this.posJ).getPiece());
            this.game.getCellAt(this.posI, this.posJ).setPiece(null);
            this.setPosI(warpI(this.posI + x));
            this.setPosJ(warpJ(this.posJ + y));
        }
        this.game.switchTurns();
    }

    public int warpI(int x) {
        int z = 6;
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
        int z = 5;
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

    public void attack(Piece target) {
        boolean hadarmor = false;
        if (target instanceof SideKick) {
            this.owner.setSideKilled(this.owner.getSideKilled() + 1);
            getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
            if ((this.owner.getSideKilled() % 2) == 0) {
                this.owner.setPayloadPos(this.owner.getPayloadPos() + 1);
            }
            target.owner.getDeadCharacters().add(target);
        } else if (target instanceof Hero) {
            if (target instanceof Armored) {
                if (((Armored) target).isArmorUp()) {
                    ((Armored) target).setArmorUp(false);
                    hadarmor = true;
                } else {
                    this.owner.setPayloadPos(this.owner.getPayloadPos() + 1);
                    target.owner.getDeadCharacters().add(target);
                    getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                }
            }
            else {
                this.owner.setPayloadPos(this.owner.getPayloadPos() + 1);
                target.owner.getDeadCharacters().add(target);
                getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
            }
        }
        if (!(hadarmor))
            game.checkWinner();
    }
}
