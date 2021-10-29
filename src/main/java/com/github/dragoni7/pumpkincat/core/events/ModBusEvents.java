package com.github.dragoni7.pumpkincat.core.events;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import com.github.dragoni7.pumpkincat.init.RegisterEntities;
import com.google.common.base.Preconditions;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PumpkinCat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
	
	@SubscribeEvent
	public static void Attributes(final EntityAttributeCreationEvent event) {
		event.put(RegisterEntities.PUMPKIN_CAT.get(), PumpkinCatEntity.customAttributes().build());
	}
	
	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
		SpawnPlacements.register(RegisterEntities.PUMPKIN_CAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PumpkinCatEntity::checkPumpkinCatSpawnRules);
	}
	
	@SubscribeEvent
	public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
		for (Item spawnEgg : RegisterEntities.SPAWN_EGGS) {
			Preconditions.checkNotNull(spawnEgg.getRegistryName(), "registryName");
			event.getRegistry().register(spawnEgg);
		}
	}

}
