package net.vorrikz.vorrikzlearning.registries.util.attributes;

import net.minecraft.world.level.block.SoundType;

import javax.annotation.Nullable;

public class BlockAttributes {
    private float strength = 2.0f; // Default strength
    private SoundType sound = null; // Default sound

    public float getStrength() {
        return strength;
    }

    public BlockAttributes(float strength, @Nullable SoundType sound) {
        this.strength = strength;
        this.sound = sound;
    }

    public BlockAttributes setStrength(float strength) {
        this.strength = strength;
        return this;
    }

    public SoundType getSound() {
        return sound;
    }

    public BlockAttributes setSound(SoundType sound) {
        this.sound = sound;
        return this;
    }
}
