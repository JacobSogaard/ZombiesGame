/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.healthsystem;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.World;
import group8.common.data.entityparts.DamagePart;
import group8.common.data.entityparts.LifePart;
import group8.common.services.IWhoHaveCollidedService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IWhoHaveCollidedService.class)})
public class DamageSystem implements IWhoHaveCollidedService {

    public DamageSystem() {
    }

    /**
     * Takes two entities and takes the damage from both entities if they have
     * one and subtracts it from the other entitys lifepart.
     *
     * @param entity1
     * @param entity2
     */
    @Override
    public void collisionDetected(Entity entity1, Entity entity2, World world) {
        DamagePart entity1Damage = entity1.getPart(DamagePart.class);
        LifePart entity1Life = entity1.getPart(LifePart.class);
        DamagePart entity2Damage = entity2.getPart(DamagePart.class);
        LifePart entity2Life = entity2.getPart(LifePart.class);
        
        
        if (!(entity1.getType() == EntityType.ZOMBIE && entity2.getType() == EntityType.ZOMBIE)) {
            this.entityCheck(entity1Damage, entity2Life);
            this.entityCheck(entity2Damage, entity1Life);
        }
        this.checkRemoveEntities(entity1Life, entity1, world);
        this.checkRemoveEntities(entity2Life, entity2, world);

    }

    private void checkRemoveEntities(LifePart entityLife, Entity entity, World world) {
        if (entityLife != null) {
            if (entityLife.getLife() <= 0) {
                world.removeEntity(entity);
            }
        }
    }

    private void entityCheck(DamagePart damagePart, LifePart lifePart) {
        if (damagePart != null && lifePart != null) {
            lifePart.setLife(lifePart.getLife() - damagePart.getDamage());
        }
    }

}
