package com.github.dragoni7.pumpkincat.world;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.init.RegisterEntities;
import com.github.dragoni7.pumpkincat.util.PumpkinCatConfig;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = PumpkinCat.MODID)
public class PumpkinCatSpawn {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		
		if (event.getName() != null) {
			
			Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
			
			if (biome != null) {
				
				ResourceKey<Biome> biomeKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());
				
				if (PumpkinCatConfig.SPAWN.pumpkincat_biomes.get().contains(biomeKey.location().toString())) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(RegisterEntities.PUMPKIN_CAT.get(), PumpkinCatConfig.SPAWN.weight.get(), PumpkinCatConfig.SPAWN.min.get(), PumpkinCatConfig.SPAWN.max.get()));
				}
				
			} else {
				throw new IllegalArgumentException("Could not add spawns for pumpkincat");
			}
		}
		
	}

}
