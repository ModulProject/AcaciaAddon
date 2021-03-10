package fr.modulproject.acaciaddon.init;

import fr.modulproject.acaciaddon.Main;
import fr.modulproject.acaciaddon.tileentity.AcaciaEnricherTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Corentin on 09/03/2021 at 18:53
 */

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MODID);

    public static final RegistryObject<TileEntityType<AcaciaEnricherTileEntity>> ACACIA_ENRICHER = TILE_ENTITIES.register("acacia_enricher_tile", () -> TileEntityType.Builder.<AcaciaEnricherTileEntity>create(AcaciaEnricherTileEntity::new, ModBlocks.ACACIA_ENRICHER.get()).build(null));

}
