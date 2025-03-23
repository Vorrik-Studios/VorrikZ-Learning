package net.vorrikz.vorrikzlearning.generators.data.providers.shared.recipes;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.vorrikz.vorrikzlearning.VorrikzLearning;

import java.util.List;

public class Cooking {

    public static void registerSmeltingRecipes(RecipeOutput recipeOutput, RecipeCategory category, SmeltingRecipeData data) {
        registerCookingRecipe(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, data, category, "smelting");
        registerCookingRecipe(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, data, category, "blasting");
    }

    private static <T extends AbstractCookingRecipe> void registerCookingRecipe(
            RecipeOutput recipeOutput, RecipeSerializer<T> serializer,
            AbstractCookingRecipe.Factory<T> factory, SmeltingRecipeData data,
            RecipeCategory category, String method) {

        for (ItemLike ingredient : data.ingredients()) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(ingredient), category, data.result(),
                            data.experience(), data.getCookTime(method), serializer, factory)
                    .group(data.group())
                    .unlockedBy("has_" + getItemName(ingredient),
                            InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                    .save(recipeOutput, data.recipeId(method, ingredient));
        }
    }

    public record SmeltingRecipeData(List<ItemLike> ingredients, ItemLike result, float experience,
                                     int smeltTime, int blastTime, String group) {

        public int getCookTime(String method) {
            return method.equals("blasting") ? blastTime : smeltTime;
        }

        public String recipeId(String method, ItemLike ingredient) {
            return VorrikzLearning.MOD_ID + ":" + getItemName(result) + "_from_" + method + "_" + getItemName(ingredient);
        }
    }

    private static String getItemName(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }
}
