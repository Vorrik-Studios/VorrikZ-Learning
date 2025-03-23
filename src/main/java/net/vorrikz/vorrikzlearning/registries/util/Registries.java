package net.vorrikz.vorrikzlearning.registries.util;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.vorrikz.vorrikzlearning.VorrikzLearning;
import net.vorrikz.vorrikzlearning.registries.custom.items.FuelItem;
import net.vorrikz.vorrikzlearning.registries.util.attributes.*;

import java.util.List;
import java.util.function.Supplier;

public class Registries {
    public static class Items {
        private static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(VorrikzLearning.MOD_ID);

        public static DeferredRegister.Items get_registry() {
            return REGISTER;
        }
        public static DeferredItem<Item> common_item(String name) {
            return REGISTER.register(name, () -> new Item(new Item.Properties()));
        }
        public static DeferredItem<Item> fuel_item(String name, int burnTime) {
            return REGISTER.register(name, () -> new FuelItem(new Item.Properties(), burnTime));
        }
        public static DeferredItem<Item> food_item(String name, FoodProperties foodProperties, Component tooltip) {
            return REGISTER.register(name, () -> new Item(new Item.Properties().food(foodProperties)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(tooltip);
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
        }
        public static DeferredItem<Item> food_item(String name, FoodProperties foodProperties) {
            return REGISTER.register(name, () -> new Item(new Item.Properties().food(foodProperties)));
        }
        public static <T extends Item> DeferredItem<T> custom_item(String name, Supplier<T> item) {
            return REGISTER.register(name, item);
        }

        public static void register(IEventBus eventBus) {
            REGISTER.register(eventBus);
        }
    }

    public static class Blocks {
        private static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(VorrikzLearning.MOD_ID);
        public static DeferredRegister.Blocks get_registry() {
            return REGISTER;
        }
        public static DeferredBlock<Block> common_block(String name, float strength, SoundType sound) {
            return registerBlock(get_name(name, ENameSuffixes.BLOCK), () -> new Block(BlockBehaviour.Properties.of().strength(strength).requiresCorrectToolForDrops().sound(sound)));
        }
        public static DeferredBlock<Block> common_block(String name, float strength) {
            return registerBlock(get_name(name, ENameSuffixes.BLOCK), () -> new Block(BlockBehaviour.Properties.of().strength(strength).requiresCorrectToolForDrops()));
        }
        public static DeferredBlock<Block> common_block(String name) {
            return registerBlock(get_name(name, ENameSuffixes.BLOCK), () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
        }
        public static DeferredBlock<Block> common_block_no_drops(String name, float strength, SoundType sound) {
            return registerBlock(get_name(name, ENameSuffixes.BLOCK), () -> new Block(BlockBehaviour.Properties.of().strength(strength).noLootTable().sound(sound)));
        }
        public static DeferredBlock<Block> common_block_no_drops(String name, float strength) {
            return registerBlock(get_name(name, ENameSuffixes.BLOCK), () -> new Block(BlockBehaviour.Properties.of().noLootTable().strength(strength)));
        }
        public static DeferredBlock<Block> common_block_no_drops(String name) {
            return registerBlock(get_name(name, ENameSuffixes.BLOCK), () -> new Block(BlockBehaviour.Properties.of().noLootTable().strength(3f)));
        }
        public static DeferredBlock<Block> ore_block(String name, int minInclusive, int maxInclusive, float strength, SoundType sound) {
            return registerBlock(get_name(name, ENameSuffixes.ORE), () -> new DropExperienceBlock(UniformInt.of(minInclusive, maxInclusive), BlockBehaviour.Properties.of().strength(strength).requiresCorrectToolForDrops().sound(sound)));
        }
        public static DeferredBlock<Block> ore_block(String name, int minInclusive, int maxInclusive, float strength) {
            return registerBlock(get_name(name, ENameSuffixes.ORE), () -> new DropExperienceBlock(UniformInt.of(minInclusive, maxInclusive), BlockBehaviour.Properties.of().strength(strength).requiresCorrectToolForDrops()));
        }
        public static DeferredBlock<Block> ore_block(String name, int minInclusive, int maxInclusive) {
            return registerBlock(get_name(name, ENameSuffixes.ORE), () -> new DropExperienceBlock(UniformInt.of(minInclusive, maxInclusive), BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
        }
        public static <T extends Block> DeferredBlock<T> custom_block(String name, Supplier<T> block) {
            return registerBlock(name, block);
        }

        public static class NonBlocks {
            public static DeferredBlock<StairBlock> stairs(String name, BlockState baseState, BlockAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.STAIRS), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops();
                    properties = add_sound(properties, attributes);

                    return new StairBlock(baseState, properties);
                });
            }
            public static DeferredBlock<SlabBlock> slabs(String name, BlockAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.SLAB), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops();
                    properties = add_sound(properties, attributes);

                    return new SlabBlock(properties);
                });
            }
            public static DeferredBlock<PressurePlateBlock> pressure_plate(String name, PressurePlateAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.PRESSURE_PLATE), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops();
                    properties = add_sound(properties, attributes);

                    return new PressurePlateBlock(attributes.getBlockSetType(), properties);
                });
            }
            public static DeferredBlock<ButtonBlock> button(String name, ButtonAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.BUTTON), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops()
                            .noCollission();
                    properties = add_sound(properties, attributes);

                    return new ButtonBlock(attributes.getBlockSetType(), attributes.getTicksToStayPressed(), properties);
                });
            }
            public static DeferredBlock<FenceBlock> fence(String name, BlockAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.FENCE), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops();
                    properties = add_sound(properties, attributes);

                    return new FenceBlock(properties);
                });
            }
            public static DeferredBlock<FenceGateBlock> fence_gate(String name, FenceGateAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.FENCE_GATE), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops();
                    properties = add_sound(properties, attributes);

                    return new FenceGateBlock(attributes.getWoodType(), properties);
                });
            }
            public static DeferredBlock<WallBlock> wall(String name, BlockAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.WALL), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops();
                    properties = add_sound(properties, attributes);

                    return new WallBlock(properties);
                });
            }
            public static DeferredBlock<DoorBlock> door(String name, DoorAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.DOOR), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops()
                            .noOcclusion();
                    properties = add_sound(properties, attributes);

                    return new DoorBlock(attributes.getBlockSetType(), properties);
                });
            }
            public static DeferredBlock<TrapDoorBlock> trapdoor(String name, DoorAttributes attributes) {
                return registerBlock(get_name(name, ENameSuffixes.TRAPDOOR), () -> {
                    BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                            .strength(attributes.getStrength())
                            .requiresCorrectToolForDrops()
                            .noOcclusion();
                    properties = add_sound(properties, attributes);

                    return new TrapDoorBlock(attributes.getBlockSetType(), properties);
                });
            }

            private static BlockBehaviour.Properties add_sound(BlockBehaviour.Properties properties, BlockAttributes attributes) {
                if (attributes.getSound() != null) {
                    return properties.sound(attributes.getSound());
                }
                return properties;
            }
        }

        public static void register(IEventBus eventBus) {
            REGISTER.register(eventBus);
        }

        private enum ENameSuffixes {
            BLOCK("_block"),
            ORE("_ore"),
            STAIRS("_stairs"),
            SLAB("_slab"),
            PRESSURE_PLATE("_pressure_plate"),
            BUTTON("_button"),
            FENCE("_fence"),
            FENCE_GATE("_fence_gate"),
            WALL("_wall"),
            DOOR("_door"),
            TRAPDOOR("_trapdoor");

            private final String suffix;

            ENameSuffixes(String suffix) {
                this.suffix = suffix;
            }

            public String getSuffix() {
                return suffix;
            }
        }

        private static String get_name(String name, ENameSuffixes suffix) {
            return name  + suffix.getSuffix();
        }
        private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
            DeferredBlock<T> toReturn = REGISTER.register(name, block);
            registerBlockItem(name, toReturn);
            return toReturn;
        }
        private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
            Items.get_registry().register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }
    }

    public static class CreativeModeTabs {
        private static final DeferredRegister<net.minecraft.world.item.CreativeModeTab> REGISTER = DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, VorrikzLearning.MOD_ID);
        public static DeferredRegister<net.minecraft.world.item.CreativeModeTab> get_registry() {
            return REGISTER;
        }

        public static Supplier<net.minecraft.world.item.CreativeModeTab> tab(String name, Supplier<? extends net.minecraft.world.item.CreativeModeTab> supplier) {
            return REGISTER.register(name + "_tab", supplier);
        }

        public static void register(IEventBus eventBus) {
            REGISTER.register(eventBus);
        }
    }

    public static class Tags {
        public static class Blocks {

            private static TagKey<Block> createTag(String name) {
                return BlockTags.create(ResourceLocation.fromNamespaceAndPath(VorrikzLearning.MOD_ID, name));
            }
        }

        public static class Items {
            public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

            private static TagKey<Item> createTag(String name) {
                return ItemTags.create(ResourceLocation.fromNamespaceAndPath(VorrikzLearning.MOD_ID, name));
            }
        }
    }
}
