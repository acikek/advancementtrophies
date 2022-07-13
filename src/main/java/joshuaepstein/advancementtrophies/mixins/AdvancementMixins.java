package joshuaepstein.advancementtrophies.mixins;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.block.entity.TrophyBlockEntity;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(value = AdvancementToast.class, priority = 1001)
public class AdvancementMixins {

    @Shadow @Final private Advancement advancement;

    @Inject(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementDisplay;getFrame()Lnet/minecraft/advancement/AdvancementFrame;", ordinal = 3))
    private void render(MatrixStack matrices, ToastManager manager, long startTime, CallbackInfoReturnable<Toast.Visibility> cir){
        if(!this.advancement.getDisplay().getFrame().equals(AdvancementFrame.TASK) && !this.advancement.getId().toString().contains("recipe")){
            ItemStack stack = new ItemStack(Main.TROPHY);
            NbtCompound tag = stack.getOrCreateNbt();
            NbtCompound blockTag = new NbtCompound();
            blockTag.putString("advancementName", this.advancement.getDisplay().getTitle().getString());
            blockTag.putString("advancementDisplayItem", Registry.ITEM.getKey(this.advancement.getDisplay().getIcon().getItem()).get().getValue().toString());
            tag.put("BlockEntityTag", blockTag);
            stack.setNbt(tag);
            stack.setCustomName(Text.literal(this.advancement.getDisplay().getTitle().getString() +  " Trophy").setStyle(Style.EMPTY.withItalic(false).withColor(Formatting.GOLD)));

            MinecraftClient.getInstance().player.giveItemStack(stack);
        }
    }
}
