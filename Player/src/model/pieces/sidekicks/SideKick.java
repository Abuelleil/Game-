package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

    public SideKick(Player player, Game game, String name) {
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
    public void attack(Piece target) {
        if (target instanceof Hero && this instanceof SideKick) {
            switch (target.getClass().getSimpleName()) {
                case "Armored":
                    Armored a=new Armored(this.getOwner(), getGame(), getName());
                    a.setPosI(target.getPosI());
                    a.setPosJ(target.getPosJ());
                    if (!((Armored) target).isArmorUp()) {
                        getGame().getCellAt(this.getPosI(), this.getPosJ())
                                .setPiece(a);
                        getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                        this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);
                        target.getOwner().getDeadCharacters().add(target);
                        this.getGame().checkWinner();
                    } else {
                        ((Armored) target).setArmorUp(false);
                    }
                    break;

                case "Medic":
                    Medic m=new Medic(this.getOwner(), getGame(), getName());
                    m.setPosI(target.getPosI());
                    m.setPosJ(target.getPosJ());
                    getGame().getCellAt(this.getPosI(), this.getPosJ())
                            .setPiece(m);
                    getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                    this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);
                    target.getOwner().getDeadCharacters().add(target);
                    this.getGame().checkWinner();
                    break;

                case "Ranged":
                    Ranged r=new Ranged(this.getOwner(), getGame(), getName());
                    r.setPosI(target.getPosI());
                    r.setPosJ(target.getPosJ());
                    getGame().getCellAt(this.getPosI(), this.getPosJ())
                            .setPiece(r);
                    getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                    this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);
                    target.getOwner().getDeadCharacters().add(target);
                    this.getGame().checkWinner();
                    break;

                case "Speedster":
                    Speedster s = new Speedster(this.getOwner(), getGame(), getName());
                    s.setPosI(target.getPosI());
                    s.setPosJ(target.getPosJ());
                    getGame().getCellAt(this.getPosI(), this.getPosJ())
                            .setPiece(s);
                    getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                    this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);
                    target.getOwner().getDeadCharacters().add(target);
                    this.getGame().checkWinner();
                    break;

                case "Super":
                    Super sups=new Super(this.getOwner(), getGame(), getName());
                    sups.setPosI(target.getPosI());
                    sups.setPosJ(target.getPosJ());
                    getGame().getCellAt(this.getPosI(), this.getPosJ())
                            .setPiece(sups);
                    getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                    this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);
                    target.getOwner().getDeadCharacters().add(target);
                    this.getGame().checkWinner();
                    break;

                case "Tech":
                    Tech t=new Tech(this.getOwner(), getGame(), getName());
                    t.setPosI(target.getPosI());
                    t.setPosJ(target.getPosJ());
                    getGame().getCellAt(this.getPosI(), this.getPosJ())
                            .setPiece(t);
                    getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
                    this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);
                    target.getOwner().getDeadCharacters().add(target);
                    this.getGame().checkWinner();
                    break;
            }

        } else {
            this.getOwner().setSideKilled(this.getOwner().getSideKilled() + 1);
            getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
            target.getOwner().getDeadCharacters().add(target);
            if ((this.getOwner().getSideKilled() % 2) == 0)
                this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);
            this.getGame().checkWinner();
        }
    }
}
