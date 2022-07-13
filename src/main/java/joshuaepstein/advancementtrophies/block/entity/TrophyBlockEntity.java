package joshuaepstein.advancementtrophies.block.entity;

import joshuaepstein.advancementtrophies.block.renderer.TrophyRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import joshuaepstein.advancementtrophies.Main;
import org.jetbrains.annotations.Nullable;

public class TrophyBlockEntity extends BlockEntity {

    private String advancementName = "Blank";
    private String advancementDisplayItem = "minecraft:air";

    public TrophyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, String name, String displayItem){
        super(type, pos, state);
        this.advancementName = name;
        this.advancementDisplayItem = displayItem;
    }

    public TrophyBlockEntity(BlockPos pos, BlockState state){
        super(Main.TROPHY_BLOCK_ENTITY, pos, state);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        return ActionResult.CONSUME;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("advancementName", advancementName);
        nbt.putString("advancementDisplayItem", advancementDisplayItem);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.advancementDisplayItem = nbt.getString("advancementDisplayItem");
        this.advancementName = nbt.getString("advancementName");
//        this.markDirty();
    }

    public ItemStack getPickStack(){
        return getTrophyItemStack(advancementName, advancementDisplayItem);
    }

    public static ItemStack getTrophyItemStack(String name, String displayItem){
        NbtCompound nbt = new NbtCompound();
        ItemStack itemStack = new ItemStack(Main.TROPHY);
        NbtCompound blockEntityTag = new NbtCompound();
        blockEntityTag.putString("advancementName", name);
        blockEntityTag.putString("advancementDisplayItem", displayItem);
        nbt.put("BlockEntityTag", blockEntityTag);
        itemStack.setNbt(nbt);
        itemStack.setCustomName(Text.literal(name + " Trophy").setStyle(Style.EMPTY.withColor(Formatting.GOLD).withItalic(false)));
        return itemStack;
    }

    public String getAdvancementName() {
        return advancementName;
    }

    public String getAdvancementDisplayItem() {
        return advancementDisplayItem;
    }

    public Item getDisplayItem(){
        return Registry.ITEM.get(new Identifier(advancementDisplayItem));
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public static void tick(World world, BlockPos pos, BlockState state, TrophyBlockEntity be){
    }

}
