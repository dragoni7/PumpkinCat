package com.github.dragoni7.pumpkincat.world;


import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.init.RegisterEntities;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PumpkinCat.MODID)
public class PumpkinCatSpawn {

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if(event.getName() == null)
			return;
		
		MobSpawnInfoBuilder spawns = event.getSpawns();
		
		if(event.getName().equals(Biomes.DARK_FOREST.getRegistryName()))
		
			event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(RegisterEntities.PUMPKIN_CAT.get(), 20, 1, 4));
		
	}

}
