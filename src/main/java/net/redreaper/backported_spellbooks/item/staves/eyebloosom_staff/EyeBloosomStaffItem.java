package net.redreaper.backported_spellbooks.item.staves.eyebloosom_staff;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.redreaper.backported_spellbooks.init.ModStaffMaterials;

public class EyeBloosomStaffItem extends StaffItem {
    public EyeBloosomStaffItem() {
        super(ItemPropertiesHelper.equipment(1).fireResistant().rarity(ASRarities.VERDANT_RARITY_PROXY.getValue())
                .attributes(ExtendedSwordItem.createAttributes(ModStaffMaterials.EYEBLOOSOM)));
    }
}