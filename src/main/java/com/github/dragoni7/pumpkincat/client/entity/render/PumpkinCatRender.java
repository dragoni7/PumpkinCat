package com.github.dragoni7.pumpkincat.client.entity.render;

import com.github.dragoni7.pumpkincat.client.model.GeoModelPumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


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
	
	protected int getBlockLightLevel(PumpkinCatEntity entity, BlockPos partialTicks) {
		return 15;
	}

}
