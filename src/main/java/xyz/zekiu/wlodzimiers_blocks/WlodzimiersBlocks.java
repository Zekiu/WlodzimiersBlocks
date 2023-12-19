package xyz.zekiu.wlodzimiers_blocks;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.zekiu.wlodzimiers_blocks.blocks.SimpleCustomBlock;

public class WlodzimiersBlocks implements ModInitializer {
    public static final String MOD_ID = "wlodzimiers_blocks";
    public static final Logger LOGGER = LoggerFactory.getLogger("WlodzimiersBlocks");
    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets(MOD_ID);
        PolymerResourcePackUtils.markAsRequired();

//      Kitchen Tiles Blocks
        SimpleCustomBlock.register("black_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.BLACK_CONCRETE);
        SimpleCustomBlock.register("blue_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.BLUE_CONCRETE);
        SimpleCustomBlock.register("brown_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.BROWN_CONCRETE);
        SimpleCustomBlock.register("cyan_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.CYAN_CONCRETE);
        SimpleCustomBlock.register("gray_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.GRAY_CONCRETE);
        SimpleCustomBlock.register("green_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.GREEN_CONCRETE);
        SimpleCustomBlock.register("light_blue_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.LIGHT_BLUE_CONCRETE);
        SimpleCustomBlock.register("light_gray_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.LIGHT_GRAY_CONCRETE);
        SimpleCustomBlock.register("lime_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.LIME_CONCRETE);
        SimpleCustomBlock.register("magenta_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.MAGENTA_CONCRETE);
        SimpleCustomBlock.register("orange_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.ORANGE_CONCRETE);
        SimpleCustomBlock.register("pink_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.PINK_CONCRETE);
        SimpleCustomBlock.register("purple_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.PURPLE_CONCRETE);
        SimpleCustomBlock.register("red_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.RED_CONCRETE);
        SimpleCustomBlock.register("yellow_kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.YELLOW_CONCRETE);

//      SimpleCustomBlock.register("id_bloku", BlockModelType.KSZTALT_BLOKU, Blocks.WLASCIWOSCI_BLOKU_VANILLA);   ||| DLA PROSTYCH BLOKÓW!!! Pamiętaj aby dodać potrzebne pliki .json w folderze assets oraz data!!!!

        LOGGER.info("Wlodzimiers Blocks zostało załadowane miaał");
    }
}
