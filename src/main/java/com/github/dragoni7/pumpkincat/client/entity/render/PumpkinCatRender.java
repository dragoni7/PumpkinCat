package com.github.dragoni7.pumpkincat.client.entity.render;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.client.model.GeoModelPumpkinCat;
import com.github.dragoni7.pumpkincat.client.model.ModelPumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


/*
 * public class PumpkinCatRender extends MobRenderer<PumpkinCatEntity,
 * ModelPumpkinCat> {
 * 
 * public static final ResourceLocation TEXTURE = new
 * ResourceLocation(PumpkinCat.MODID, "textures/entity/pumpkin_cat.png");
 * 
 * public PumpkinCatRender(EntityRendererProvider.Context context) {
 * super(context, new
 * ModelPumpkinCat(context.bakeLayer(ModelPumpkinCat.LAYER_LOCATION)), 0.5f); }
 * 
 * @Override public ResourceLocation getTextureLocation(PumpkinCatEntity entity)
 * { return TEXTURE; }
 * 
 * }
 */

public class PumpkinCatRender extends GeoEntityRenderer<PumpkinCatEntity> {
	
	public PumpkinCatRender(EntityRendererProvider.Context renderManager) {
		super(renderManager, new GeoModelPumpkinCat());
	}
	
	@Override 
	public RenderType getRenderType(PumpkinCatEntity animatable, float partialTicks, PoseStack stack, 
			MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
