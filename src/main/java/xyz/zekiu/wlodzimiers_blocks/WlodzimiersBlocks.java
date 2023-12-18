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

        SimpleCustomBlock.register("kitchen_tiles", BlockModelType.FULL_BLOCK, Blocks.BLACK_CONCRETE);
//      SimpleCustomBlock.register("id_bloku", BlockModelType.KSZTALT_BLOKU, Blocks.WLASCIWOSCI_BLOKU_VANILLA);   ||| DLA PROSTYCH BLOKÓW!!! Pamiętaj aby dodać potrzebne pliki .json w folderze assets oraz data!!!!

        LOGGER.info("Wlodzimiers Blocks zostało załadowane miaał");
    }
}
