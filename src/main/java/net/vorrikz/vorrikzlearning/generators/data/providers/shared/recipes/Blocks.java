package net.vorrikz.vorrikzlearning.generators.data.providers.shared.recipes;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.vorrikz.vorrikzlearning.VorrikzLearning;

public class Blocks {

    public static void registerStorageRecipe(RecipeOutput recipeOutput, RecipeCategory uncompressedCategory,
                                             RecipeCategory compressedCategory, StorageRecipe recipe) {
        // Compressed block (9x9)
        ShapedRecipeBuilder.shaped(compressedCategory, recipe.compressed())
                .pattern("###").pattern("###").pattern("###")
                .define('#', recipe.uncompressed())
                .group(recipe.group())
                .unlockedBy("has_" + recipe.uncompressedName(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(recipe.uncompressed()))
                .save(recipeOutput, recipe.recipeId("_from_"));

        // Uncrafting (breaking back into 9 items)
        ShapelessRecipeBuilder.shapeless(uncompressedCategory, recipe.uncompressed(), 9)
                .requires(recipe.compressed())
                .group(recipe.group())
                .unlockedBy("has_" + recipe.compressedName(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(recipe.compressed()))
                .save(recipeOutput, recipe.recipeId("_to_"));
    }

    public static void registerCrossFilledRecipe(RecipeOutput recipeOutput, RecipeCategory category, CrossFilledRecipe recipe) {
        // Main recipe
        ShapedRecipeBuilder.shaped(category, recipe.result())
                .pattern("#@#").pattern("@@@").pattern("#@#")
                .define('#', recipe.external()).define('@', recipe.internal())
                .group(recipe.group())
                .unlockedBy("has_" + recipe.internalName(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(recipe.internal()))
                .unlockedBy("has_" + recipe.externalName(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(recipe.external()))
                .save(recipeOutput, recipe.recipeId("_from_"));

        // Optional uncrafting recipe
        if (recipe.addUncraft()) {
            ShapelessRecipeBuilder.shapeless(category, recipe.uncraftResult(), recipe.uncraftAmount())
                    .requires(recipe.result())
                    .group(recipe.group())
                    .unlockedBy("has_" + recipe.resultName(),
                            InventoryChangeTrigger.TriggerInstance.hasItems(recipe.result()))
                    .save(recipeOutput, recipe.recipeId("_uncraft_"));
        }
    }

    // Storage block recipe (e.g., ingot <-> block)
    public record StorageRecipe(ItemLike uncompressed, String uncompressedName,
                                ItemLike compressed, String compressedName, String group) {
        public String recipeId(String suffix) {
            return VorrikzLearning.MOD_ID + ":" + compressedName + suffix + uncompressedName;
        }
    }

    // Cross-filled block recipe (e.g., magic block crafting)
    public record CrossFilledRecipe(ItemLike result, String resultName,
                                    ItemLike internal, String internalName,
                                    ItemLike external, String externalName,
                                    String group, boolean addUncraft,
                                    ItemLike uncraftResult, int uncraftAmount) {
        public String recipeId(String suffix) {
            return VorrikzLearning.MOD_ID + ":" + resultName + suffix + internalName + "_and_" + externalName;
        }
    }

    private static String getItemName(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }
}
