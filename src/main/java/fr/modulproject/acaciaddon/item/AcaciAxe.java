package fr.modulproject.acaciaddon.item;

import fr.modulproject.acaciaddon.utils.AAItemTier;
import fr.modulproject.acaciaddon.utils.AcaciAddonCreativeTab;
import fr.modulproject.acaciaddon.utils.NBTTag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * Created by Corentin on 07/03/2021 at 13:19
 */

public class AcaciAxe extends AxeItem {

    private static Set<Material> EFFECTIVE_ON_MATERIALS;
    private static Set<Block> EFFECTIVE_ON_BLOCK;

    public static final String XP_KEY = "XP";
    private static final int[] LEVEL_XP = new int[] {80, 270, 650, 1800, 4250, 9900, 21000, 66000, 130000, 250000};

    public AcaciAxe() {
        super(AAItemTier.ACACIA, 8, -2.8f, (new Item.Properties()).group(AcaciAddonCreativeTab.ACACIADDON_TAB));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if(state.getBlock().equals(Blocks.ACACIA_LOG))
            addXp(stack, 1);
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        NBTTag<IntNBT> xpTag = new NBTTag<>(stack, XP_KEY, IntNBT.valueOf(0));
        int xp = xpTag.get().getInt();
        if(getLevel(xp) >= getMaxLevel()) {
            stack.setDamage(0);
            return false;
        }
        return super.showDurabilityBar(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        NBTTag<IntNBT> xpTag = new NBTTag<>(stack, XP_KEY, IntNBT.valueOf(0));
        int xp = xpTag.get().getInt();

        tooltip.add(new StringTextComponent("Level " + getLevel(xp) + "/" + getMaxLevel()));
        StringBuilder xpLore = new StringBuilder("XP : ").append(xp);
        if(getLevel(xp) < getMaxLevel())
            xpLore.append("/").append(getNextXpTier(xp));
        tooltip.add(new StringTextComponent(xpLore.toString()));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if(EFFECTIVE_ON_BLOCK == null || EFFECTIVE_ON_MATERIALS == null)
            loadEfficiencies();
        if(EFFECTIVE_ON_MATERIALS.contains(state.getMaterial()) || EFFECTIVE_ON_BLOCK.contains(state.getBlock())) {
            NBTTag<IntNBT> xpTag = new NBTTag<>(stack, XP_KEY, IntNBT.valueOf(0));
            return this.efficiency + getLevel(xpTag.get().getInt());
        } else {
            return super.getDestroySpeed(stack, state);
        }
    }

    private int getLevel(int xp) {
        for(int i = LEVEL_XP.length-1; i >= 0; i--) {
            if(xp >= LEVEL_XP[i])
                return i+1;
        }
        return 0;
    }

    private int getMaxLevel() {
        return LEVEL_XP.length;
    }

    private int getNextXpTier(int xp) {
        int level = getLevel(xp);
        if(level == getMaxLevel())
            return 0;
        return LEVEL_XP[level];
    }

    public void addXp(ItemStack stack, int amount){
        NBTTag<IntNBT> xp = new NBTTag<>(stack, XP_KEY, IntNBT.valueOf(0));
        if(xp.get().getInt()>=0 && xp.get().getInt()+amount>0)
            xp.set(IntNBT.valueOf(xp.get().getInt()+amount));
    }


    private void loadEfficiencies() {
        try {
            Field mField = AxeItem.class.getDeclaredField("EFFECTIVE_ON_MATERIALS");
            mField.setAccessible(true);
            Field bField = AxeItem.class.getDeclaredField("EFFECTIVE_ON_BLOCKS");
            bField.setAccessible(true);

            EFFECTIVE_ON_MATERIALS = (Set<Material>) mField.get(null);
            EFFECTIVE_ON_BLOCK = (Set<Block>) bField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
