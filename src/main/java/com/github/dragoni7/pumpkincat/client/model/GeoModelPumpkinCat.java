package com.github.dragoni7.pumpkincat.client.model;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

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
	
	@SuppressWarnings({"rawtypes","unchecked"})
	@Override
	public void setLivingAnimations(PumpkinCatEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		head.setRotationX(extraData.headPitch * (float) Math.PI / 360F);
		head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 340F);
		
	}

}
