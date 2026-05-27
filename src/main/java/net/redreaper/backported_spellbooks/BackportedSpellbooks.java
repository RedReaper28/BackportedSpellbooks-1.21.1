package net.redreaper.backported_spellbooks;

import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import mod.azure.azurelib.common.animation.cache.AzIdentityRegistry;
import mod.azure.azurelib.common.render.item.AzItemRendererRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.redreaper.backported_spellbooks.entities.weapons.GardenRapierRenderer;
import net.redreaper.backported_spellbooks.init.*;
import net.redreaper.backported_spellbooks.item.staves.eyebloosom_staff.EyebloosomStaffRenderer;
import net.redreaper.backported_spellbooks.item.staves.miasma_staff.MiasmaStaffRenderer;
import net.redreaper.backported_spellbooks.item.weapons.GardenRapierItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(BackportedSpellbooks.MOD_ID)
public class BackportedSpellbooks {
    public static final String MOD_ID = "backportedspellbooks";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BackportedSpellbooks(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);

        ModCreativeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModExtendedArmorMaterials.register(modEventBus);
        ModEntities.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModSpellRegistry.register(modEventBus);
        ModParticleTypes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModSpellSubSchool.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Animation Registry
        AzIdentityRegistry.register(
                ModItems.EYEBLOOSOM_STAFF.get()
        );
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber({Dist.CLIENT})
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


            AzItemRendererRegistry.register(EyebloosomStaffRenderer::new, ModItems.EYEBLOOSOM_STAFF.get());
            AzItemRendererRegistry.register(MiasmaStaffRenderer::new, ModItems.MIASMA_STAFF.get());
            AzItemRendererRegistry.register(GardenRapierRenderer::new, ModItems.GARDEN_RAPIER.get());

            // Curio Rendering
            event.enqueueWork(() -> {
                CuriosRendererRegistry.register(ModItems.PALE_GUIDE.get(), SpellBookCurioRenderer::new);
            });


            // Animation Registry
            AzIdentityRegistry.register(
                    ModItems.EYEBLOOSOM_STAFF.get()
            );
        }
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, path);
    }
}
