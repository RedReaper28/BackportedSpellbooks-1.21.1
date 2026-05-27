package net.redreaper.backported_spellbooks.entities.spell.sulfur_bomb;

import com.blackgear.vanillabackport.client.registries.ModParticles;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.redreaper.backported_spellbooks.init.ModEntities;

import java.util.Optional;

public class SulfurGasCloud extends AoeEntity {
    private DamageSource damageSource;

    public SulfurGasCloud(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public SulfurGasCloud(Level level) {
        this(ModEntities.SULFUR_FIELD.get(), level);
    }


    @Override
    public void applyEffect(LivingEntity target) {
        if (damageSource == null) {
            damageSource = new DamageSource(DamageSources.getHolderFromResource(target, ISSDamageTypes.POISON_CLOUD), this, getOwner());
        }
        DamageSources.ignoreNextKnockback(target);
        target.hurt(damageSource, getDamage());
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, (int) getDamage()));
        target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, (int) getDamage()));
    }

    @Override
    public float getParticleCount() {
        return 1.5f * getRadius();
    }

    @Override
    protected float particleYOffset() {
        return .25f;
    }

    @Override
    protected float getParticleSpeedModifier() {
        return 1.4f;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(ModParticles.NOXIOUS_GAS.get());
    }
}
