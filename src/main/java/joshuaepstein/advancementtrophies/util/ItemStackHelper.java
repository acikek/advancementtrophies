package joshuaepstein.advancementtrophies.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackHelper
{
    //private static boolean initialized;
    //private static Field itemField;
    //private static Field itemDamageField;
    //private static Field stackTagCompoundField;
    //private static Field capabilitiesField;

    public static Item getTrueItem (@NotNull ItemStack stack) {
        //if (!initialized)
        return stack.getItem();

        /*try {
            return (Item)itemField.get(stack);
        } catch (IllegalAccessException e) {
            return stack.getItem();
        }*/
    }

    @NotNull
    public static ItemStack getItemPrototype (@NotNull ItemStack stack) {
        //if (!initialized)
        return stack.copy();

        /*try {
            CapabilityDispatcher capabilities = (CapabilityDispatcher) capabilitiesField.get(stack);
            Item item = (Item) itemField.get(stack);
            CompoundTag stackTagCompound = (CompoundTag) stackTagCompoundField.get(stack);
            ItemStack proto = new ItemStack(item, 1, capabilities != null ? capabilities.serializeNBT() : null);
            if (stackTagCompound != null)
                proto.setTag(stackTagCompound);
            return proto;
        } catch (IllegalAccessException e) {
            return stack.copy();
        }*/
    }

    /*static {
        try {
            itemField = ReflectionHelper.findField(ItemStack.class,  "item", "item");
            itemDamageField = ReflectionHelper.findField(ItemStack.class,"itemDamage", "field_77991_e");
            stackTagCompoundField = ReflectionHelper.findField(ItemStack.class,"stackTagCompound", "tag");
            capabilitiesField = ReflectionHelper.findField(ItemStack.class,"capabilities");
            itemField.setAccessible(true);
            itemDamageField.setAccessible(true);
            stackTagCompoundField.setAccessible(true);
            capabilitiesField.setAccessible(true);
            initialized = true;
        } catch (ReflectionHelper.UnableToFindFieldException e) {
            initialized = false;
        }
    }*/
}