package com.github.dragoni7.pumpkincat.common.entities;

import java.util.EnumSet;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class PumpkinCatEntity extends Animal implements IAnimatable, FlyingAnimal{
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.flap", true));
		return PlayState.CONTINUE;
	}

	public PumpkinCatEntity(EntityType<? extends PumpkinCatEntity> type, Level worldIn) {
		super(type, worldIn);
		this.moveControl = new FlyingMoveControl(this,20,true);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
	    this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
	    this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
	    this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
	    this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
	}
	
	protected SoundEvent getAmbientSound() {
		return SoundEvents.CAT_AMBIENT;
	}
	
	public int getAmbientSoundInterval() {
		return 60;
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.CAT_HURT;
	}
	
	protected SoundEvent getDeathSound() {
		return SoundEvents.CAT_DEATH;
	}
	
	public static AttributeSupplier.Builder customAttributes() {
	      return Mob.createMobAttributes()
	    		  .add(Attributes.MAX_HEALTH, 10.0D)
	    		  .add(Attributes.FLYING_SPEED, (double)0.4F)
	    		  .add(Attributes.MOVEMENT_SPEED, (double)0.2F)
	    		  .add(Attributes.ATTACK_DAMAGE, 2.0D)
	    		  .add(Attributes.FOLLOW_RANGE, 48.0D);
	   }
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new WanderGoal());
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D, 1.0000001E-5F));
        this.goalSelector.addGoal(6, new PanicGoal(this, 2.0D));
        super.registerGoals();
	}
	
	protected PathNavigation createNavigation(Level worldIn) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation(this, worldIn) {
            public boolean isStableDestination(BlockPos pos) {
                return !this.level.getBlockState(pos.below()).isAir();
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(false);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }
	
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return this.isBaby() ? sizeIn.height * 0.5F : sizeIn.height * 0.5F;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0;
    }
    
    protected boolean makeFlySound() {
        return true;
    }

	public boolean isFlying() {
		return true;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return PumpkinCatEntity.this;
	}
	
	class WanderGoal extends Goal {
		WanderGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		
		public boolean canUse() {
			
			return PumpkinCatEntity.this.navigation.isDone() &&  PumpkinCatEntity.this.random.nextInt(3) == 0;
		}
		
		public boolean canContinuToUse() {
			return PumpkinCatEntity.this.navigation.isInProgress();
		}
		
		public void start() {
			Vec3 vector3d = this.getRandomLocation();
			if(vector3d != null) {
				PumpkinCatEntity.this.navigation.moveTo(PumpkinCatEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
				
			}
		}
		
		@Nullable
		private Vec3 getRandomLocation() {
			Vec3 vec3 = PumpkinCatEntity.this.getViewVector(0.0F);
			int i = 8;
			Vec3 vec32 = HoverRandomPos.getPos(PumpkinCatEntity.this, 6, 5, vec3.x, vec3.z, ((float)Math.PI / 2F), 3, 1);
			
			return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(PumpkinCatEntity.this, 8, 4, -2, vec3.x, vec3.z, (double)((float)Math.PI / 2F));
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		
		return this.factory;
	}

	
	 

}

