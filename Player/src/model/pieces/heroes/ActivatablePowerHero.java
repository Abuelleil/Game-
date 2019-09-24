package model.pieces.heroes;


import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public abstract class ActivatablePowerHero extends Hero {

    private boolean powerUsed;

    public ActivatablePowerHero(Player player, Game game, String name) {
        super(player, game, name);
        this.powerUsed = false;
    }

    public boolean isPowerUsed() {
        return powerUsed;
    }

    public void setPowerUsed(boolean powerUsed) {
        this.powerUsed = powerUsed;
    }

    public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerTargetException, InvalidPowerDirectionException, OccupiedCellException{
        if (this.getOwner()!=this.getGame().getCurrentPlayer())
            throw  new WrongTurnException ("estana dorko ya bahayem ya bahayem", this);
    }
}
