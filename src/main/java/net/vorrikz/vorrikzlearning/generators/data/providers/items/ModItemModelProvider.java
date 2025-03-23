package net.vorrikz.vorrikzlearning.generators.data.providers.items;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.vorrikz.vorrikzlearning.VorrikzLearning;
import net.vorrikz.vorrikzlearning.registries.blocks.ModBlocks;
import net.vorrikz.vorrikzlearning.registries.items.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VorrikzLearning.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BISMUTH.get());
        basicItem(ModItems.RAW_BISMUTH.get());

        basicItem(ModItems.RADISH.get());
        basicItem(ModItems.STARLIGHT_ASHES.get());
        basicItem(ModItems.FROSTFIRE_ICE.get());
        basicItem(ModItems.CHISEL.get());

        buttonItem(ModBlocks.BISMUTH_BUTTON, ModBlocks.BISMUTH_BLOCK);
        fenceItem(ModBlocks.BISMUTH_FENCE, ModBlocks.BISMUTH_BLOCK);
        wallItem(ModBlocks.BISMUTH_WALL, ModBlocks.BISMUTH_BLOCK);

        basicItem(ModBlocks.BISMUTH_DOOR.asItem());
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(VorrikzLearning.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(VorrikzLearning.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(VorrikzLearning.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}