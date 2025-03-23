package net.vorrikz.vorrikzlearning.generators.data.providers.shared;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.vorrikz.vorrikzlearning.generators.data.providers.shared.recipes.Blocks;
import net.vorrikz.vorrikzlearning.generators.data.providers.shared.recipes.Cooking;
import net.vorrikz.vorrikzlearning.generators.data.providers.shared.recipes.Tools;
import net.vorrikz.vorrikzlearning.registries.blocks.ModBlocks;
import net.vorrikz.vorrikzlearning.registries.items.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> BISMUTH_SMELTABLES = List.of(
            ModItems.RAW_BISMUTH.get(), ModBlocks.BISMUTH_ORE.get(), ModBlocks.BISMUTH_DEEPSLATE_ORE.get()
    );

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Storage block recipes (Bismuth ingot <-> block)
        Blocks.registerStorageRecipe(recipeOutput, RecipeCategory.MISC, RecipeCategory.MISC,
                new Blocks.StorageRecipe(ModItems.BISMUTH.get(), "bismuth",
                        ModBlocks.BISMUTH_BLOCK.get(), "bismuth_block", "bismuth"));

        // Magic block recipe with optional uncrafting
        Blocks.registerCrossFilledRecipe(recipeOutput, RecipeCategory.MISC,
                new Blocks.CrossFilledRecipe(ModBlocks.MAGIC_BLOCK.get(), "magic_block",
                        ModItems.BISMUTH.get(), "bismuth",
                        ModBlocks.BISMUTH_BLOCK.get(), "bismuth_block",
                        "bismuth", true, ModBlocks.BISMUTH_BLOCK.get(), 4));

        // Tool crafting recipes (Example: Chisel)
        Tools.registerToolRecipe(recipeOutput, Tools.ToolType.SHOVEL,
                ModItems.CHISEL.get(), "chisel",
                ModItems.BISMUTH.get(), "bismuth", "bismuth");

        // Smelting & Blasting Recipes for Bismuth
        Cooking.registerSmeltingRecipes(recipeOutput, RecipeCategory.MISC,
                new Cooking.SmeltingRecipeData(BISMUTH_SMELTABLES, ModItems.BISMUTH.get(), 0.25f, 200, 100, "bismuth"));
    }
}
