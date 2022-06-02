package joshuaepstein.advancementtrophies.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasicItem extends Item  {

    List<Component> tooltipsAdd = new ArrayList<>();
    public BasicItem(ResourceLocation id, Properties properties, Component... tooltips) {
        super(properties);
        this.setRegistryName(id);
        tooltipsAdd.addAll(List.of(tooltips));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, world, tooltips, flagIn);
        tooltips.addAll(tooltipsAdd);
    }
}
