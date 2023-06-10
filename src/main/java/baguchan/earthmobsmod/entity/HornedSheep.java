package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HornedSheep extends Sheep {
	private static final EntityDataAccessor<Boolean> DATA_HAS_HORN = SynchedEntityData.defineId(HornedSheep.class, EntityDataSerializers.BOOLEAN);


	public HornedSheep(EntityType<? extends Sheep> p_29806_, Level p_29807_) {
		super(p_29806_, p_29807_);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_HAS_HORN, true);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Goat.class, 12.0F, 1.3D, 1.35D) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasHorn();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasHorn();
			}
		});
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.3D, true));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && hasHorn();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && hasHorn();
			}
		}).setAlertOthers());
	}

	public boolean hasHorn() {
		return this.entityData.get(DATA_HAS_HORN);
	}

	public boolean dropHorn() {
		boolean flag = this.hasHorn();
		if (!flag) {
			return false;
		} else {
			this.entityData.set(DATA_HAS_HORN, false);
			Vec3 vec3 = this.position();
			ItemStack itemstack = this.createHorn();
			double d0 = (double) Mth.randomBetween(this.random, -0.2F, 0.2F);
			double d1 = (double) Mth.randomBetween(this.random, 0.3F, 0.7F);
			double d2 = (double) Mth.randomBetween(this.random, -0.2F, 0.2F);
			ItemEntity itementity = new ItemEntity(this.level(), vec3.x(), vec3.y(), vec3.z(), itemstack, d0, d1, d2);
			this.level().addFreshEntity(itementity);
			ItemEntity itementity2 = new ItemEntity(this.level(), vec3.x(), vec3.y(), vec3.z(), itemstack, d0, d1, d2);
			this.level().addFreshEntity(itementity2);
			return true;
		}
	}

	public ItemStack createHorn() {
		RandomSource randomsource = RandomSource.create((long) this.getUUID().hashCode());
		TagKey<Instrument> tagkey = InstrumentTags.REGULAR_GOAT_HORNS;
		HolderSet<Instrument> holderset = BuiltInRegistries.INSTRUMENT.getOrCreateTag(tagkey);
		return InstrumentItem.create(ModItems.HORN_FLUTE.get(), holderset.getRandomElement(randomsource).get());
	}

	public void addHorns() {
		this.entityData.set(DATA_HAS_HORN, true);
	}

	public void addAdditionalSaveData(CompoundTag p_149385_) {
		super.addAdditionalSaveData(p_149385_);
		p_149385_.putBoolean("HasHorn", this.hasHorn());
	}

	public void readAdditionalSaveData(CompoundTag p_149373_) {
		super.readAdditionalSaveData(p_149373_);
		this.entityData.set(DATA_HAS_HORN, p_149373_.getBoolean("HasHorn"));
	}

	public void aiStep() {
		this.updateSwingTime();
		super.aiStep();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Sheep.createAttributes().add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ATTACK_KNOCKBACK, (double) 0.75F);
	}

	@Override
	public HornedSheep getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
		return ModEntities.HORNED_SHEEP.get().create(p_149035_);
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean flag = super.doHurtTarget(entity);

		if (!flag && entity instanceof LivingEntity) {
			if (((LivingEntity) entity).isBlocking()) {
				this.dropHorn();
				this.playSound(SoundEvents.SHEEP_HURT);
			}
		}

		return flag;
	}

	@Override
	public boolean hurt(DamageSource damagesource, float p_27568_) {
		Entity entity1 = damagesource.getEntity();
		if (entity1 != null) {
			if (entity1 instanceof LivingEntity) {
				this.setLastHurtByMob((LivingEntity) entity1);
			}
		}
		return super.hurt(damagesource, p_27568_);
	}
}
