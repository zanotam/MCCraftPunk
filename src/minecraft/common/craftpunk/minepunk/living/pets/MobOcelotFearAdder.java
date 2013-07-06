package common.craftpunk.minepunk.living.pets;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class MobOcelotFearAdder
{	
	 @ForgeSubscribe
	 public void onEntityJoinedWorld(EntityJoinWorldEvent event)
	 {
		 if (EntityMob.class.isInstance(event.entity))
		 {
			 EntityCreature entity = (EntityCreature) event.entity;
			 entity.tasks.addTask(1, new EntityAIAvoidEntity(entity, EntityOcelot.class, 16.0F, 0.25F, 0.3F));
		 }
	 }
}

/*
@ForgeSubscribe
public void livingSpawnEvent(LivingSpawnEvent event)
{
	 if (EntityMob.class.isInstance(event.entityLiving))
	 {
		 EntityCreature entity = (EntityCreature) event.entityLiving;
		 entity.tasks.addTask(1, new EntityAIAvoidEntity(entity, EntityOcelot.class, 16.0F, 0.25F, 0.3F));
	 }
}

@ForgeSubscribe
public void onLivingSpawnEvent(LivingSpawnEvent event)
{
	 if (EntityMob.class.isInstance(event.entityLiving))
	 {
		 EntityCreature entity = (EntityCreature) event.entityLiving;
		 entity.tasks.addTask(1, new EntityAIAvoidEntity(entity, EntityOcelot.class, 16.0F, 0.25F, 0.3F));
	 }
}
*/
