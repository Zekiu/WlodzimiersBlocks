package xyz.zekiu.wlodzimiers_blocks.blocks;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import com.mojang.serialization.MapCodec;

import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import xyz.nucleoid.packettweaker.PacketContext;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;

public class FridgeBlock extends BlockWithEntity implements FactoryBlock, PolymerTexturedBlock {
    private static final EnumProperty<Direction> FACING = Properties.FACING;
    private static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public FridgeBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(HALF, DoubleBlockHalf.LOWER));
    }

    private static void register(String modelId, Block mimicBlock) {
        var id = Identifier.of(WlodzimiersBlocks.MOD_ID, modelId);
        var block = Registry.register(Registries.BLOCK, id,
                new FridgeBlock(Block.Settings.copy(mimicBlock).registryKey(RegistryKey.of(RegistryKeys.BLOCK, id))));
        var item = new PolymerBlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        WlodzimiersBlocks.items.add(item);

        Registry.register(Registries.ITEM, id, item);
    }

    public static void registerBlocks() {
        register("iron_fridge", Blocks.IRON_BLOCK);
        // register("copper_fridge", Blocks.COPPER_BLOCK);
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!ctx.getWorld().getBlockState(ctx.getBlockPos().up()).canReplace(ItemPlacementContext.offset(ctx, ctx.getBlockPos().up(), Direction.DOWN))) {
            return null;
        }
        return super.getPlacementState(ctx).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient()) {
            BlockPos blockPos = pos.up();
            world.setBlockState(blockPos, state.with(HALF, DoubleBlockHalf.UPPER), 3);
            world.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(world, pos, 3);
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        var half = state.get(HALF);
        if (((half == DoubleBlockHalf.LOWER && direction == Direction.UP) || (half == DoubleBlockHalf.UPPER && direction == Direction.DOWN)) && !neighborState.isOf(this)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return Blocks.BARRIER.getDefaultState();
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FridgeBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return initialBlockState.get(HALF) == DoubleBlockHalf.LOWER ? new FridgeModel(initialBlockState, pos) : null;
    }

    public static final class FridgeModel extends BlockModel {
        private final ItemDisplayElement main;

        public FridgeModel(BlockState state, BlockPos pos) {
            this.main = ItemDisplayElementUtil.createSimple(state.getBlock().asItem());
            this.main.setScale(new Vector3f(2));
            this.main.setTranslation(new Vector3f(0, 0.5f, 0));
            this.main.setDisplaySize(1, 1);
            this.main.setYaw(state.get(FACING).getPositiveHorizontalDegrees());

            this.addElement(this.main);
        }
    }
}