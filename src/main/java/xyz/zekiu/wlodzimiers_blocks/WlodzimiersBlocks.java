package xyz.zekiu.wlodzimiers_blocks;

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
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


        SimpleCustomBlock.registerBlocks();
        PolymerItemGroupUtils.registerPolymerItemGroup(
                Identifier.of(MOD_ID, MOD_ID),
                new ItemGroup.Builder(ItemGroup.Row.BOTTOM, -1)
                        .displayName(Text.literal("Wlodzimiers Blocks"))
                        .icon(() -> new ItemStack(SimpleCustomBlock.items.get(0)))
                        .entries(((c, e) -> {
                            for (Item item : SimpleCustomBlock.items) e.add(item);
                        }))
                        .build());

        LOGGER.info("Wlodzimiers Blocks zostało załadowane miaał");
    }
}
