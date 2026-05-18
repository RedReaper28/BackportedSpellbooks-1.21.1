package net.redreaper.backported_spellbooks.entities.spell.resin_spray;

import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.redreaper.backported_spellbooks.init.ModEntities;
import net.redreaper.backported_spellbooks.init.ModMobEffects;
import net.redreaper.backported_spellbooks.init.ModSpellRegistry;
import net.redreaper.backported_spellbooks.particles.ModParticleHelper;

public class ResinSprayProjectile extends AbstractConeProjectile {
    public ResinSprayProjectile(EntityType<? extends AbstractConeProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ResinSprayProjectile(Level level, LivingEntity entity) {
        super(ModEntities.RESIN_SPRAY_PROJECTILE.get(), level, entity);
    }

    @Override
    public void tick() {
        if (!level().isClientSide) {
            if (dealDamageActive) {
                //Set Fire Blocks
            }
        }
        super.tick();
    }


    @Override
    public void spawnParticles() {
        var owner = getOwner();
        if (!level().isClientSide || owner == null) {
            return;
        }
        Vec3 rotation = owner.getLookAngle().normalize();
        var pos = owner.position().add(rotation.scale(1.6));

        double x = pos.x;
        double y = pos.y + owner.getEyeHeight() * .9f;
        double z = pos.z;

        double speed = random.nextDouble() * .35 + .35;
        for (int i = 0; i < 10; i++) {
            double offset = .15;
            double ox = Math.random() * 2 * offset - offset;
            double oy = Math.random() * 2 * offset - offset;
            double oz = Math.random() * 2 * offset - offset;

            double angularness = .5;
            Vec3 randomVec = new Vec3(Math.random() * 2 * angularness - angularness, Math.random() * 2 * angularness - angularness, Math.random() * 2 * angularness - angularness).normalize();
            Vec3 result = (rotation.scale(3).add(randomVec)).normalize().scale(speed);
            level().addParticle(ModParticleHelper.RESIN_BUBBLE, x + ox, y + oy, z + oz, result.x, result.y, result.z);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        //irons_spellbooks.LOGGER.debug("ConeOfColdProjectile.onHitEntity: {}", entityHitResult.getEntity().getName().getString());
        var entity = entityHitResult.getEntity();
        if (DamageSources.applyDamage(entity, damage, ModSpellRegistry.RESIN_SPRAY.get().getDamageSource(this, getOwner())) && entity instanceof LivingEntity livingEntity)
            livingEntity.addEffect(new MobEffectInstance(ModMobEffects.RESIN_POISON, 100, 1));
    }
}

