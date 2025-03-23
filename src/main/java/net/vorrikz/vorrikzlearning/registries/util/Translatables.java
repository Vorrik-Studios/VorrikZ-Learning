package net.vorrikz.vorrikzlearning.registries.util;

import net.minecraft.network.chat.Component;
import net.vorrikz.vorrikzlearning.VorrikzLearning;

public class Translatables {
    private static final String MOD_ID = VorrikzLearning.MOD_ID + ".";
    public static Component tooltip(String name) {
        return Component.translatable("tooltip." + MOD_ID + name + ".tooltip");
    }
    public static Component creative_tab(String name) {
        return Component.translatable("creativetab." + MOD_ID + name + "_tab");
    }
}
