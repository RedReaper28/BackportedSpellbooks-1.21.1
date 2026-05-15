package net.redreaper.backported_spellbooks.item.staves.eyebloosom_staff;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzItemAnimator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EyeBloosomStaffAnimator extends AzItemAnimator {
    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath("backportedspellbooks", "animations/item/eyebloom_staff.animation.json");

    public void registerControllers(AzAnimationControllerContainer<ItemStack> animationControllerContainer) {
        animationControllerContainer.add(AzAnimationController.builder(this, "base_controller").build());
    }

    public @NotNull ResourceLocation getAnimationLocation(ItemStack animatable) {
        return ANIMATIONS;
    }
}