package fr.modulproject.acaciaddon.init;

import fr.modulproject.acaciaddon.Main;
import fr.modulproject.acaciaddon.item.AcaciAxe;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Corentin on 06/03/2021 at 20:50
 */

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> ACACIAXE = ITEMS.register("acaciaxe", AcaciAxe::new);

}
