package net.redreaper.backported_spellbooks.spells.nature;

import com.blackgear.vanillabackport.client.registries.ModParticles;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.entities.spell.pale_thorn.PaleThornVisualEntity;
import net.redreaper.backported_spellbooks.init.ModMobEffects;
import net.redreaper.backported_spellbooks.init.ModSpellSubSchool;
import net.redreaper.backported_spellbooks.spells.AbstractPaleSpells;

import java.util.List;
import java.util.Optional;

public class PaleThornSpell extends AbstractPaleSpells {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "pale_thorn");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 2)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(ModSpellSubSchool.PALE_FLORA_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(10)
            .build();

    public PaleThornSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 25;
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

    @Override public Optional<SoundEvent> getCastStartSound() {return Optional.of(SoundRegistry.ROOT_EMERGE.get());}


    @Override public AnimationHolder getCastFinishAnimation() {
        return ASSpellAnimations.ANIMATION_CLAP;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        var hitResult = Utils.raycastForEntity(level, entity, getRange(spellLevel, entity), false, .15f);
        level.addFreshEntity(new PaleThornVisualEntity(level, entity.getEyePosition(), hitResult.getLocation(), entity));
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            int i = getDuration(spellLevel, entity);
            Entity target = ((EntityHitResult) hitResult).getEntity();
            DamageSources.applyDamage(target, getDamage(spellLevel, entity), getDamageSource(entity));
            if (target instanceof  LivingEntity livingEntity)
                livingEntity.addEffect(new MobEffectInstance(ModMobEffects.PARANOIA,i,1, false, true, true));
            MagicManager.spawnParticles(level, ModParticles.PALE_OAK_LEAVES.get(), hitResult.getLocation().x, target.getY(), hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
        } else if (hitResult.getType() == HitResult.Type.BLOCK) {
            MagicManager.spawnParticles(level, ModParticles.PALE_OAK_LEAVES.get(), hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 4, 0, 0, 0, .3, true);
        }
        MagicManager.spawnParticles(level, ModParticles.PALE_OAK_LEAVES.get(), hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 50, 0, 0, 0, .3, false);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 100);
    }

    public static float getRange(int level, LivingEntity caster) {
        return 15;
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        if (caster == null) {
            return this.getSpellPower(spellLevel, (Entity)null) * 1.5f;
        } else {
            double firePower = caster.getAttributeValue(AttributeRegistry.NATURE_SPELL_POWER);
            double bloodPower = caster.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER);
            return (float)((double)5.0F + (double).4F * (double)this.getSpellPower(spellLevel, caster) * ((double)0.5F * firePower + (double)0.5F * bloodPower));
        }
    }
}
