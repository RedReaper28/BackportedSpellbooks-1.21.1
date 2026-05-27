package net.redreaper.backported_spellbooks.events;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASDamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.redreaper.backported_spellbooks.init.ModItems;
import net.redreaper.backported_spellbooks.init.ModMobEffects;
import net.redreaper.backported_spellbooks.item.staves.miasma_staff.MiasmaStaffItem;
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

            if (mainhandItem.getItem() instanceof MiasmaStaffItem && (!(livingEntity instanceof Player player) || !player.getCooldowns().isOnCooldown(ModItems.MIASMA_STAFF.get())))
            {
                // Miasma Staff
                {
                    if (target instanceof LivingEntity livingTarget)
                    {
                        if (event.getSource().is(ISSDamageTypes.NATURE_MAGIC)) {

                            livingTarget.addEffect(new MobEffectInstance(ModMobEffects.SULFURIC_POISON, 5 * 20, 1, true, true, true));
                            if (livingEntity instanceof Player player)
                            {
                                player.getCooldowns().addCooldown(ModItems.MIASMA_STAFF.get(), MiasmaStaffItem.COOLDOWN);
                            }
                        }
                        if (event.getSource().is(ASDamageTypes.HYDRO_MAGIC)) {

                            livingTarget.addEffect(new MobEffectInstance(ModMobEffects.SULFURIC_POISON, 5 * 20, 1, true, true, true));
                            if (livingEntity instanceof Player player)
                            {
                                player.getCooldowns().addCooldown(ModItems.MIASMA_STAFF.get(), MiasmaStaffItem.COOLDOWN);
                            }
                        }
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onFallEvent(LivingFallEvent event)
    {
        var entity = event.getEntity();

        if (entity instanceof Player player)
        {
            if (player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.SLIME_BOOTS))
            {
                event.setCanceled(true);
            }
        }
    }

}
