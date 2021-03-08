package fr.modulproject.acaciaddon.init;

import fr.modulproject.acaciaddon.Main;
import fr.modulproject.acaciaddon.item.AcaciAxe;
import fr.modulproject.acaciaddon.utils.AcaciAddonCreativeTab;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Corentin on 06/03/2021 at 20:50
 */

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> ACACIAXE = ITEMS.register("acaciaxe", AcaciAxe::new);
    public static final RegistryObject<Item> ACACIA_STICK = ITEMS.register("acacia_stick", () -> new Item(new Item.Properties().group(AcaciAddonCreativeTab.ACACIADDON_TAB)));


}
