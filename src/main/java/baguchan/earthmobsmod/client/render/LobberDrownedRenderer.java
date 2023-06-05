package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoulderingZombieModel;
import baguchan.earthmobsmod.client.model.LobberZombieModel;
import baguchan.earthmobsmod.client.render.layer.ZombieOuterLayer;
import baguchan.earthmobsmod.entity.LobberDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LobberDrownedRenderer extends AbstractZombieRenderer<LobberDrowned, ZombieModel<LobberDrowned>> {
    private static final ResourceLocation LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/lobber_zombie/lobber_drowned.png");
    private static final ResourceLocation OUTER_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/lobber_zombie/lobber_drowned_outer_layer.png");


    public LobberDrownedRenderer(EntityRendererProvider.Context p_173964_) {
        super(p_173964_, new LobberZombieModel<>(p_173964_.bakeLayer(ModModelLayers.LOBBER_DROWNED)), new ZombieModel(p_173964_.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new ZombieModel(p_173964_.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
        this.addLayer(new ZombieOuterLayer<>(this, new BoulderingZombieModel<>(p_173964_.bakeLayer(ModModelLayers.LOBBER_DROWNED_OUTER)), OUTER_LOCATION));
    }

    protected void setupRotations(LobberDrowned p_114109_, PoseStack p_114110_, float p_114111_, float p_114112_, float p_114113_) {
        super.setupRotations(p_114109_, p_114110_, p_114111_, p_114112_, p_114113_);
        float f = p_114109_.getSwimAmount(p_114113_);
        if (f > 0.0F) {
            float f1 = -10.0F - p_114109_.getXRot();
            float f2 = Mth.lerp(f, 0.0F, f1);
            p_114110_.rotateAround(Axis.XP.rotationDegrees(f2), 0.0F, p_114109_.getBbHeight() / 2.0F, 0.0F);
        }

    }

    public ResourceLocation getTextureLocation(Zombie p_114115_) {
        return LOCATION;
    }
}
