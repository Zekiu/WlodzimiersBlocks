package xyz.zekiu.wlodzimiers_blocks.blocks;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;

public class SimpleCustomBlock extends Block implements PolymerTexturedBlock {
    private final BlockState polymerBlockState;

    public SimpleCustomBlock(Settings settings, BlockModelType type, String modelId) {
        super(settings);
        this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(type, PolymerBlockModel.of(Identifier.of(WlodzimiersBlocks.MOD_ID, "block/" + modelId)));
    }

    private static void register(String modelId, BlockModelType type, Block mimicBlock) {
        var id = Identifier.of(WlodzimiersBlocks.MOD_ID, modelId);
        var block = Registry.register(Registries.BLOCK, id,
                new SimpleCustomBlock(Block.Settings.copy(mimicBlock).registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)), type, modelId));
        var item = new PolymerBlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        WlodzimiersBlocks.items.add(item);

        Registry.register(Registries.ITEM, id, item);
    }

    public static void registerBlocks() {
        register("black_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.BLACK_CONCRETE);
        register("blue_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.BLUE_CONCRETE);
        register("brown_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.BROWN_CONCRETE);
        register("cyan_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.CYAN_CONCRETE);
        register("gray_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.GRAY_CONCRETE);
        register("green_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.GREEN_CONCRETE);
        register("light_blue_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.LIGHT_BLUE_CONCRETE);
        register("light_gray_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.LIGHT_GRAY_CONCRETE);
        register("lime_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.LIME_CONCRETE);
        register("magenta_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.MAGENTA_CONCRETE);
        register("orange_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.ORANGE_CONCRETE);
        register("pink_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.PINK_CONCRETE);
        register("purple_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.PURPLE_CONCRETE);
        register("red_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.RED_CONCRETE);
        register("yellow_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.YELLOW_CONCRETE);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return this.polymerBlockState;
    }
}
