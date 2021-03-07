package fr.modulproject.acaciaddon;

import fr.modulproject.acaciaddon.init.BlockInit;
import fr.modulproject.acaciaddon.init.ItemInit;
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

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

}
