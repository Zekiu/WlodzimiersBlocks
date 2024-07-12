package xyz.zekiu.wlodzimiers_blocks.items;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;

public class SimpleCustomBlockItem extends BlockItem implements PolymerItem{
    private final PolymerModelData polymerModel;

    public SimpleCustomBlockItem(Settings settings, Block block, String modelId) {
        super(block, settings);
        this.polymerModel = PolymerResourcePackUtils.requestModel(Items.BARRIER, Identifier.of(WlodzimiersBlocks.MOD_ID, modelId));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModel.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModel.value();
    }
}
