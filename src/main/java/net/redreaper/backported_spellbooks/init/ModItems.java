package net.redreaper.backported_spellbooks.init;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.item.armor.PaleObserverArmorItem;
import net.redreaper.backported_spellbooks.item.curios.spellbooks.PaleGuideSpellbookItem;
import net.redreaper.backported_spellbooks.item.staves.eyebloosom_staff.EyeBloosomStaffItem;
import net.redreaper.backported_spellbooks.item.weapons.GardenRapierItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS= DeferredRegister.createItems(BackportedSpellbooks.MOD_ID);

    public static final DeferredHolder<Item, Item>PALE_GUIDE = ITEMS.register("pale_guide_spell_book",
            PaleGuideSpellbookItem::new);
    public static final DeferredItem<Item> EYEBLOOSOM_STAFF=ITEMS.register("eyebloosom_staff",
            EyeBloosomStaffItem::new);
    public static final DeferredItem<Item> GARDEN_RAPIER=ITEMS.register("garden_rapier",
            GardenRapierItem::new);

    public static final DeferredHolder<Item, Item> PALE_OBSERVER_HELMET = ITEMS.register("pale_observer_helmet", () ->
            new PaleObserverArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredHolder<Item, Item>PALE_OBSERVER_CHESTPLATE = ITEMS.register("pale_observer_chestplate", () ->
            new PaleObserverArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredHolder<Item, Item> PALE_OBSERVER_LEGGINGS = ITEMS.register("pale_observer_leggings", () ->
            new PaleObserverArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredHolder<Item, Item> PALE_OBSERVER_BOOTS = ITEMS.register("pale_observer_boots", () ->
            new PaleObserverArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

