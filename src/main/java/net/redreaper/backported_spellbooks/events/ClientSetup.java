package net.redreaper.backported_spellbooks.events;

import io.redspace.ironsspellbooks.fluids.SimpleClientFluidType;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.entities.spell.pale_thorn.PaleThornVisualEntityRenderer;
import net.redreaper.backported_spellbooks.entities.spell.sulfur_bomb.SulfurBombRenderer;
import net.redreaper.backported_spellbooks.init.ModEntities;
import net.redreaper.backported_spellbooks.init.ModFluids;
import net.redreaper.backported_spellbooks.init.ModParticleTypes;
import net.redreaper.backported_spellbooks.particles.ResinBubbleParticle;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = BackportedSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PALE_THORN_VISUAL_ENTITY.get(), PaleThornVisualEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.RESIN_SPRAY_PROJECTILE.get(), NoopRenderer::new);

        event.registerEntityRenderer(ModEntities.SULFUR_FIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.SULFUR_BOMB.get(), SulfurBombRenderer::new);
        event.registerEntityRenderer(ModEntities.SULFUR_CLOUD.get(), NoopRenderer::new);


    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PaleThornVisualEntityRenderer.MODEL_LAYER_LOCATION, PaleThornVisualEntityRenderer::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event)
    {
        event.registerSpriteSet(ModParticleTypes.RESIN_BUBBLE.get(), ResinBubbleParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new SimpleClientFluidType(BackportedSpellbooks.id("block/resin_fluid")), ModFluids.RESIN_TYPE);
    }
}
