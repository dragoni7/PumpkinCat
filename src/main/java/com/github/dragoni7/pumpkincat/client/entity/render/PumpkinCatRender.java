package com.github.dragoni7.pumpkincat.client.entity.render;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.client.model.ModelPumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class PumpkinCatRender extends MobRenderer<PumpkinCatEntity, ModelPumpkinCat> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(PumpkinCat.MODID, "textures/entity/pumpkin_cat.png");

	public PumpkinCatRender(EntityRendererProvider.Context context) {
		super(context, new ModelPumpkinCat(context.bakeLayer(ModelPumpkinCat.LAYER_LOCATION)), 0.5f);
	}
	
	@Override
	public ResourceLocation getTextureLocation(PumpkinCatEntity entity) {
		return TEXTURE;
	}
	
}
