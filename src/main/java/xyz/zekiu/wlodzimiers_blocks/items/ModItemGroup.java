package xyz.zekiu.wlodzimiers_blocks.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.zekiu.wlodzimiers_blocks.WlodzimiersBlocks;
import xyz.zekiu.wlodzimiers_blocks.blocks.ModBlocks;

public class ModItemGroup {
    public static final ItemGroup WLODZIMIERS_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WlodzimiersBlocks.MOD_ID, "kitchen_tiles"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.wlodzimiers_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.KITCHEN_TILES)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.KITCHEN_TILES);
                    }).build());

    public static void registerItemGroups() {
        WlodzimiersBlocks.LOGGER.info("Grupa itemów została zarejestrowana miaał");
    }
}
