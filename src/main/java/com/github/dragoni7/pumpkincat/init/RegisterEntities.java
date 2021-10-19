package com.github.dragoni7.pumpkincat.init;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterEntities {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, PumpkinCat.MODID);
	
	
	  public static final RegistryObject<EntityType<PumpkinCatEntity>> PUMPKIN_CAT = createPumpkinCatEntity("pumpkin_cat", PumpkinCatEntity::new);
	  
	  public static <E extends PumpkinCatEntity> RegistryObject<EntityType<E>> createPumpkinCatEntity(String name, EntityType.EntityFactory<E> supplier) {
	 
		  EntityType.Builder<E> builder = EntityType.Builder.of(supplier, MobCategory.CREATURE).sized(0.7F,0.6F).setTrackingRange(8);
	  
		  RegistryObject<EntityType<E>> entity = ENTITY_TYPES.register(name, 
				  () ->  builder.build(PumpkinCat.MODID + ":" + name));
	  
		  		return entity; 
		  		
	  }
	 
	 
		
}

