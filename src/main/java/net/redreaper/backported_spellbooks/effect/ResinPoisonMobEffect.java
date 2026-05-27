package net.redreaper.backported_spellbooks.effect;

import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForgeMod;

public class ResinPoisonMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float SLOWNESS_PER_LEVEL = -.25f;
    public static final float ATTACK_SLOWNESS_PER_LEVEL = -.25f;
    public static final float BLINDNESS_PER_LEVEL = -.90f;

    public ResinPoisonMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
}
