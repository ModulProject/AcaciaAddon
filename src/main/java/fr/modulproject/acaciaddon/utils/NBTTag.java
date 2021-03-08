package fr.modulproject.acaciaddon.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

/**
 * Created by Corentin on 07/03/2021 at 15:21
 */

public class NBTTag<T extends INBT> {

    private final ItemStack stack;
    private final String key;

    public NBTTag(ItemStack stack, String key, T initValue){
        this.stack = stack;
        this.key = key;
        init(initValue);
    }

    private void init(T initValue) {
        if(!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }

        if(stack.getTag().get(key) == null)
            set(initValue);
    }

    public T get(){
        return (T) stack.getTag().get(key);
    }

    public void set(T value) {
        CompoundNBT tag = stack.getTag();
        tag.put(key, value);
        stack.setTag(tag);
    }

}
