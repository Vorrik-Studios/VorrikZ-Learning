package net.vorrikz.vorrikzlearning.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.vorrikz.vorrikzlearning.VorrikZLearning;
import net.vorrikz.vorrikzlearning.block.ModBlocks;
import net.vorrikz.vorrikzlearning.item.ModItems;
import net.vorrikz.vorrikzlearning.util.ModTranslatables;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, VorrikZLearning.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(ModBlocks.BISMUTH_BLOCK.get(), "Bismuth Block");
        this.add(ModBlocks.BISMUTH_BUTTON.get(), "Bismuth Button");
        this.add(ModBlocks.BISMUTH_DOOR.get(), "Bismuth Door");
        this.add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(), "Deepslate Bismuth Ore");
        this.add(ModBlocks.BISMUTH_ORE.get(), "Bismuth Ore");
        this.add(ModBlocks.BISMUTH_FENCE.get(), "Bismuth Fence");
        this.add(ModBlocks.BISMUTH_FENCE_GATE.get(), "Bismuth Fence Gate");
        this.add(ModBlocks.BISMUTH_WALL.get(), "Bismuth Wall");
        this.add(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), "Bismuth Pressure Plate");
        this.add(ModBlocks.BISMUTH_STAIRS.get(), "Bismuth Stairs");
        this.add(ModBlocks.BISMUTH_SLAB.get(), "Bismuth Slab");
        this.add(ModBlocks.BISMUTH_TRAPDOOR.get(), "Bismuth Trapdoor");
        this.add(ModBlocks.MAGIC_BLOCK.get(), "Magic Block");
        this.add(ModBlocks.BISMUTH_LAMP.get(), "Bismuth Lamp");

        this.add(ModItems.BISMUTH.get(), "Bismuth");
        this.add(ModItems.RAW_BISMUTH.get(), "Raw Bismuth");
        this.add(ModItems.FROSTFIRE_ICE.get(), "Frostfire Ice");
        this.add(ModItems.STARLIGHT_ASHES.get(), "Starlight Ashes");
        this.add(ModItems.RADISH.get(), "Radish");
        this.add(ModItems.CHISEL.get(), "Chisel");
        this.add(ModItems.BISMUTH_SWORD.get(), "Bismuth Sword");
        this.add(ModItems.BISMUTH_SHOVEL.get(), "Bismuth Shovel");
        this.add(ModItems.BISMUTH_PICKAXE.get(), "Bismuth Pickaxe");
        this.add(ModItems.BISMUTH_AXE.get(), "Bismuth Axe");
        this.add(ModItems.BISMUTH_HOE.get(), "Bismuth Hoe");
        this.add(ModItems.BISMUTH_HAMMER.get(), "Bismuth Hammer");
        this.add(ModItems.BISMUTH_HELMET.get(), "Bismuth Helmet");
        this.add(ModItems.BISMUTH_CHESTPLATE.get(), "Bismuth Chestplate");
        this.add(ModItems.BISMUTH_LEGGINGS.get(), "Bismuth Leggings");
        this.add(ModItems.BISMUTH_BOOTS.get(), "Bismuth Boots");

        this.add(ModTranslatables.creativeTabString("bismuth_items"), "VorrikZ Items");
        this.add(ModTranslatables.creativeTabString("bismuth_block"), "VorrikZ Blocks");

        this.add(ModTranslatables.tooltipString("radish"), "Tastes really great!");
        this.add(ModTranslatables.tooltipString("chisel"), "Press §eShift§r for more Information");
        this.add(ModTranslatables.tooltipString("chisel.shift_down"), "This Item can chisel Blocks into Bricks");
        this.add(ModTranslatables.tooltipString("magic_block"), "This Block is very §9MAGICAL§r");
    }
}
