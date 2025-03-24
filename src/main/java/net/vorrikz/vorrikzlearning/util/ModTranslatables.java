package net.vorrikz.vorrikzlearning.util;

import net.minecraft.network.chat.Component;
import net.vorrikz.vorrikzlearning.VorrikZLearning;


public class ModTranslatables {
    private static final String MOD_ID = VorrikZLearning.MOD_ID + ".";
    public static Component creativeTab(String name) {
        return Component.translatable("creativetab." + MOD_ID + name);
    }
    public static String creativeTabString(String name) {
        return "creativetab." + MOD_ID + name;
    }
    public static Component tooltip(String name) {
        return Component.translatable("tooltip." + MOD_ID + name + ".tooltip");
    }
    public static String tooltipString(String name) {
        return "tooltip." + MOD_ID + name + ".tooltip";
    }
}
