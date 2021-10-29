package com.github.dragoni7.pumpkincat.init;

import java.util.List;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import com.google.common.collect.Lists;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterEntities {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, PumpkinCat.MODID);

	public static final List<Item> SPAWN_EGGS = Lists.newArrayList();
	
	public static final RegistryObject<EntityType<PumpkinCatEntity>> PUMPKIN_CAT = createPumpkinCatEntity("pumpkin_cat", PumpkinCatEntity::new);
	  
	  public static <E extends PumpkinCatEntity> RegistryObject<EntityType<E>> createPumpkinCatEntity(String name, EntityType.EntityFactory<E> supplier) {
	 
		  EntityType.Builder<E> builder = EntityType.Builder.of(supplier, MobCategory.CREATURE).sized(0.7F,0.6F).setTrackingRange(32);
		  
		  RegistryObject<EntityType<E>> entity = ENTITY_TYPES.register(name, 
				  () ->  builder.build(PumpkinCat.MODID + ":" + name));
		  
		  Item spawnEgg = new ForgeSpawnEggItem(entity, 0x000000, 0xAA00AA, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));
		  spawnEgg.setRegistryName(new ResourceLocation(PumpkinCat.MODID, name + "_spawn_egg"));
		  SPAWN_EGGS.add(spawnEgg);
	  
		  		return entity; 
		  		
	  }
}

