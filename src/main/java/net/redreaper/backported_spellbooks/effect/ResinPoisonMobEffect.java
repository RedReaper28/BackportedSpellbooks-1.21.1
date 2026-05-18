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

    public ResinPoisonMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public boolean applyEffectTick(LivingEntity p_296276_, int p_296233_) {
        Registry<DamageType> dTypeReg = p_296276_.damageSources().damageTypes;
        Holder.Reference<DamageType> dType = dTypeReg.getHolder(NeoForgeMod.POISON_DAMAGE).orElse(dTypeReg.getHolderOrThrow(ISSDamageTypes.POISON_CLOUD));
        p_296276_.hurt(new DamageSource(dType), 1);
        return true;
    }

    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
        int i = 25 >> p_294232_;
        return i > 0 ? p_295368_ % i == 0 : true;
    }
}
