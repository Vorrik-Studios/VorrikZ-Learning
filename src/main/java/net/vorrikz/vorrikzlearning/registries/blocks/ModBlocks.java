package net.vorrikz.vorrikzlearning.registries.blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.vorrikz.vorrikzlearning.registries.custom.blocks.MagicBlock;
import net.vorrikz.vorrikzlearning.registries.util.Registries;
import net.vorrikz.vorrikzlearning.registries.util.attributes.*;

public class ModBlocks {
    public static final DeferredBlock<Block> BISMUTH_BLOCK = Registries.Blocks.common_block("bismuth", 4f);
    public static final DeferredBlock<Block> BISMUTH_ORE = Registries.Blocks.ore_block("bismuth", 2, 4);
    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE = Registries.Blocks.ore_block("bismuth_deepslate", 3, 6);
    public static final DeferredBlock<Block> MAGIC_BLOCK = Registries.Blocks.custom_block("magic_block", () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> BISMUTH_STAIRS = Registries.Blocks.NonBlocks.stairs("bismuth", ModBlocks.BISMUTH_BLOCK.get().defaultBlockState(), new DefaultAttributes());
    public static final DeferredBlock<SlabBlock> BISMUTH_SLAB = Registries.Blocks.NonBlocks.slabs("bismuth", new DefaultAttributes());
    public static final DeferredBlock<PressurePlateBlock> BISMUTH_PRESSURE_PLATE = Registries.Blocks.NonBlocks.pressure_plate("bismuth", new PressurePlateAttributes());
    public static final DeferredBlock<ButtonBlock> BISMUTH_BUTTON = Registries.Blocks.NonBlocks.button("bismuth", new ButtonAttributes());
    public static final DeferredBlock<FenceBlock> BISMUTH_FENCE = Registries.Blocks.NonBlocks.fence("bismuth", new DefaultAttributes());
    public static final DeferredBlock<FenceGateBlock> BISMUTH_FENCE_GATE = Registries.Blocks.NonBlocks.fence_gate("bismuth", new FenceGateAttributes());
    public static final DeferredBlock<WallBlock> BISMUTH_WALL = Registries.Blocks.NonBlocks.wall("bismuth", new DefaultAttributes());
    public static final DeferredBlock<DoorBlock> BISMUTH_DOOR = Registries.Blocks.NonBlocks.door("bismuth", new DoorAttributes());
    public static final DeferredBlock<TrapDoorBlock> BISMUTH_TRAPDOOR = Registries.Blocks.NonBlocks.trapdoor("bismuth", new DoorAttributes());

    public static void register(IEventBus eventBus) { Registries.Blocks.register(eventBus); }
}