/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.healthsystem;

import group8.common.data.Entity;
import group8.common.data.entityparts.DamagePart;
import group8.common.data.entityparts.LifePart;
import group8.common.services.IDamageService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IDamageService.class)})
public class DamageSystem implements IDamageService {
    public DamageSystem(){
    }

    @Override
    public void dealDamage(Entity entity1, Entity entity2) {
        DamagePart entity1Damage = entity1.getPart(DamagePart.class);
        LifePart entity1Life = entity1.getPart(LifePart.class);
        DamagePart entity2Damage = entity2.getPart(DamagePart.class);
        LifePart entity2Life = entity2.getPart(LifePart.class);
        
        this.entityCheck(entity1Damage, entity2Life);
        this.entityCheck(entity2Damage, entity1Life);
    }
    
    private void entityCheck(DamagePart damagePart, LifePart lifePart){
        if (damagePart != null){
            lifePart.setLife(lifePart.getLife() - damagePart.getDamage());
        }
    }
    
    
}