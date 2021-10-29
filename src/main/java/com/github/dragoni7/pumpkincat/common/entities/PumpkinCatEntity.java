package com.github.dragoni7.pumpkincat.common.entities;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class PumpkinCatEntity extends Animal implements IAnimatable, FlyingAnimal{
	
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.PUMPKIN_PIE);
	
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
		return 600;
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.CAT_HURT;
	}
	
	protected SoundEvent getDeathSound() {
		return SoundEvents.CAT_DEATH;
	}
	
	public static boolean checkPumpkinCatSpawnRules(EntityType<PumpkinCatEntity> entity, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn) {
		return worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK);
	      
	   }
	
	public static AttributeSupplier.Builder customAttributes() {
	      return Mob.createMobAttributes()
	    		  .add(Attributes.MAX_HEALTH, 8.0D)
	    		  .add(Attributes.FLYING_SPEED, (double)1.0F)
	    		  .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
	    		  .add(Attributes.ATTACK_DAMAGE, 3.0D)
	    		  .add(Attributes.FOLLOW_RANGE, 48.0D);
	   }
	
	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(2, new WanderGoal());
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        
        
	}
	
	protected PathNavigation createNavigation(Level worldIn) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation(this, worldIn) {
            public boolean isStableDestination(BlockPos pos) {
                return !this.level.getBlockState(pos.below()).isAir();
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(true);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }
	
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return this.isBaby() ? sizeIn.height * 0.6F : sizeIn.height * 0.6F;
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
	
	public float getBrightness() {
		return 1.0F;
	}
	
	public boolean isNoGravity() {
		return true;
	}
	
	private AABB catArea() {
		return this.getBoundingBox().inflate(15,32,15);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return PumpkinCatEntity.this;
	}
	
	public boolean isFood(ItemStack itemstack) {
	      return FOOD_ITEMS.test(itemstack);
	   }
	
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
	
		if(itemstack.is(Items.PUMPKIN_PIE)) {
			
			List<Player> playerList = this.level.getEntitiesOfClass(Player.class, this.catArea(), Predicates.alwaysTrue());
			for (Player e : playerList) {
				if (!e.hasEffect(MobEffects.NIGHT_VISION) && !e.hasEffect(MobEffects.NIGHT_VISION)) {
					e.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 4800, 0));
					
					if(!player.isCreative()) {
		                itemstack.shrink(1);
		            }
					
					if(this.level.isClientSide) {
						for(int i = 0; i < 7; ++i) {
					         double d0 = this.random.nextGaussian() * 0.02D;
					         double d1 = this.random.nextGaussian() * 0.02D;
					         double d2 = this.random.nextGaussian() * 0.02D;
					         this.level.addParticle(ParticleTypes.GLOW, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
					      }
						
					}
				}
			}
			
			
			
			return InteractionResult.SUCCESS;
		}
		
		else return InteractionResult.FAIL;
	}
	
	class WanderGoal extends Goal {
		WanderGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		
		public boolean canUse() {
			
			return PumpkinCatEntity.this.navigation.isDone() &&  PumpkinCatEntity.this.random.nextInt(50) == 0;
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
		data.addAnimationController(new AnimationController<PumpkinCatEntity>(this, "controller", 0, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		
		return this.factory;
	}


	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

