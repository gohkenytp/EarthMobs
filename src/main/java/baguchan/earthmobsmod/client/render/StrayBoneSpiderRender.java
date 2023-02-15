package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoneSpiderModel;
import baguchan.earthmobsmod.client.render.layer.StrayBoneSpiderEyesLayer;
import baguchan.earthmobsmod.entity.StrayBoneSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrayBoneSpiderRender<T extends StrayBoneSpider> extends MobRenderer<T, BoneSpiderModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bone_spider/stray_bone_spider.png");

	public StrayBoneSpiderRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new BoneSpiderModel<>(p_173952_.bakeLayer(ModModelLayers.STRAY_BONE_SPIDER)), 0.65F);
		this.addLayer(new StrayBoneSpiderEyesLayer<>(this));
	}


	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return TEXTURE;
	}

	@Override
	protected void scale(T p_115314_, PoseStack p_115315_, float p_115316_) {
		super.scale(p_115314_, p_115315_, p_115316_);
		p_115315_.scale(p_115314_.getScale(), p_115314_.getScale(), p_115314_.getScale());
	}

	@Override
	protected boolean isShaking(T p_115304_) {
		return false;
	}
}