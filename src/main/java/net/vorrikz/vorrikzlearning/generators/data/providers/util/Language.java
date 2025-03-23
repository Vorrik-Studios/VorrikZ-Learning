package net.vorrikz.vorrikzlearning.generators.data.providers.util;

import net.vorrikz.vorrikzlearning.VorrikzLearning;

public class Language {
    private static final String MOD_ID = VorrikzLearning.MOD_ID + ".";

    public static class Items {
        public static String item(String name) {
            return "item." + MOD_ID + name;
        }
    }

    public static class Blocks {
        public static String block(String name, boolean suffix) {
            String block_translatable = "block." + MOD_ID + name;
            if (suffix) block_translatable += "_block";
            return block_translatable;
        }
        public static String block(String name) {
            return block(name, true);
        }
        public static String ore(String name, boolean suffix) {
            String block_translatable = "block." + MOD_ID + name;
            if (suffix) block_translatable += "_ore";
            return block_translatable;
        }
        public static String ore(String name) {
            return ore(name, true);
        }
    }

    public static class Tooltips {
        public static String tooltip(String name) {
            return "tooltip." + MOD_ID + name + ".tooltip";
        }
    }

    public static class CreativeTabs {
        public static String creative_tab(String name) {
            return "creativetab." + MOD_ID + name;
        }
    }
}
