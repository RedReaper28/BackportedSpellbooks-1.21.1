package net.redreaper.backported_spellbooks.spells.nature;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.effect.SlimeAspectMobEffect;
import net.redreaper.backported_spellbooks.init.ModMobEffects;

import java.util.List;
import java.util.Optional;

public class AspectOfTheSlimeSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "slime_aspect");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("attribute.modifier.plus.0", Utils.stringTruncation(getPercentAttackDamage(spellLevel, caster), 0), Component.translatable("attribute.name.bounciness")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentSpeed(spellLevel, caster), 0), Component.translatable("attribute.name.generic.jump_strength")),
                Component.translatable("attribute.modifier.take.1", Utils.stringTruncation(getPercentSpellPower(spellLevel, caster), 0), Component.translatable("attribute.name.air_drag_modifier")),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 20, 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(90)
            .build();

    public AspectOfTheSlimeSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 20;
        this.spellPowerPerLevel = 5;
        this.castTime = 0;
        this.baseManaCost = 35;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {return Optional.of(SoundEvents.SLIME_JUMP);}

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(ModMobEffects.SLIME_ASPECT, (int) (getSpellPower(spellLevel, entity) * 20), spellLevel - 1, false, false, true));
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getPercentAttackDamage(int spellLevel, LivingEntity entity) {
        return spellLevel * SlimeAspectMobEffect.BOUNCE_PER_LEVEL;
    }

    private float getPercentSpeed(int spellLevel, LivingEntity entity) {
        return spellLevel * SlimeAspectMobEffect.JUMP_PER_LEVEL * 100;
    }

    private float getPercentSpellPower(int spellLevel, LivingEntity entity) {
        return spellLevel * SlimeAspectMobEffect.AIR_DRAG_PER_LEVEL * -100;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SELF_CAST_ANIMATION;
    }
}
