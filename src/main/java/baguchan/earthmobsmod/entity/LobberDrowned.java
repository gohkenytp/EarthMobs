package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.goal.GoToWaterGoal;
import baguchan.earthmobsmod.entity.projectile.ZombieFlesh;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.Random;

public class LobberDrowned extends Drowned implements RangedAttackMob {
	public LobberDrowned(EntityType<? extends LobberDrowned> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
	}

	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(1, new GoToWaterGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 6.5F));
		this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Axolotl.class, true, false));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	@Override
	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		ZombieFlesh zombieFlesh = new ZombieFlesh(this.level, this);
		double d0 = p_29912_.getEyeY() - this.getEyeY();
		double d1 = p_29912_.getX() - this.getX();
		double d3 = p_29912_.getZ() - this.getZ();
		double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double) 0.1F;
		zombieFlesh.shoot(d1, d0 + d4, d3, 0.9F, 0.4F);
		this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level.addFreshEntity(zombieFlesh);
		this.swing(InteractionHand.MAIN_HAND);
	}

	public static boolean checkLobberDrownedSpawnRules(EntityType<LobberDrowned> p_32350_, ServerLevelAccessor p_32351_, MobSpawnType p_32352_, BlockPos p_32353_, Random p_32354_) {
		if (!p_32351_.getFluidState(p_32353_.below()).is(FluidTags.WATER)) {
			return false;
		} else {
			Holder<Biome> holder = p_32351_.getBiome(p_32353_);
			boolean flag = p_32351_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_32351_, p_32353_, p_32354_) && (p_32352_ == MobSpawnType.SPAWNER || p_32351_.getFluidState(p_32353_).is(FluidTags.WATER));
			if (!holder.is(Biomes.RIVER) && !holder.is(Biomes.FROZEN_RIVER)) {
				return p_32354_.nextInt(40) == 0 && isDeepEnoughToSpawn(p_32351_, p_32353_) && flag;
			} else {
				return p_32354_.nextInt(15) == 0 && flag;
			}
		}
	}

	private static boolean isDeepEnoughToSpawn(LevelAccessor p_32367_, BlockPos p_32368_) {
		return p_32368_.getY() < p_32367_.getSeaLevel() - 5;
	}
}
