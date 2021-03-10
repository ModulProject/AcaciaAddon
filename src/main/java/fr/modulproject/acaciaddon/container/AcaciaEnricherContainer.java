package fr.modulproject.acaciaddon.container;

import fr.modulproject.acaciaddon.init.ModBlocks;
import fr.modulproject.acaciaddon.init.ModContainers;
import fr.modulproject.acaciaddon.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Corentin on 08/03/2021 at 20:41
 */

public class AcaciaEnricherContainer extends Container {

    public static final List<Item> AUTHORIZED_BLOCKS = Arrays.asList(ModBlocks.COMPRESSED_ACACIA_1.get().asItem(),
            ModBlocks.COMPRESSED_ACACIA_2.get().asItem(), ModBlocks.COMPRESSED_ACACIA_3.get().asItem(),
            ModBlocks.COMPRESSED_ACACIA_4.get().asItem(), ModBlocks.COMPRESSED_ACACIA_5.get().asItem(),
            ModBlocks.COMPRESSED_ACACIA_6.get().asItem(), ModBlocks.COMPRESSED_ACACIA_7.get().asItem(),
            ModBlocks.COMPRESSED_ACACIA_8.get().asItem());

    private final IInventory acaciaEnricherInventory;
    private final IIntArray data;

    public AcaciaEnricherContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, new Inventory(3), new IntArray(1));
    }

    public AcaciaEnricherContainer(int windowIdIn, PlayerInventory playerInventoryIn, IInventory acaciaEnricherIn, IIntArray data) {
        super(ModContainers.ACACIA_ENRICHER_CONTAINER.get(), windowIdIn);

        this.acaciaEnricherInventory = acaciaEnricherIn;
        this.data = data;

        this.addSlot(new Slot(acaciaEnricherInventory, 0, 52, 31) {

            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().equals(ModItems.ACACIAXE.get());
            }
        });

        this.addSlot(new Slot(acaciaEnricherInventory, 1, 100, 31) {

            @Override
            public boolean isItemValid(ItemStack stack) {
                return AUTHORIZED_BLOCKS.contains(stack.getItem());
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

        this.trackIntArray(data);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return acaciaEnricherInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            ItemStack slot0 = this.acaciaEnricherInventory.getStackInSlot(0);
            ItemStack slot1 = this.acaciaEnricherInventory.getStackInSlot(1);

            if(index != 0 && index != 1) {
                if(!slot0.isEmpty() && !slot1.isEmpty()) {
                    if(index >= 2 && index < 29) {
                        if(!this.mergeItemStack(stack1, 30, 39, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if(index >= 29 && index < 38 && !this.mergeItemStack(stack1, 3, 30, false)) {
                        return ItemStack.EMPTY;
                    }
                }else if(!this.mergeItemStack(stack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stack1, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if(stack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if(stack1.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack1);
        }

        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgress() {
        return data.get(0);
    }
}
