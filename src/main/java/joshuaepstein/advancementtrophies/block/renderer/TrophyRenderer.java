package joshuaepstein.advancementtrophies.block.renderer;

import joshuaepstein.advancementtrophies.block.TrophyBlock;
import joshuaepstein.advancementtrophies.block.entity.TrophyBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.scoreboard.ScoreboardCriterion.XP;

public class TrophyRenderer implements BlockEntityRenderer<TrophyBlockEntity> {

    private static ItemRenderer itemRenderer;
    public static List<BlockPos> positionsWithoutName = new ArrayList<>();

    public TrophyRenderer(BlockEntityRendererFactory.Context context){
    }

    @Override
    public void render(TrophyBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity player = mc.player;
        itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        String advancementName = entity.getAdvancementName();
        if(player != null && advancementName != null && shouldRenderNameplate(entity, player)){
            renderName(mc, entity, advancementName, matrices, vertexConsumers, light);
        }
        renderItem(mc, entity, entity.getDisplayItem(), matrices, vertexConsumers, light);
    }

    public static boolean shouldRenderNameplate(TrophyBlockEntity trophyBlockEntity, ClientPlayerEntity player){
//        double radius = player.isCrouching() ? -1D : 8D;
        double radius = 8D;
        if (radius <= 0) return false;

        BlockPos boxPos = trophyBlockEntity.getPos();
        double boxX = boxPos.getX() + 0.5D;
        double boxY = boxPos.getY() + 0.5D;
        double boxZ = boxPos.getZ() + 0.5D;
        double distanceSquared = player.squaredDistanceTo(boxX, boxY, boxZ);
        return distanceSquared < radius * radius;
    }

    public static void renderName(net.minecraft.client.MinecraftClient mc, TrophyBlockEntity trophyBlock, String stringName, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn){
        TextRenderer fontRenderer = mc.textRenderer;
        Quaternion cameraRotation = mc.getEntityRenderDispatcher().camera.getRotation();
        Text displayNameIn = Text.literal(stringName);
        matrixStackIn.push();
        matrixStackIn.translate(0.5F, 1.65F, 0.5F);
        matrixStackIn.multiply(cameraRotation);
        matrixStackIn.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = matrixStackIn.peek().getPositionMatrix();
        float backgroundOpacity = mc.options.getTextBackgroundOpacity(0.25F);
        int alpha = (int) (backgroundOpacity * 255.0F) << 24;
        float textOffset = -fontRenderer.getWidth(displayNameIn) / 2;
        fontRenderer.draw(displayNameIn, textOffset, 0F, 553648127, false, matrix4f, bufferIn, false, alpha, packedLightIn);
        fontRenderer.draw(displayNameIn, textOffset, 0F, -1, false, matrix4f, bufferIn, false, 0, packedLightIn);

        matrixStackIn.pop();
    }

    public static void renderItem(MinecraftClient minecraft, TrophyBlockEntity trophyBlock, Item item, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn){
        Quaternion cameraRotation = minecraft.getEntityRenderDispatcher().camera.getRotation();
        matrixStackIn.push();
        matrixStackIn.translate(0.5F,  1.13F, 0.5F);

//        Change the rotation of the item to be in the same direction as the block
        matrixStackIn.multiply(trophyBlock.getCachedState().get(TrophyBlock.FACING).getRotationQuaternion());
//        Rotate 90 degrees on the x axis
        matrixStackIn.multiply(Vec3f.POSITIVE_X.getRadialQuaternion((float) Math.toRadians(-90)));
        matrixStackIn.scale(0.35F, 0.35F, 0.35F);
        BakedModel itemModel = itemRenderer.getModel(new ItemStack(item), null, null, 0);
        itemRenderer.renderItem(new ItemStack(item), ModelTransformation.Mode.NONE, false, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.DEFAULT_UV, itemModel);
        matrixStackIn.pop();
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
