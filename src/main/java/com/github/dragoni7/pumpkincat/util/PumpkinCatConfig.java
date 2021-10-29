package com.github.dragoni7.pumpkincat.util;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;

public class PumpkinCatConfig {
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	public static final Spawn SPAWN = new Spawn(BUILDER);
	
	public static class Spawn {
		public final ForgeConfigSpec.IntValue min;
		public final ForgeConfigSpec.IntValue max;
		public final ForgeConfigSpec.IntValue weight;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> pumpkincat_biomes;
		
		Spawn(ForgeConfigSpec.Builder builder) {
			builder.push("Spawn Chances");
			builder.comment("Configure spawn min-max group size and spawn weight for pumpkincat entity");
			min = builder.defineInRange("min", 1, 0, 64);
			max = builder.defineInRange("max", 4, 0, 64);
			weight = builder.defineInRange("weight", 20, 0, 100);
			builder.pop();
			builder.push("Spawn Biomes");
			pumpkincat_biomes = builder.define("pumpkincat_biomes", Lists.newArrayList(Biomes.DARK_FOREST.location().toString()), o -> o instanceof String);
			builder.pop();
		}
		
	}
	
	public static final ForgeConfigSpec spec = BUILDER.build();

}
