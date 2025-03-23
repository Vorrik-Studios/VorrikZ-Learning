package net.vorrikz.vorrikzlearning.registries.creativetabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.vorrikz.vorrikzlearning.registries.blocks.ModBlocks;
import net.vorrikz.vorrikzlearning.registries.items.ModItems;
import net.vorrikz.vorrikzlearning.registries.util.Registries;
import net.vorrikz.vorrikzlearning.registries.util.Translatables;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final Supplier<CreativeModeTab> VORRIKZ_TAB = Registries.CreativeModeTabs.tab("vorrikz",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                    .title(Translatables.creative_tab("vorrikz"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.STARLIGHT_ASHES);
                        output.accept(ModItems.FROSTFIRE_ICE);
                        output.accept(ModItems.RADISH);

                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.MAGIC_BLOCK);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                    }).build());

    public static void register(IEventBus eventBus) { Registries.CreativeModeTabs.register(eventBus); }
}
