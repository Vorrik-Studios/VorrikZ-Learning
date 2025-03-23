package net.vorrikz.vorrikzlearning.generators.data.providers.shared;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.vorrikz.vorrikzlearning.VorrikzLearning;
import net.vorrikz.vorrikzlearning.generators.data.providers.util.Language;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        super(output, VorrikzLearning.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(Language.Items.item("bismuth"), "Bismuth");
        this.add(Language.Items.item("raw_bismuth"), "Raw Bismuth");
        this.add(Language.Items.item("chisel"), "Chisel");
        this.add(Language.Items.item("radish"), "Radish");
        this.add(Language.Items.item("frostfire_ice"), "Frostfire Ice");
        this.add(Language.Items.item("starlight_ashes"), "Starlight Ashes");
        this.add(Language.Blocks.block("bismuth"), "Block of Bismuth");
        this.add(Language.Blocks.block("magic_block", false), "Magic Block");
        this.add(Language.Blocks.ore("bismuth"), "Bismuth Ore");
        this.add(Language.Blocks.ore("bismuth_deepslate"), "Deepslate Bismuth Ore");
        this.add(Language.Blocks.NonBlocks.fence("bismuth"), "Bismuth Fence");
        this.add(Language.Blocks.NonBlocks.fence_gate("bismuth"), "Bismuth Fence Gate");
        this.add(Language.Blocks.NonBlocks.wall("bismuth"), "Bismuth Wall");
        this.add(Language.Blocks.NonBlocks.stairs("bismuth"), "Bismuth Stairs");
        this.add(Language.Blocks.NonBlocks.slab("bismuth"), "Bismuth Slab");
        this.add(Language.Blocks.NonBlocks.button("bismuth"), "Bismuth Button");
        this.add(Language.Blocks.NonBlocks.pressure_plate("bismuth"), "Bismuth Pressure Plate");
        this.add(Language.Blocks.NonBlocks.door("bismuth"), "Bismuth Door");
        this.add(Language.Blocks.NonBlocks.trapdoor("bismuth"), "Bismuth Trapdoor");

        this.add(Language.Tooltips.tooltip("magic_block"), "This Block is very §9MAGICAL§r");
        this.add(Language.Tooltips.tooltip("radish"), "Tastes really great!");
        this.add(Language.Tooltips.tooltip("chisel"), "Press §eShift§r for more Information");
        this.add(Language.Tooltips.tooltip("chisel.shift_down"), "This Item can chisel Blocks into Bricks");
        this.add(Language.CreativeTabs.creative_tab("vorrikz_tab"), "Learning VorrikZ");
    }
}
