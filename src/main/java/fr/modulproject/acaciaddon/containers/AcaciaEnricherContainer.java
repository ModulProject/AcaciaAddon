package fr.modulproject.acaciaddon.containers;

import fr.modulproject.acaciaddon.init.ModBlocks;
import fr.modulproject.acaciaddon.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;

/**
 * Created by Corentin on 08/03/2021 at 20:41
 */

public class AcaciaEnricherContainer extends Container {

    private final IInventory inputInventory = new Inventory(2);
    private final IWorldPosCallable worldPosCallable;

    public AcaciaEnricherContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.DUMMY);
    }

    public AcaciaEnricherContainer(int windowIdIn, PlayerInventory playerInventoryIn, final IWorldPosCallable worldPosCallableIn) {
        super(ModContainers.ACACIA_ENRICHER_CONTAINER.get(), windowIdIn);
        this.worldPosCallable = worldPosCallableIn;

        this.addSlot(new Slot(this.inputInventory, 0, 52, 31) {

            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });

        this.addSlot(new Slot(this.inputInventory, 1, 100, 31) {

            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(this.worldPosCallable, playerIn, ModBlocks.ACACIA_ENRICHER.get());
    }

}
