package net.redreaper.backported_spellbooks.item.staves.miasma_staff;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.redreaper.backported_spellbooks.init.ModStaffMaterials;

import java.util.List;

public class MiasmaStaffItem extends StaffItem {
    public static final int COOLDOWN = 10 * 20;
    public MiasmaStaffItem() {
        super(ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.VERDANT_RARITY_PROXY.getValue())
                .attributes(ExtendedSwordItem.createAttributes(ModStaffMaterials.MIASMA)));
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability", new Object[]{Component.literal(Utils.timeFromTicks((float)this.getPassiveCooldownTicks(), 1)).withStyle(ChatFormatting.LIGHT_PURPLE)}).withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(Style.EMPTY.withColor(15528848)));
        }
        else {
            tooltipComponents.add(Component.translatable("item.aces_spell_utils.more_details1").withStyle(ChatFormatting.GRAY));
        }

    }

    protected int getPassiveCooldownTicks() {
        return COOLDOWN;
    }
}
