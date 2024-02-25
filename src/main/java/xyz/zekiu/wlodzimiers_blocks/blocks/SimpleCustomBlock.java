package xyz.zekiu.wlodzimiers_blocks.blocks;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;
import xyz.zekiu.wlodzimiers_blocks.items.SimpleCustomBlockItem;

public class SimpleCustomBlock extends Block implements PolymerTexturedBlock {
    private final BlockState polymerBlockState;

    public SimpleCustomBlock(Settings settings, BlockModelType type, String modelId) {
        super(settings);
        this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(type, PolymerBlockModel.of(new Identifier(WlodzimiersBlocks.MOD_ID, modelId)));
    }

    private static void register(String modelId, BlockModelType type, AbstractBlock abstractBlock) {
        var modId = new Identifier(WlodzimiersBlocks.MOD_ID, modelId);
        var block = Registry.register(Registries.BLOCK, modId,
                new SimpleCustomBlock(FabricBlockSettings.copy(abstractBlock), type, modelId));

        Registry.register(Registries.ITEM, modId, new SimpleCustomBlockItem(new Item.Settings(), block, modelId));
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

//      register("id_bloku", BlockModelType.KSZTALT_BLOKU, Blocks.WLASCIWOSCI_BLOKU_VANILLA);   ||| DLA PROSTYCH BLOKÓW!!! Pamiętaj aby dodać potrzebne pliki .json w folderze assets oraz data!!!!
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return this.polymerBlockState.getBlock();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return this.polymerBlockState;
    }
}
