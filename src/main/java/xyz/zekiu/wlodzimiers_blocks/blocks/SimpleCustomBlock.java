package xyz.zekiu.wlodzimiers_blocks.blocks;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;
import xyz.zekiu.wlodzimiers_blocks.items.SimpleCustomItem;

public class SimpleCustomBlock extends Block implements PolymerTexturedBlock {
    private final BlockState polymerBlockState;

    public SimpleCustomBlock(Settings settings, BlockModelType type, String modelId) {
        super(settings);
        this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(type, PolymerBlockModel.of(new Identifier(WlodzimiersBlocks.MOD_ID, modelId)));
    }

    public static void register(String modelId, BlockModelType type, AbstractBlock abstractBlock) {
        var modId = new Identifier(WlodzimiersBlocks.MOD_ID, modelId);
        var block = Registry.register(Registries.BLOCK, modId,
                new SimpleCustomBlock(FabricBlockSettings.copy(abstractBlock), type, modelId));

        Registry.register(Registries.ITEM, modId, new SimpleCustomItem(new Item.Settings(), block, modelId));
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
