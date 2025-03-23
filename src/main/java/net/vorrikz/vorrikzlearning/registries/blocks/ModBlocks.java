package net.vorrikz.vorrikzlearning.registries.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.vorrikz.vorrikzlearning.registries.custom.blocks.MagicBlock;
import net.vorrikz.vorrikzlearning.registries.util.Registries;

public class ModBlocks {
    public static final DeferredBlock<Block> BISMUTH_BLOCK = Registries.Blocks.common_block("bismuth", 4f);
    public static final DeferredBlock<Block> BISMUTH_ORE = Registries.Blocks.ore_block("bismuth", 2, 4);
    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE = Registries.Blocks.ore_block("bismuth_deepslate", 3, 6);
    public static final DeferredBlock<Block> MAGIC_BLOCK = Registries.Blocks.custom_block("magic_block", () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static void register(IEventBus eventBus) { Registries.Blocks.register(eventBus); }
}