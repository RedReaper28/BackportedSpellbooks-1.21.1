package net.redreaper.backported_spellbooks.init;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.gui.overlays.ModScreenEffectsOverlay;

@EventBusSubscriber(modid = BackportedSpellbooks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModOverlays {
    @SubscribeEvent
    public static void onRegisterOverlays(RegisterGuiLayersEvent event) {


        event.registerAboveAll(BackportedSpellbooks.id("screen_effects"), ModScreenEffectsOverlay.instance);
    }
}
