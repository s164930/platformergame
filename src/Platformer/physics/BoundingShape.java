/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Platformer.physics;

import Platformer.level.tile.Tile;
import java.util.ArrayList;

/**
 *
 * @author vikto
 */
public abstract class BoundingShape {

    public boolean checkCollision(BoundingShape bv) {
        if (bv instanceof ABoundingRect) {
            return checkCollision((ABoundingRect) bv);
        }
        return false;
    }

    public abstract boolean checkCollision(ABoundingRect box);

    public abstract void updatePosition(float newX, float newY);

    public abstract void movePosition(float x, float y);

    public abstract ArrayList<Tile> getTilesOccupying(Tile[][] tiles);

    public abstract ArrayList<Tile> getGroundTiles(Tile[][] tiles);

}
