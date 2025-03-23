package net.vorrikz.vorrikzlearning.registries.util.attributes;

import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ButtonAttributes extends BlockAttributes {
    private BlockSetType blockSetType;
    private int ticksToStayPressed;

    public ButtonAttributes() {
        super(2f, null);
        this.blockSetType = BlockSetType.IRON;
        this.ticksToStayPressed = 20;
    }

    public BlockSetType getBlockSetType() {
        return this.blockSetType;
    }

    public int getTicksToStayPressed() {
        return this.ticksToStayPressed;
    }

    public ButtonAttributes setBlockSetType(BlockSetType blockSetType1) {
        this.blockSetType = blockSetType1;
        return this;
    }

    public ButtonAttributes setTicksToStayPressed(int ticks) {
        this.ticksToStayPressed = ticks;
        return this;
    }

}
