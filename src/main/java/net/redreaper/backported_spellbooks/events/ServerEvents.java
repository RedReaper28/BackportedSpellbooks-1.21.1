package net.redreaper.backported_spellbooks.events;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.redreaper.backported_spellbooks.init.ModItems;
import net.redreaper.backported_spellbooks.init.ModMobEffects;
import net.redreaper.backported_spellbooks.item.weapons.GardenRapierItem;

@EventBusSubscriber

public class ServerEvents {

    @SubscribeEvent
    public static void livingDamageEventPost(LivingDamageEvent.Post event) {
        var sourceEntity = event.getSource().getEntity();
        var target = event.getEntity();
        var projectile = event.getSource().getDirectEntity();

        if (sourceEntity != null) {
            if (sourceEntity instanceof Player player) {
            }
        }

        if (sourceEntity instanceof LivingEntity livingEntity)
        {
            ItemStack mainhandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();

            if (mainhandItem.getItem() instanceof GardenRapierItem && (!(livingEntity instanceof Player player) || !player.getCooldowns().isOnCooldown(ModItems.GARDEN_RAPIER.get())))
            {
                // GardenRapier
                {
                    MagicManager.spawnParticles(target.level(), new BlastwaveParticleOptions(SchoolRegistry.NATURE.get().getTargetingColor(), 1.5f), target.getX(), target.getY() + 0.165F, target.getZ(), 1, 0, 0, 0, 0, true);
                    if (target instanceof LivingEntity livingTarget)
                    {
                        livingTarget.addEffect(new MobEffectInstance(ModMobEffects.RESIN_POISON, 10*20, 0, true, true, true));
                    }

                    if (livingEntity instanceof Player player)
                    {
                        player.getCooldowns().addCooldown(ModItems.GARDEN_RAPIER.get(), GardenRapierItem.COOLDOWN);
                    }
                }
            }
        }
    }
}
