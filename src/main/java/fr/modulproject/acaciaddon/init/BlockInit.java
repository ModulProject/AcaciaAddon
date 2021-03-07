package fr.modulproject.acaciaddon.init;

import fr.modulproject.acaciaddon.Main;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Corentin on 06/03/2021 at 20:49
 */

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    public static RegistryObject<Block> register(String id, Block block, ItemGroup itemGroupIn){
        RegistryObject<Block> BLOCK = BLOCKS.register(id, ()-> block);
        ItemInit.ITEMS.register(id, () -> new BlockItem(block, (new Item.Properties()).group(itemGroupIn)));
        return BLOCK;
    }

    public static final RegistryObject<Block> COMPRESSED_ACACIA;

    static {
        Block block = null;

        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(9f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA = register("compressed_acacia_1", block, ItemGroup.DECORATIONS);
    }

}
