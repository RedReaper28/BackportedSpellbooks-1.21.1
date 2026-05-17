package net.redreaper.backported_spellbooks.entities.weapons;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;

public class GardenRapierRenderer  extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("backportedspellbooks", "geo/item/weapons/garden_rapier.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("backportedspellbooks", "textures/item/weapons/garden_rapier.png");

    public GardenRapierRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).addRenderLayer(new AzAutoGlowingLayer<>()).build());
    }
}

