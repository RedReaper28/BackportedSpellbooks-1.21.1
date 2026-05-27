package net.redreaper.backported_spellbooks.entities.armor;

import net.minecraft.resources.ResourceLocation;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;
import net.redreaper.backported_spellbooks.item.armor.SlimeBootsArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class SlimeBootsArmorModel extends DefaultedItemGeoModel<SlimeBootsArmorItem> {

    public SlimeBootsArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath("backportedspellbooks", ""));
    }

    public ResourceLocation getModelResource(SlimeBootsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "geo/armor/pale_observer_armor.geo.json");
    }

    public ResourceLocation getTextureResource(SlimeBootsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID, "textures/armor/slime_boots.png");
    }

    public ResourceLocation getAnimationResource(SlimeBootsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "animations/wizard_armor_animation.json");
    }
}
