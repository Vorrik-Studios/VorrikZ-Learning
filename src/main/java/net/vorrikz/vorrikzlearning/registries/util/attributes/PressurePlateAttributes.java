package net.vorrikz.vorrikzlearning.registries.util.attributes;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class PressurePlateAttributes extends BlockAttributes {
    private BlockSetType blockSetType;

    public PressurePlateAttributes() {
        super(2f, null);
        this.blockSetType = BlockSetType.IRON;
    }

    public BlockSetType getBlockSetType() {
        return blockSetType;
    }

    public PressurePlateAttributes setBlockSetType(BlockSetType blockSetType1) {
        this.blockSetType = blockSetType1;
        return this;
    }
}
