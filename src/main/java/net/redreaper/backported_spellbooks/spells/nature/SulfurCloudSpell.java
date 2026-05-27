package net.redreaper.backported_spellbooks.spells.nature;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.entities.spell.sulfur_clouds.SulfurCloudProjectile;

import java.util.List;
import java.util.Optional;

public class SulfurCloudSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "sulfur_clouds");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.projectile_count", getCount(spellLevel)));

    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(16)
            .build();

    public SulfurCloudSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 1;
        this.castTime = 45;
        this.baseManaCost = 50;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
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
        return Optional.of(SoundRegistry.GUST_CHARGE.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.GUST_CAST.get());
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!world.isClientSide) {
            Vec3 look = entity.getLookAngle();
                int[] angles2 = new int[]{0, -15, 15};
                for (int angle : angles2) {
                    SulfurCloudProjectile dagger = new SulfurCloudProjectile(world, entity);
                    dagger.setOwner(entity);
                    dagger.setDamage(this.getDamage(spellLevel, entity));
                    dagger.setPos(entity.position().add((double) 0.0F, (double) entity.getEyeHeight() - (double) dagger.getBbHeight() * (double) 0.5F, (double) 0.0F));
                    Vec3 dir = look.yRot((float) Math.toRadians((double) angle));
                    dagger.shoot(dir.x, dir.y, dir.z, 1.25F, 0.0F);
                    world.addFreshEntity(dagger);
                }
            }

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }

    private int getCount(int spellLevel) {
        return 3;
    }


    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.CHARGE_SPIT_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
            return SpellAnimations.SPIT_FINISH_ANIMATION;
    }
}
