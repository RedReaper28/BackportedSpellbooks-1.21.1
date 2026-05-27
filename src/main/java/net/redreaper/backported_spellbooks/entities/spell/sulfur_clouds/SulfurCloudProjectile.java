package net.redreaper.backported_spellbooks.entities.spell.sulfur_clouds;

import com.blackgear.vanillabackport.client.registries.ModParticles;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.redreaper.backported_spellbooks.init.ModEntities;
import net.redreaper.backported_spellbooks.init.ModMobEffects;
import net.redreaper.backported_spellbooks.init.ModSpellRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SulfurCloudProjectile extends AbstractMagicProjectile {
    public static final int lifetime = 100;
    private final List<Entity> victims = new ArrayList<>();
    private int hitsPerTick;

    public SulfurCloudProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.setPierceLevel(-1);
    }

    public SulfurCloudProjectile(Level levelIn, LivingEntity shooter) {
        this(ModEntities.SULFUR_CLOUD.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    public void trailParticles() {
        Vec3 vec3 = this.position().subtract(getDeltaMovement());
        level().addParticle(ModParticles.NOXIOUS_GAS.get(), vec3.x, vec3.y, vec3.z, 0, 0, 0);
    }

    @Override
    public void impactParticles(double x, double y, double z) {

    }

    @Override public float getSpeed() {
        return 0.5f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }


    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {

    }

    @Override
    public void tick() {
        super.tick();
        super.tick();
        this.createParticleSphere();
        if (tickCount > lifetime) {
            discard();
            if (!level().isClientSide) {
                impactParticles(getX(), this.getBoundingBox().getCenter().y, getZ());
            }
        }
        hitsPerTick = 0;
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (!victims.contains(entity)) {
            DamageSources.applyDamage(entity, damage, ModSpellRegistry.SULFUR_CLOUD.get().getDamageSource(this, getOwner()));
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(ModMobEffects.SULFURIC_POISON, 5 * 20, 1));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 5 * 20, 1));
            }

            victims.add(entity);
        }
        if (getPierceLevel() != 0) {
            if (hitsPerTick++ < 5) {
                HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
                if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
                    onHit(hitresult);
                }
            }
            pierceOrDiscard();
        } else {
            discard();
        }
    }

    BlockPos lastHitBlock;

    @Override
    protected void onHit(HitResult result) {
        if (!level().isClientSide) {
            var blockPos = BlockPos.containing(result.getLocation());
            if (result.getType() == HitResult.Type.BLOCK && !blockPos.equals(lastHitBlock)) {
                lastHitBlock = blockPos;
            } else if (result.getType() == HitResult.Type.ENTITY) {
                level().playSound(null, BlockPos.containing(position()), SoundRegistry.ACID_ORB_IMPACT.get(), SoundSource.NEUTRAL, 2, .65f);
            }
        }
        super.onHit(result);
    }

    private void createParticleSphere() {
        double radius = (double)(this.getBbWidth());
        int particleCount = 15;

        for(int i = 0; i < particleCount; ++i) {
            double theta = Math.toRadians(this.level().random.nextDouble() * (double)360.0F);
            double phi = Math.toRadians(this.level().random.nextDouble() * (double)180.0F);
            double randomRadius = radius * Math.pow(this.level().random.nextDouble(), 0.3333333333333333);
            double xOffset = randomRadius * Math.sin(phi) * Math.cos(theta);
            double yOffset = randomRadius * Math.cos(phi);
            double zOffset = randomRadius * Math.sin(phi) * Math.sin(theta);
            this.level().addParticle(ModParticles.NOXIOUS_GAS.get(), this.getX() + xOffset, this.getY() + yOffset, this.getZ() + zOffset, (double)0.0F, (double)0.0F, (double)0.0F);
        }
    }

    @Override
    protected boolean shouldPierceShields() {
        return true;
    }
}
