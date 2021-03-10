package fr.modulproject.acaciaddon.init;

import fr.modulproject.acaciaddon.Main;
import fr.modulproject.acaciaddon.container.AcaciaEnricherContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Corentin on 08/03/2021 at 20:40
 */

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MODID);

    public static final RegistryObject<ContainerType<AcaciaEnricherContainer>> ACACIA_ENRICHER_CONTAINER = CONTAINERS.register("acacia_enricher_container", () -> new ContainerType<>(AcaciaEnricherContainer::new));

}
