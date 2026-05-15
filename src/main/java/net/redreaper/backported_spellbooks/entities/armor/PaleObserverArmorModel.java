package net.redreaper.backported_spellbooks.entities.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.item.armor.PaleObserverArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class PaleObserverArmorModel  extends DefaultedItemGeoModel<PaleObserverArmorItem> {
    public PaleObserverArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("backportedspellbooks", ""));
    }

    public ResourceLocation getModelResource(PaleObserverArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "geo/armor/pale_observer_armor.geo.json");
    }

    public ResourceLocation getTextureResource(PaleObserverArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "textures/armor/pale_observer_armor.png");
    }

    public ResourceLocation getAnimationResource(PaleObserverArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}
