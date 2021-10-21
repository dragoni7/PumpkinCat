package com.github.dragoni7.pumpkincat.client.model;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeoModelPumpkinCat extends AnimatedGeoModel<PumpkinCatEntity>{

	@Override
	public ResourceLocation getModelLocation(PumpkinCatEntity object) {
		return new ResourceLocation(PumpkinCat.MODID, "geo/geomodelpumpkincat.geo.json");
	}
	
	@Override
	public ResourceLocation getTextureLocation(PumpkinCatEntity object) {
		return new ResourceLocation(PumpkinCat.MODID, "textures/entity/pumpkin_cat.png");
	}
	
	@Override
	public ResourceLocation getAnimationFileLocation(PumpkinCatEntity object) {
		return new ResourceLocation(PumpkinCat.MODID, "animations/geomodelpumpkincat.animation.json");
	}
}
