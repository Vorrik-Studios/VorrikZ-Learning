package net.vorrikz.vorrikzlearning.datagen;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.vorrikz.vorrikzlearning.VorrikZLearning;
import net.vorrikz.vorrikzlearning.block.ModBlocks;
import net.vorrikz.vorrikzlearning.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
        System.out.println("ModRecipeProvider registered!");
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        System.out.println("Generating Recipes..."); // Debug message
        List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH,
                ModBlocks.BISMUTH_ORE, ModBlocks.BISMUTH_DEEPSLATE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BISMUTH_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BISMUTH.get())
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 9)
                .requires(ModBlocks.BISMUTH_BLOCK)
                .unlockedBy("has_bismuth_block", has(ModBlocks.BISMUTH_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 18)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, "vorrikzlearning:bismuth_from_magic_block");

        oreSmelting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 200, "bismuth");
        oreBlasting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 100, "bismuth");

        stairBuilder(ModBlocks.BISMUTH_STAIRS.get(), Ingredient.of(ModItems.BISMUTH)).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_SLAB.get(), ModItems.BISMUTH.get());

        buttonBuilder(ModBlocks.BISMUTH_BUTTON.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.BISMUTH_PRESSURE_PLATE.get(), ModItems.BISMUTH.get());

        fenceBuilder(ModBlocks.BISMUTH_FENCE.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.BISMUTH_FENCE_GATE.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_WALL.get(), ModItems.BISMUTH.get());

        doorBuilder(ModBlocks.BISMUTH_DOOR.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.BISMUTH_TRAPDOOR.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);

        Armor.set(recipeOutput, ModItems.BISMUTH_HELMET.get(), ModItems.BISMUTH_CHESTPLATE.get(),
                ModItems.BISMUTH_LEGGINGS.get(), ModItems.BISMUTH_BOOTS.get(), ModItems.BISMUTH.get(), "bismuth");

        Tools.set(recipeOutput, ModItems.BISMUTH_SWORD.get(), ModItems.BISMUTH_PICKAXE.get(), ModItems.BISMUTH_AXE.get(), ModItems.BISMUTH_SHOVEL.get(), ModItems.BISMUTH_HOE.get(), ModItems.BISMUTH.get(), "bismuth");
        Tools.hammer(recipeOutput, ModItems.BISMUTH_HAMMER.get(), ModBlocks.BISMUTH_BLOCK.get(), ModItems.BISMUTH.get(), "bismuth");
    }

    protected static class Armor {
        protected static void helmet(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).pattern("###").pattern("# #").define('#', material).group(group)
                    .unlockedBy(getHasName(material), has(material)).save(output);
        }
        protected static void chestplate(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).pattern("# #").pattern("###").pattern("###").define('#', material).group(group)
                    .unlockedBy(getHasName(material), has(material)).save(output);
        }
        protected static void leggings(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).pattern("###").pattern("# #").pattern("# #").define('#', material).group(group)
                    .unlockedBy(getHasName(material), has(material)).save(output);
        }
        protected static void boots(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).pattern("# #").pattern("# #").define('#', material).group(group)
                    .unlockedBy(getHasName(material), has(material)).save(output);
        }
        protected static void set(RecipeOutput output, ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots, ItemLike material, String group) {
            helmet(output, helmet, material, group);
            chestplate(output, chestplate, material, group);
            leggings(output, leggings, material, group);
            boots(output, boots, material, group);
        }
    }

    protected static class Tools {
        protected static void sword(RecipeOutput output, ItemLike result, ItemLike material, ItemLike handle, String group) {
           ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).pattern(" # ").pattern(" # ").pattern(" | ")
                   .define('#', material).define('|', handle).group(group).unlockedBy(getHasName(material), has(material))
                   .unlockedBy(getHasName(handle), has(handle)).save(output);
        }
        protected static void sword(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            sword(output, result, material, Items.BLAZE_ROD, group);
        }

        protected static void pickaxe(RecipeOutput output, ItemLike result, ItemLike material, ItemLike handle, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern("###").pattern(" | ").pattern(" | ")
                    .define('#', material).define('|', handle).group(group).unlockedBy(getHasName(material), has(material))
                    .unlockedBy(getHasName(handle), has(handle)).save(output);
        }
        protected static void pickaxe(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            pickaxe(output, result, material, Items.BLAZE_ROD, group);
        }

        protected static void axe(RecipeOutput output, ItemLike result, ItemLike material, ItemLike handle, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern("## ").pattern("#| ").pattern(" | ")
                    .define('#', material).define('|', handle).group(group).unlockedBy(getHasName(material), has(material))
                    .unlockedBy(getHasName(handle), has(handle)).save(output);
        }
        protected static void axe(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            axe(output, result, material, Items.BLAZE_ROD, group);
        }

        protected static void shovel(RecipeOutput output, ItemLike result, ItemLike material, ItemLike handle, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern(" # ").pattern(" | ").pattern(" | ")
                    .define('#', material).define('|', handle).group(group).unlockedBy(getHasName(material), has(material))
                    .unlockedBy(getHasName(handle), has(handle)).save(output);
        }
        protected static void shovel(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            shovel(output, result, material, Items.BLAZE_ROD, group);
        }

        protected static void hoe(RecipeOutput output, ItemLike result, ItemLike material, ItemLike handle, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern("## ").pattern(" | ").pattern(" | ")
                    .define('#', material).define('|', handle).group(group).unlockedBy(getHasName(material), has(material))
                    .unlockedBy(getHasName(handle), has(handle)).save(output);
        }
        protected static void hoe(RecipeOutput output, ItemLike result, ItemLike material, String group) {
            hoe(output, result, material, Items.BLAZE_ROD, group);
        }

        protected static void hammer(RecipeOutput output, ItemLike result, ItemLike hardMaterial, ItemLike softMaterial, ItemLike handle, String group) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern("#*#").pattern("#*#").pattern(" | ")
                    .define('#', hardMaterial).define('*', softMaterial).unlockedBy(getHasName(hardMaterial), has(hardMaterial))
                    .unlockedBy(getHasName(softMaterial), has(softMaterial)).unlockedBy(getHasName(handle), has(handle)).define('|', handle)
                    .group(group).save(output);
        }
        protected static void hammer(RecipeOutput output, ItemLike result, ItemLike hardMaterial, ItemLike softMaterial, String group) {
            hammer(output, result, hardMaterial, softMaterial, Items.BLAZE_ROD, group);
        }

        protected static void set(RecipeOutput output, ItemLike sword, ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike hoe, ItemLike material, ItemLike handle, String group) {
            sword(output, sword, material, handle, group);
            pickaxe(output, pickaxe, material, handle, group);
            shovel(output, shovel, material, handle, group);
            hoe(output, hoe, material, handle, group);
            axe(output, axe, material, handle, group);
        }
        protected static void set(RecipeOutput output, ItemLike sword, ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike hoe, ItemLike material, String group) {
            sword(output, sword, material, group);
            pickaxe(output, pickaxe, material, group);
            shovel(output, shovel, material, group);
            hoe(output, hoe, material, group);
            axe(output, axe, material, group);
        }
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, VorrikZLearning.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
