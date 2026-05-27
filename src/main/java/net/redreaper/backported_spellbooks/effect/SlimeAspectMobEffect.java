package net.redreaper.backported_spellbooks.effect;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SlimeAspectMobEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float BOUNCE_PER_LEVEL = .5f;
    public static final float JUMP_PER_LEVEL = .15f;
    public static final float AIR_DRAG_PER_LEVEL = -.10f;

    public SlimeAspectMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

}
