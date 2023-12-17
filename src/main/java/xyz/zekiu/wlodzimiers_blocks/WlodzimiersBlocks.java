package xyz.zekiu.wlodzimiers_blocks;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.zekiu.wlodzimiers_blocks.blocks.KitchenTilesBlock;
import xyz.zekiu.wlodzimiers_blocks.items.KitchenTilesItem;

public class WlodzimiersBlocks implements ModInitializer {
    public static final String MOD_ID = "wlodzimiers_blocks";
    public static final Logger LOGGER = LoggerFactory.getLogger("WlodzimiersBlocks");
    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("wlodzimiers_blocks");
        PolymerResourcePackUtils.markAsRequired();
        register(BlockModelType.FULL_BLOCK,"kitchen_tiles");
        LOGGER.info("Wlodzimiers Blocks zostało załadowane miaał");
    }
    public static void register(BlockModelType type, String modelId) {
        var id = new Identifier(MOD_ID, modelId);
        var block = Registry.register(Registries.BLOCK, id,
                new KitchenTilesBlock(FabricBlockSettings.copy(Blocks.BLACK_CONCRETE), type, modelId));

        Registry.register(Registries.ITEM, id, new KitchenTilesItem(new Item.Settings(), block, modelId));
    }
}
