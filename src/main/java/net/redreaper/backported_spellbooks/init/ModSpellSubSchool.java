package net.redreaper.backported_spellbooks.init;


import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.SCHOOL_REGISTRY_KEY;

public class ModSpellSubSchool {

    private static final DeferredRegister<SchoolType> HNS_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, BackportedSpellbooks.MOD_ID);

    public static void register(IEventBus eventBus)
    {
        HNS_SCHOOLS.register(eventBus);
    }

    private static Supplier<SchoolType> registerSchool(SchoolType type)
    {
        return HNS_SCHOOLS.register(type.getId().getPath(), () -> type);
    }

    public static final ResourceLocation PALE_FLORA_RESOURCE = BackportedSpellbooks.id("pale_flora");
    public static final Supplier<SchoolType> PALE_FLORA = registerSchool(new SchoolType
            (
                    PALE_FLORA_RESOURCE,
                    ModTags.Items.PALE_FOCUS,
                    Component.translatable("school.backportedspellbooks.pale_flora")
                            .withStyle(Style.EMPTY.withColor(5129024)),
                    AttributeRegistry.NATURE_SPELL_POWER,
                    AttributeRegistry.NATURE_SPELL_POWER,
                    SoundRegistry.OAKSKIN_CAST,
                    ISSDamageTypes.NATURE_MAGIC
            ));
}
