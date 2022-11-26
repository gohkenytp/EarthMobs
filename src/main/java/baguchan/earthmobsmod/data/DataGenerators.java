package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		/*event.getGenerator().addProvider(true, new BlockstateGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(true, new ItemModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		*/
		BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator(), event.getExistingFileHelper());
		event.getGenerator().addProvider(true, blocktags);
		event.getGenerator().addProvider(true, new ItemTagGenerator(event.getGenerator(), blocktags, event.getExistingFileHelper()));
		event.getGenerator().addProvider(true, new EntityTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
		//event.getGenerator().addProvider(true, new CraftingGenerator(event.getGenerator()));
	}
}