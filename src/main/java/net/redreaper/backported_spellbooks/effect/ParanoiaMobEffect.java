package net.redreaper.backported_spellbooks.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ParanoiaMobEffect extends MagicMobEffect {
    public static final float MANA_REDUCTION_PER_LEVEL = -0.10f;
    public static final float CAST_REDUCTION_PER_LEVEL = -0.10f;

    public ParanoiaMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
}
