package net.redreaper.backported_spellbooks.item.staves.miasma_staff;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;

public class MiasmaStaffRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath("backportedspellbooks", "geo/item/staff/miasma_staff.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath("backportedspellbooks", "textures/item/staff/miasma_staff.png");

    public MiasmaStaffRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).build());
    }
}
