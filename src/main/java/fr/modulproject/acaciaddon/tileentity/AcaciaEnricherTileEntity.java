package fr.modulproject.acaciaddon.tileentity;

import fr.modulproject.acaciaddon.block.AcaciaEnricher;
import fr.modulproject.acaciaddon.container.AcaciaEnricherContainer;
import fr.modulproject.acaciaddon.init.ModItems;
import fr.modulproject.acaciaddon.init.ModTileEntities;
import fr.modulproject.acaciaddon.item.AcaciAxe;
import fr.modulproject.acaciaddon.utils.NBTTag;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Corentin on 09/03/2021 at 18:31
 */

public class AcaciaEnricherTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider, IInventory, ISidedInventory {

    public static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.acacia_enricher");
    public static final int ENRICH_TIME = 300;

    private NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private int enrichTime;
    private final IIntArray acaciaEnricherData = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return AcaciaEnricherTileEntity.this.enrichTime;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    AcaciaEnricherTileEntity.this.enrichTime = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    public AcaciaEnricherTileEntity() {
        super(ModTileEntities.ACACIA_ENRICHER.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, items);

        this.enrichTime = nbt.getInt("EnrichTime");

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        compound.putInt("EnrichTime", enrichTime);
        ItemStackHelper.saveAllItems(compound, items);

        return compound;
    }

    @Override
    public void tick() {
        if(!this.world.isRemote) {
            this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AcaciaEnricher.PROCESS, items.get(0).getItem().equals(ModItems.ACACIAXE.get())));

            if(items.get(0).getItem().equals(ModItems.ACACIAXE.get()) && AcaciaEnricherContainer.AUTHORIZED_BLOCKS.contains(items.get(1).getItem())) {
                if(enrichTime++ >= ENRICH_TIME) {
                    enrichTime = 0;
                    ItemStack stack = decrStackSize(1, 1);
                    int power = AcaciaEnricherContainer.AUTHORIZED_BLOCKS.indexOf(stack.getItem());
                    int xp = (int) Math.pow(9, power);
                    ((AcaciAxe) ModItems.ACACIAXE.get()).addXp(getStackInSlot(0), xp);
                }
            } else {
                enrichTime = 0;
            }
        }
    }

    public int getSizeInventory() {
        return items.size();
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return items.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.items.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return CONTAINER_NAME;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new AcaciaEnricherContainer(p_createMenu_1_, p_createMenu_2_, this, this.acaciaEnricherData);
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if(side == Direction.UP || side == Direction.NORTH || side == Direction.SOUTH || side == Direction.EAST || side == Direction.WEST)
            return new int[] {1};
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        if(Arrays.stream(getSlotsForFace(direction)).anyMatch(value -> value == index)) {
            return AcaciaEnricherContainer.AUTHORIZED_BLOCKS.contains(itemStackIn.getItem());
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return false;
    }
}
