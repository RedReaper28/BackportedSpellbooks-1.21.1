package net.redreaper.backported_spellbooks.init;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.effect.ParanoiaMobEffect;
import net.redreaper.backported_spellbooks.effect.ResinPoisonMobEffect;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect>MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, BackportedSpellbooks.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> PARANOIA = MOB_EFFECT_DEFERRED_REGISTER.register("paranoia", () -> new ParanoiaMobEffect(MobEffectCategory.HARMFUL, 10892825)
            .addAttributeModifier(AttributeRegistry.MAX_MANA, BackportedSpellbooks.id("effect_paranoia"), ParanoiaMobEffect.MANA_REDUCTION_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(AttributeRegistry.CAST_TIME_REDUCTION, BackportedSpellbooks.id("effect_paranoia"), ParanoiaMobEffect.CAST_REDUCTION_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> RESIN_POISON = MOB_EFFECT_DEFERRED_REGISTER.register("resin_poison", () -> new ResinPoisonMobEffect(MobEffectCategory.HARMFUL, 15495700)
            .addAttributeModifier(Attributes.ATTACK_SPEED, BackportedSpellbooks.id("effect_resin_poison"), ResinPoisonMobEffect.ATTACK_SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, BackportedSpellbooks.id("effect_resin_poison"), ResinPoisonMobEffect.SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }
}
