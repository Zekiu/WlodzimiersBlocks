package xyz.zekiu.wlodzimiers_blocks.blocks;

import org.jetbrains.annotations.Nullable;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.MultiPolymerBlockModel;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.virtualentity.api.BlockWithElementHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import xyz.nucleoid.packettweaker.PacketContext;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;

public class FridgeBlock extends Block implements PolymerBlock, BlockWithElementHolder {
    private static final EnumProperty<Direction> FACING = Properties.FACING;
    private static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public FridgeBlock(Settings settings, BlockModelType type, MultiPolymerBlockModel model) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(HALF, DoubleBlockHalf.LOWER));
    }

    private static void register(String modelId, BlockModelType type, MultiPolymerBlockModel model) {
        var id = Identifier.of(WlodzimiersBlocks.MOD_ID, modelId);
        var block = Registry.register(Registries.BLOCK, id,
                new FridgeBlock(Block.Settings.copy(Blocks.IRON_BLOCK).registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)), type, model));
        var item = new PolymerBlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        WlodzimiersBlocks.items.add(item);

        Registry.register(Registries.ITEM, id, item);
    }

    public static void registerBlocks() {
        register("fridge", BlockModelType.FULL_BLOCK, MultiPolymerBlockModel.of()
            .with(Identifier.of(WlodzimiersBlocks.MOD_ID, "block/fridge_top"))
            .with(Identifier.of(WlodzimiersBlocks.MOD_ID, "block/fridge_bottom"))
        );
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return Blocks.BARRIER.getDefaultState();
    }
}