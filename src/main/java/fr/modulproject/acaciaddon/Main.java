package fr.modulproject.acaciaddon;

import fr.modulproject.acaciaddon.containers.AcaciaEnricherContainer;
import fr.modulproject.acaciaddon.init.ModBlocks;
import fr.modulproject.acaciaddon.init.ModItems;
import fr.modulproject.acaciaddon.init.ModContainers;
import fr.modulproject.acaciaddon.screen.AcaciaEnricherScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Created by Corentin on 06/03/2021 at 20:46
 */

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "acaciaddon";

    public Main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModContainers.CONTAINERS.register(bus);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainers.ACACIA_ENRICHER_CONTAINER.get(), AcaciaEnricherScreen::new);
    }

}
