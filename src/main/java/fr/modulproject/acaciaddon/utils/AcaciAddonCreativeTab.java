package fr.modulproject.acaciaddon.utils;

import fr.modulproject.acaciaddon.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Created by Corentin on 08/03/2021 at 21:45
 */

public class AcaciAddonCreativeTab extends ItemGroup {

    public static final AcaciAddonCreativeTab ACACIADDON_TAB = new AcaciAddonCreativeTab();

    public AcaciAddonCreativeTab() {
        super("acaciaddon");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.COMPRESSED_ACACIA_1.get());
    }
}
