package net.redreaper.backported_spellbooks.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;

import java.util.function.Supplier;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, BackportedSpellbooks.MOD_ID);

    public static final Supplier<SimpleParticleType> RESIN_PARTICLE = PARTICLE_TYPES.register("resin", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> RESIN_BUBBLE = PARTICLE_TYPES.register("resin_bubble", () -> new SimpleParticleType(false));


    public static void register(IEventBus eventBus)
    {
        PARTICLE_TYPES.register(eventBus);
    }
}
