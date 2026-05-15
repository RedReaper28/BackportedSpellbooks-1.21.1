package net.redreaper.backported_spellbooks.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.entities.spell.pale_thorn.PaleThornVisualEntityRenderer;
import net.redreaper.backported_spellbooks.init.ModEntities;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = BackportedSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PALE_THORN_VISUAL_ENTITY.get(), PaleThornVisualEntityRenderer::new);



    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PaleThornVisualEntityRenderer.MODEL_LAYER_LOCATION, PaleThornVisualEntityRenderer::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event)
    {

    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {

    }
}
