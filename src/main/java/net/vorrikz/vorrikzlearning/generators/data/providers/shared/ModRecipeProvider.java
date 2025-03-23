package net.vorrikz.vorrikzlearning.generators.data.providers.shared;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.vorrikz.vorrikzlearning.VorrikzLearning;
import net.vorrikz.vorrikzlearning.registries.blocks.ModBlocks;
import net.vorrikz.vorrikzlearning.registries.items.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH, ModBlocks.BISMUTH_ORE, ModBlocks.BISMUTH_DEEPSLATE_ORE);

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        nine_by_nine_storage(recipeOutput, RecipeCategory.MISC, ModItems.BISMUTH.get(), "bismuth", RecipeCategory.MISC, ModBlocks.BISMUTH_BLOCK.get(), "bismuth_block", "bismuth");
        cross_filled_with_uncraft(recipeOutput, RecipeCategory.MISC, RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK.get(), "magic_block", ModBlocks.BISMUTH_BLOCK.get() ,"bismuth_block", 4, ModItems.BISMUTH.get(), "bismuth", ModBlocks.BISMUTH_BLOCK.get(), "bismuth_block", "bismuth");
        Tools.shovel(recipeOutput, ModItems.CHISEL.get(), "chisel", ModItems.BISMUTH.get(), "bismuth", "bismuth");
        smeltable(recipeOutput, RecipeCategory.MISC, BISMUTH_SMELTABLES, ModItems.BISMUTH.get(), 0.25f, 200, "bismuth");
    }

    private static void nine_by_nine_storage(RecipeOutput recipeOutput, RecipeCategory uncompressedCategory, ItemLike uncompressed, String uncompressedName, RecipeCategory compressedCategory, ItemLike compressed, String compressedName, String group) {
        ShapedRecipeBuilder.shaped(compressedCategory, compressed).pattern("###").pattern("###").pattern("###").define('#', uncompressed).unlockedBy(getHasName(uncompressed), has(uncompressed)).group(group).save(recipeOutput, VorrikzLearning.MOD_ID + ":" + compressedName + "_from_" + uncompressedName);
        ShapelessRecipeBuilder.shapeless(uncompressedCategory, uncompressed, 9).requires(compressed).unlockedBy(getHasName(compressed), has(compressed)).group(group).save(recipeOutput, VorrikzLearning.MOD_ID + ":" + uncompressedName + "_from_" + compressedName);
    }

    private static void cross_filled(RecipeOutput recipeOutput, RecipeCategory recipeCategory, ItemLike result, String resultName, ItemLike internal, String internalName, ItemLike external, String externalName, String group) {
        ShapedRecipeBuilder.shaped(recipeCategory, result).pattern("#@#").pattern("@@@").pattern("#@#").define('#', external).define('@', internal).unlockedBy(getHasName(internal), has(internal)).unlockedBy(getHasName(external), has(external)).group(group).save(recipeOutput, VorrikzLearning.MOD_ID + ":" + resultName + "_from_" + internalName + "_and_" + externalName);
    }
    private static void cross_filled_with_uncraft(RecipeOutput recipeOutput, RecipeCategory compressedRecipeCategory, RecipeCategory uncompressedRecipeCategory, ItemLike compressResult, String compressResultName, ItemLike uncraftResult, String uncraftResultName, int uncraftAmount, ItemLike internal, String internalName, ItemLike external, String externalName, String group) {
        cross_filled(recipeOutput, compressedRecipeCategory, compressResult, compressResultName, internal, internalName, external, externalName, group);
        ShapelessRecipeBuilder.shapeless(uncompressedRecipeCategory, uncraftResult, uncraftAmount).requires(compressResult).unlockedBy(getHasName(compressResult), has(compressResult)).group(group).save(recipeOutput, VorrikzLearning.MOD_ID + ":" + uncraftResultName + "_from_" + compressResultName);
    }

    private static class Tools {
        public static void shovel(RecipeOutput recipeOutput, ItemLike result, String resultName, ItemLike material, String materialName, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern(" # ").pattern(" | ").pattern(" | ").define('#', material).define('|', Items.STICK).unlockedBy(getHasName(material), has(material)).unlockedBy("has_stick", has(Items.STICK)).group(group).save(recipeOutput, VorrikzLearning.MOD_ID + ":" + resultName + "_from_" + materialName + "_and_stick");
        }
    }

    private static void smeltable(RecipeOutput recipeOutput, RecipeCategory recipeCategory, List<ItemLike> ingredients, ItemLike result, float experience, int cookingTime, String group) {
        int blastTime = cookingTime / 2;
        oreSmelting(recipeOutput, ingredients, recipeCategory,  result, experience, cookingTime, group);
        oreBlasting(recipeOutput, ingredients, recipeCategory,  result, experience, blastTime, group);
    }
    private static void smeltable(RecipeOutput recipeOutput, RecipeCategory recipeCategory, List<ItemLike> ingredients, ItemLike result, float experience, int smeltTime, int blastTime, String group) {
        oreSmelting(recipeOutput, ingredients, recipeCategory,  result, experience, smeltTime, group);
        oreBlasting(recipeOutput, ingredients, recipeCategory,  result, experience, blastTime, group);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, VorrikzLearning.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
