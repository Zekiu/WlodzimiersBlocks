package xyz.zekiu.wlodzimiers_blocks.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;

public class ModBlocks {
    public static Block KITCHEN_TILES = registerBlock("kitchen_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(WlodzimiersBlocks.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(WlodzimiersBlocks.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        WlodzimiersBlocks.LOGGER.info("Registering Mod Blocks for " + WlodzimiersBlocks.MOD_ID);
    }
}
