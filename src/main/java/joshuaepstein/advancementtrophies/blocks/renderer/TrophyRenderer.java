package joshuaepstein.advancementtrophies.blocks.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import joshuaepstein.advancementtrophies.blocks.TrophyBlock;
import joshuaepstein.advancementtrophies.blocks.entity.TrophyBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrophyRenderer implements BlockEntityRenderer<TrophyBlockEntity> {

    private static ItemRenderer itemRenderer;
    public static List<BlockPos> positionsWithoutName = new ArrayList<>();

    public TrophyRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(TrophyBlockEntity trophy, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        itemRenderer = Minecraft.getInstance().getItemRenderer();
        String advancementName = trophy.getAdvancementName();
        if(player != null && advancementName != null && shouldRenderNameplate(trophy, player)){
            renderName(mc, trophy, advancementName, poseStack, buffer, combinedLight);
        }
        renderItem(mc, trophy, trophy.getDisplayItem(), poseStack, buffer, combinedLight);
    }

    public static boolean shouldRenderNameplate(TrophyBlockEntity trophyBlockEntity, LocalPlayer player){
//        double radius = player.isCrouching() ? -1D : 8D;
        double radius = 8D;
        if (radius <= 0) return false;

        BlockPos boxPos = trophyBlockEntity.getBlockPos();
        double boxX = boxPos.getX() + 0.5D;
        double boxY = boxPos.getY() + 0.5D;
        double boxZ = boxPos.getZ() + 0.5D;
        double distanceSquared = player.distanceToSqr(boxX, boxY, boxZ);
        return distanceSquared < radius * radius;
    }

    public static void renderName(Minecraft mc, TrophyBlockEntity trophyBlock, String stringName, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn){
        Font fontRenderer = mc.font;
        Quaternion cameraRotation = mc.getEntityRenderDispatcher().cameraOrientation();
        Component displayNameIn = new TextComponent(stringName);
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 1.65F, 0.5F);
        matrixStackIn.mulPose(cameraRotation);
        matrixStackIn.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = matrixStackIn.last().pose();
        float backgroundOpacity = mc.options.getBackgroundOpacity(0.25F);
        int alpha = (int) (backgroundOpacity * 255.0F) << 24;
        float textOffset = -fontRenderer.width(displayNameIn) / 2;
        fontRenderer.drawInBatch(displayNameIn, textOffset, 0F, 553648127, false, matrix4f, bufferIn, false, alpha, packedLightIn);
        fontRenderer.drawInBatch(displayNameIn, textOffset, 0F, -1, false, matrix4f, bufferIn, false, 0, packedLightIn);

        matrixStackIn.popPose();
    }

    public static void renderItem(Minecraft minecraft, TrophyBlockEntity trophyBlock, Item item, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn){
        Quaternion cameraRotation = minecraft.getEntityRenderDispatcher().cameraOrientation();
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F,  1.13F, 0.5F);

//        Change the rotation of the item to be in the same direction as the block
        matrixStackIn.mulPose(trophyBlock.getBlockState().getValue(TrophyBlock.FACING).getRotation());
//        Rotate 90 degrees on the x axis
        matrixStackIn.mulPose(Vector3f.XP.rotation((float) Math.toRadians(-90)));

        matrixStackIn.scale(0.35F, 0.35F, 0.35F);
        BakedModel itemModel = itemRenderer.getModel(new ItemStack(item), null, null, 0);
        itemRenderer.render(new ItemStack(item), ItemTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, itemModel);
        matrixStackIn.popPose();
//        RenderSystem.enableCull();
    }

    public static void toggleRenderName(BlockPos pos) {
        if(positionsWithoutName.contains(pos)) {
            positionsWithoutName.remove(pos);
        } else {
            positionsWithoutName.add(pos);
        }
    }
}
