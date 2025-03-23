package net.vorrikz.vorrikzlearning.registries.util.attributes;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FenceGateAttributes extends BlockAttributes {
    private WoodType woodType;

    public FenceGateAttributes() {
        super(2f, null);
        this.woodType = WoodType.ACACIA;
    }

    public WoodType getWoodType() {
        return this.woodType;
    }

    public FenceGateAttributes setWoodType(WoodType woodType1) {
        this.woodType = woodType1;
        return this;
    }
}
