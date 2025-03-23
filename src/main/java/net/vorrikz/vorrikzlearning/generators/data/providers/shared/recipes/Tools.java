package net.vorrikz.vorrikzlearning.generators.data.providers.shared.recipes;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.vorrikz.vorrikzlearning.VorrikzLearning;

public class Tools {
    public enum ToolType {
        SHOVEL(" # ", " | ", " | "),
        PICKAXE("###", " | ", " | "),
        AXE("## ", "#| ", " | "),
        HOE("## ", " | ", " | "),
        SWORD(" # ", " # ", " | ");

        private final String[] pattern;

        ToolType(String... pattern) {
            this.pattern = pattern;
        }

        public String[] getPattern() {
            return pattern;
        }
    }

    public static void registerToolRecipe(RecipeOutput recipeOutput, ToolType type, ItemLike result, String resultName,
                                          ItemLike material, String materialName, String group) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result);

        for (String row : type.getPattern()) {
            recipe.pattern(row);
        }

        recipe.define('#', material)
                .define('|', Items.STICK)
                .group(group)
                .unlockedBy("has_" + materialName,
                        InventoryChangeTrigger.TriggerInstance.hasItems(material))
                .save(recipeOutput, VorrikzLearning.MOD_ID + ":" + resultName + "_from_" + materialName + "_and_stick");
    }
}
