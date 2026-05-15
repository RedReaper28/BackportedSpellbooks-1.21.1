package net.redreaper.backported_spellbooks.init;

import com.blackgear.vanillabackport.common.registries.ModItems;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModExtendedArmorMaterials {
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, BackportedSpellbooks.MOD_ID);



    public static DeferredHolder<ArmorMaterial,ArmorMaterial> PALE_OBSERVER=register("pale_observer_armor",
            schoolHybridArmorMap(),
            20,
            SoundRegistry.OAKSKIN_CAST,
            () -> Ingredient.of(ModItems.RESIN_BRICK.get()),
            0,
            0);


    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            Supplier<Ingredient> repairIngredient,
            float toughness,
            float knockbackResistance
    )

    {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(BackportedSpellbooks.id(name)));
        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(defense, enchantmentValue, equipSound, repairIngredient, list, toughness, knockbackResistance));
    }

    public static EnumMap<ArmorItem.Type, Integer> makeArmorMap(int helmet, int chestplate, int leggings, int boots)
    {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
            typeIntegerEnumMap.put(ArmorItem.Type.HELMET, helmet);
            typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, chestplate);
            typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, leggings);
            typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, boots);
        });
    }
    static public EnumMap<ArmorItem.Type, Integer> spellcounterArmorMap() {
        return makeArmorMap(7, 11, 10, 6);
    }


    static public EnumMap<ArmorItem.Type, Integer> schoolArmorMap() {
        return makeArmorMap(3, 8, 6, 3);
    }

    static public EnumMap<ArmorItem.Type, Integer> schoolHybridArmorMap() {
        return makeArmorMap(5, 10, 9, 6);
    }

    static public EnumMap<ArmorItem.Type, Integer> schoolUpgradedArmorMap() {
        return makeArmorMap(5, 10, 9, 6);
    }

    public static void register(IEventBus eventBus)
    {
        ARMOR_MATERIALS.register(eventBus);
    }
}
