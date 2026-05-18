package net.redreaper.backported_spellbooks.init;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.spells.fire_breath.FireBreathProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.entities.spell.pale_thorn.PaleThornVisualEntity;
import net.redreaper.backported_spellbooks.entities.spell.resin_spray.ResinSprayProjectile;

import static net.minecraft.core.registries.Registries.ENTITY_TYPE;

public class ModEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ENTITY_TYPE, BackportedSpellbooks.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<PaleThornVisualEntity>> PALE_THORN_VISUAL_ENTITY=
            ENTITIES.register("pale_thorn", () -> EntityType.Builder.<PaleThornVisualEntity>of(PaleThornVisualEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "pale_thorn").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ResinSprayProjectile>> RESIN_SPRAY_PROJECTILE =
            ENTITIES.register("resin_spray", () -> EntityType.Builder.<ResinSprayProjectile>of(ResinSprayProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "resin_spray").toString()));
    
    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
