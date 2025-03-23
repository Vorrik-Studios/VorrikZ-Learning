package net.vorrikz.vorrikzlearning.registries.items;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.vorrikz.vorrikzlearning.registries.custom.items.ChiselItem;
import net.vorrikz.vorrikzlearning.registries.util.Registries;
import net.vorrikz.vorrikzlearning.registries.util.Translatables;

public class ModItems {
    public static final DeferredItem<Item> BISMUTH = Registries.Items.common_item("bismuth");
    public static final DeferredItem<Item> RAW_BISMUTH = Registries.Items.common_item("raw_bismuth");
    public static final DeferredItem<Item> CHISEL = Registries.Items.custom_item("chisel", () -> new ChiselItem(new Item.Properties().durability(32)));
    public static final DeferredItem<Item> FROSTFIRE_ICE = Registries.Items.fuel_item("frostfire_ice", 800);
    public static final DeferredItem<Item> STARLIGHT_ASHES = Registries.Items.fuel_item("starlight_ashes", 1200);
    public static final DeferredItem<Item> RADISH = Registries.Items.food_item("radish", ModFoodProperties.RADISH, Translatables.tooltip("radish"));

    public static void register(IEventBus eventBus) {
        Registries.Items.register(eventBus);
    }
}
