package fr.modulproject.acaciaddon.init;

import fr.modulproject.acaciaddon.Main;
import fr.modulproject.acaciaddon.block.AcaciaEnricher;
import fr.modulproject.acaciaddon.utils.AcaciAddonCreativeTab;
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

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    public static RegistryObject<Block> register(String id, Block block, ItemGroup itemGroupIn){
        RegistryObject<Block> BLOCK = BLOCKS.register(id, ()-> block);
        ModItems.ITEMS.register(id, () -> new BlockItem(block, (new Item.Properties()).group(itemGroupIn)));
        return BLOCK;
    }

    public static final RegistryObject<Block> COMPRESSED_ACACIA_1;
    public static final RegistryObject<Block> COMPRESSED_ACACIA_2;
    public static final RegistryObject<Block> COMPRESSED_ACACIA_3;
    public static final RegistryObject<Block> COMPRESSED_ACACIA_4;
    public static final RegistryObject<Block> COMPRESSED_ACACIA_5;
    public static final RegistryObject<Block> COMPRESSED_ACACIA_6;
    public static final RegistryObject<Block> COMPRESSED_ACACIA_7;
    public static final RegistryObject<Block> COMPRESSED_ACACIA_8;
    public static final RegistryObject<Block> ACACIA_ENRICHER;

    static {
        Block block;

        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(8f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_1 = register("compressed_acacia_1", block, AcaciAddonCreativeTab.ACACIADDON_TAB);
        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(10f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_2 = register("compressed_acacia_2", block, AcaciAddonCreativeTab.ACACIADDON_TAB);
        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(12f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_3 = register("compressed_acacia_3", block, AcaciAddonCreativeTab.ACACIADDON_TAB);
        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(14f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_4 = register("compressed_acacia_4", block, AcaciAddonCreativeTab.ACACIADDON_TAB);
        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(16f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_5 = register("compressed_acacia_5", block, AcaciAddonCreativeTab.ACACIADDON_TAB);
        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(18f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_6 = register("compressed_acacia_6", block, AcaciAddonCreativeTab.ACACIADDON_TAB);
        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(20f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_7 = register("compressed_acacia_7", block, AcaciAddonCreativeTab.ACACIADDON_TAB);
        block = new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(22f).harvestTool(ToolType.AXE));
        COMPRESSED_ACACIA_8 = register("compressed_acacia_8", block, AcaciAddonCreativeTab.ACACIADDON_TAB);

        ACACIA_ENRICHER = register("acacia_enricher", new AcaciaEnricher(), AcaciAddonCreativeTab.ACACIADDON_TAB);
    }

}
