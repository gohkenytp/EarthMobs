package baguchan.earthmobsmod;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class EarthMobsConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static class Common {
		public final ForgeConfigSpec.IntValue woolyCowSpawnRate;
		public final ForgeConfigSpec.IntValue umbraCowSpawnRate;
		public final ForgeConfigSpec.IntValue cluckshroomSpawnRate;
		public final ForgeConfigSpec.IntValue fancyChickenSpawnRate;
		public final ForgeConfigSpec.IntValue duckSpawnRate;
		public final ForgeConfigSpec.IntValue turkeySpawnRate;
		public final ForgeConfigSpec.IntValue hornedSheepSpawnRate;
		public final ForgeConfigSpec.IntValue boneSpiderSpawnRate;
		public final ForgeConfigSpec.IntValue hyperRabbitSpawnRate;
		public final ForgeConfigSpec.IntValue moobloomSpawnRate;
		public final ForgeConfigSpec.IntValue moolipSpawnRate;
		public final ForgeConfigSpec.IntValue jumboRabbitSpawnRate;
		
		public final ForgeConfigSpec.IntValue boulderingZombieSpawnRate;
		public final ForgeConfigSpec.IntValue lobberZombieSpawnRate;

		public final ForgeConfigSpec.IntValue tropicalSlimeSpawnRate;

		public final ForgeConfigSpec.IntValue skeletonWolfOverWorldSpawnRate;
		public final ForgeConfigSpec.IntValue skeletonWolfNetherSpawnRate;
		public final ForgeConfigSpec.IntValue witherSkeletonWolfNetherSpawnRate;

		public final ForgeConfigSpec.BooleanValue mudSpawnInOverworld;

		public Common(ForgeConfigSpec.Builder builder) {
			woolyCowSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.woolyCowSpawnRate")
					.comment("Changed WoolyCow SpawnRate. [0 ~ 100]")
					.defineInRange("Wooly cow SpawnRate", 10, 0, 100);
			umbraCowSpawnRate = builder
					.comment("Changed Umbra Cow SpawnRate. [0 ~ 100]")
					.defineInRange("Umbra Cow SpawnRate", 10, 0, 100);
			cluckshroomSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.cluckshroomSpawnRate")
					.comment("Changed Cluckshroom SpawnRate. [0 ~ 100]")
					.defineInRange("Cluckshroom SpawnRate", 5, 0, 100);
			fancyChickenSpawnRate = builder
					.comment("Changed Fancy Chicken SpawnRate. [0 ~ 100]")
					.defineInRange("Fancy Chicken SpawnRate", 6, 0, 100);
			duckSpawnRate = builder
					.comment("Changed Duck SpawnRate. [0 ~ 100]")
					.defineInRange("Duck SpawnRate", 8, 0, 100);
			turkeySpawnRate = builder
					.comment("Changed Turkey SpawnRate. [0 ~ 100]")
					.defineInRange("Turkey SpawnRate", 10, 0, 100);
			hornedSheepSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.hornedSheepSpawnRate")
					.comment("Changed Horned Sheep SpawnRate. [0 ~ 100]")
					.defineInRange("Horned Sheep SpawnRate", 10, 0, 100);
			hyperRabbitSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.hyperRabbitSpawnRate")
					.comment("Changed Hyper Rabbit SpawnRate. [0 ~ 100]")
					.defineInRange("Hyper Rabbit SpawnRate", 5, 0, 100);
			moobloomSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.moobloomSpawnRate")
					.comment("Changed Moobloom SpawnRate. [0 ~ 100]")
					.defineInRange("Moobloom SpawnRate", 10, 0, 100);
			moolipSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.moolipSpawnRate")
					.comment("Changed Moolip SpawnRate. [0 ~ 100]")
					.defineInRange("Moolip SpawnRate", 10, 0, 100);
			jumboRabbitSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.jumboRabbitSpawnRate")
					.comment("Changed Jumbo Rabbit SpawnRate. [0 ~ 100]")
					.defineInRange("Jumbo Rabbit SpawnRate", 5, 0, 100);
			boneSpiderSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.boneSpiderSpawnRate")
					.comment("Changed Bone Spider SpawnRate. [0 ~ 1000]")
					.defineInRange("Bone Spider SpawnRate", 10, 0, 1000);
			boulderingZombieSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.boulderingZombieSpawnRate")
					.comment("Changed BoulderingZombie SpawnRate. [0 ~ 1000]")
					.defineInRange("BoulderingZombie SpawnRate", 20, 0, 1000);
			lobberZombieSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.lobberZombieSpawnRate")
					.comment("Changed LobberZombie SpawnRate. [0 ~ 1000]")
					.defineInRange("LobberZombie SpawnRate", 20, 0, 1000);
			tropicalSlimeSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.tropicalSlimeSpawnRate")
					.comment("Changed TropicalSlime SpawnRate. [0 ~ 1000]")
					.defineInRange("TropicalSlime SpawnRate", 3, 0, 1000);

			skeletonWolfOverWorldSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.skeletonWolfOverworldSpawnRate")
					.comment("Changed SkeletonWolf SpawnRate In Overworld. [0 ~ 1000]")
					.defineInRange("SkeletonWolf SpawnRate In Overworld", 10, 0, 1000);

			skeletonWolfNetherSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.skeletonWolfNetherSpawnRate")
					.comment("Changed SkeletonWolf SpawnRate In Nether. [0 ~ 1000]")
					.defineInRange("SkeletonWolf SpawnRate In Nether", 5, 0, 1000);
			witherSkeletonWolfNetherSpawnRate = builder
					.comment("Changed WitherSkeletonWolf SpawnRate In Nether. [0 ~ 1000]")
					.defineInRange("WitherSkeletonWolf SpawnRate In Nether", 4, 0, 1000);

			mudSpawnInOverworld = builder
					.translation(EarthMobsMod.MODID + ".config.mudSpawnInOverworld")
					.comment("Changed Mud Spawn in Overworld.")
					.define("Mud Spawn in Overworld", true);
		}
	}

}
