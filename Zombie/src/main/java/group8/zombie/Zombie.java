package group8.zombie;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.entityparts.PositionPart;
import group8.common.playercommon.IPlayerService;
import group8.commonenemy.enemy.Enemy;
import group8.commonenemy.enemy.EntityDamage;
import group8.commonenemy.enemy.Rating;
import java.util.Random;
import org.openide.util.Lookup;

/**
 * Super class for zombies. Only sets the entity type to be a zombie.
 *
 * @author group 8
 */
public abstract class Zombie extends Enemy {

    private EntityDamage damage;
    private Random r = new Random();

    public Zombie(Rating rating) {
        super(rating);
        this.setType(EntityType.ZOMBIE);
    }

    /**
     * Method to set the x value of a new zombie with defined distance from player
     * @param gameData games gamedata
     * @param distance Wanted distance as float
     * @return Return the x value the zombie should have as float
     */
    public float setX(GameData gameData, float distance) {
        float playerX = this.getPlayerPos(gameData).getX();
        float x = r.nextInt((int) (((gameData.getDisplayWidth()) - (gameData.getDisplayWidth() / 2))
                + playerX));

        //If zombie is too close to player, move it.
        if (x >= playerX) {
            x += distance;
        } else {
            x -= distance;
        }

        //If zombie is over one of the edges move it inside
        if (x < 40) {
            x = 40;
        }

        if (x > 1500) {
            x = 1500;
        }

        return x;
    }

     /**
     * Method to set the y value of a new zombie with defined distance from player
     * @param gameData games gamedata
     * @param distance Wanted distance as float
     * @return Return the y value the zombie should have as float
     */
    public float setY(GameData gameData, float distance) {
        float playerY = this.getPlayerPos(gameData).getY();

        float y = r.nextInt((int) (((gameData.getDisplayHeight()) - (gameData.getDisplayHeight() / 2))
                + playerY));

        //If zombie is too close to player, move it.
        if (y >= playerY) {
            y += distance;
        } else {
            y -= distance;
        }

        //If zombie is over one of the edges move it inside
        if (y < 40) {
            y = 40;
        }

        if (y > 1100) {
            y = 1100;
        }
        return y;
    }

    //Method to get the position part of the player. Returns the positionpart
    private PositionPart getPlayerPos(GameData gameData) {
        Entity player;
        try {
            Lookup lookup = Lookup.getDefault();
            IPlayerService ps = lookup.lookup(IPlayerService.class);
            player = ps.getPlayer();
        } catch (NullPointerException ex) {
            Random r = new Random();
            float x = r.nextFloat() * (gameData.getDisplayWidth() / 2) - 1;
            float y = r.nextFloat() * (gameData.getDisplayHeight() / 2) - 1;
            PositionPart pos = new PositionPart(x, y, 0);
            player = new Entity();
            player.add(pos);
        }
        return player.getPart(PositionPart.class);
    }

}
