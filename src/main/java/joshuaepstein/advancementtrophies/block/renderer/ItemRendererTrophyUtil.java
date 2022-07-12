package joshuaepstein.advancementtrophies.block.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemRendererTrophyUtil extends ItemRenderer {
    private final ItemRenderer parent;

    @NotNull
    public ItemStack overrideStack;

    public ItemRendererTrophyUtil(TextureManager texManager, BakedModelManager modelManager, ItemColors colors) {
        super(texManager, modelManager, colors, null);
        parent = MinecraftClient.getInstance().getItemRenderer();
        overrideStack = ItemStack.EMPTY;
    }

    @Override
    public ItemModels getModels() {
        return parent.getModels();
    }

    @Override
    public void renderItem(ItemStack stack, ModelTransformation.Mode transformationType, int light, int overlay, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int seed) {
        parent.renderItem(stack, transformationType, light, overlay, matrices, vertexConsumers, seed);
    }

    @Override
    public void renderItem(@Nullable LivingEntity entity, ItemStack item, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, @Nullable World world, int light, int overlay, int seed) {
        parent.renderItem(entity, item, renderMode, leftHanded, matrices, vertexConsumers, world, light, overlay, seed);
    }

    @Override
    public void renderItem(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
        parent.renderItem(stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, model);
    }

    @Override
    public BakedModel getModel(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed) {
        return parent.getModel(stack, world, entity, seed);
    }
}