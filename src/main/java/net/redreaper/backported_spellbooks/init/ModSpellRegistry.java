package net.redreaper.backported_spellbooks.init;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.spells.nature.*;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ModSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, BackportedSpellbooks.MOD_ID);


    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    //NATURE
     public static final Supplier<AbstractSpell> SLIME_ASPECT = registerSpell(new AspectOfTheSlimeSpell());
     public static final Supplier<AbstractSpell> SULFUR_BOMB = registerSpell(new SulfurBombSPell());
     public static final Supplier<AbstractSpell> SULFUR_CLOUD = registerSpell(new SulfurCloudSpell());
     public static final Supplier<AbstractSpell> SULFUR_RELEASE = registerSpell(new SulfurReleaseSpell());
     public static final Supplier<AbstractSpell> PALE_THORN = registerSpell(new PaleThornSpell());
     public static final Supplier<AbstractSpell> RESIN_SPRAY = registerSpell(new ResinSpraySpell());

    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
