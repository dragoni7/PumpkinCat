package com.github.dragoni7.pumpkincat.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ModelUtils;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class ModelPumpkinCat extends EntityModel<PumpkinCatEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PumpkinCat.MODID, "pumpkin_cat"), "main");
	private final ModelPart body;

	public ModelPumpkinCat(ModelPart root) {
		this.body = root.getChild("body");
		
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.0F, -7.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-3.5F, -5.0F, -9.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(1.0F, -6.0F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -6.0F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 19.0F, 2.0F));

		PartDefinition wing_right_r1 = body.addOrReplaceChild("wing_right_r1", CubeListBuilder.create().texOffs(13, 0).addBox(4.0F, -5.0F, -2.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.0F, -2.0F, 0.8309F, -0.5148F, -0.4946F));

		PartDefinition wing_left_r1 = body.addOrReplaceChild("wing_left_r1", CubeListBuilder.create().texOffs(13, 5).addBox(-11.0F, -5.0F, -2.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.0F, -2.0F, 0.8309F, 0.5148F, 0.4946F));

		PartDefinition leg_front = body.addOrReplaceChild("leg_front", CubeListBuilder.create(), PartPose.offset(1.5F, 3.0F, -2.0F));

		PartDefinition left_r1 = leg_front.addOrReplaceChild("left_r1", CubeListBuilder.create().texOffs(17, 14).addBox(0.0F, -4.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition right_r1 = leg_front.addOrReplaceChild("right_r1", CubeListBuilder.create().texOffs(4, 24).addBox(-1.0F, -4.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition leg_back = body.addOrReplaceChild("leg_back", CubeListBuilder.create(), PartPose.offset(1.5F, 3.0F, 2.0F));

		PartDefinition left_r2 = leg_back.addOrReplaceChild("left_r2", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -4.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, -4.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition right_r2 = leg_back.addOrReplaceChild("right_r2", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -4.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, -4.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(-0.5F, 5.0F, -2.0F));

		PartDefinition tail_end_r1 = tail.addOrReplaceChild("tail_end_r1", CubeListBuilder.create().texOffs(22, 10).addBox(-1.0F, -1.0F, 11.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2382F, -1.0744F, 0.2182F, 0.0F, 0.0F));

		PartDefinition tail_base_r1 = tail.addOrReplaceChild("tail_base_r1", CubeListBuilder.create().texOffs(15, 17).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(PumpkinCatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
	}

	
}
