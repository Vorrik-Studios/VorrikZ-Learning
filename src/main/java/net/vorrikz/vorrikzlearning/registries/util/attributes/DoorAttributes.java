package net.vorrikz.vorrikzlearning.registries.util.attributes;

import net.minecraft.world.level.block.state.properties.BlockSetType;

public class DoorAttributes extends BlockAttributes {
    private BlockSetType blockSetType;

    public DoorAttributes() {
        super(2f, null);
        this.blockSetType = BlockSetType.IRON;
    }

    public BlockSetType getBlockSetType() {
        return blockSetType;
    }

    public DoorAttributes setBlockSetType(BlockSetType blockSetType1) {
        this.blockSetType = blockSetType1;
        return this;
    }
}
