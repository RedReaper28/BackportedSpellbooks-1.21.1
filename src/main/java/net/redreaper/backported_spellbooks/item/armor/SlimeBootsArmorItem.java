package net.redreaper.backported_spellbooks.item.armor;

import com.blackgear.vanillabackport.common.registries.ModAttributes;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.effect.ImmolateEffect;
import io.redspace.ironsspellbooks.entity.armor.BootsOfSpeedArmorModel;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.redreaper.backported_spellbooks.entities.armor.SlimeBootsArmorModel;
import net.redreaper.backported_spellbooks.init.ModExtendedArmorMaterials;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class SlimeBootsArmorItem extends ImbuableChestplateArmorItem {
    public static final int COOLDOWN_TICKS = 1 * 20;
    public SlimeBootsArmorItem(Type type, Properties settings) {
        super(ModExtendedArmorMaterials.SLIME_BOOTS, type, settings,
                new AttributeContainer(Attributes.JUMP_STRENGTH, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ModAttributes.FRICTION_MODIFIER, 0.30, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ModAttributes.BOUNCINESS, 2.5, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(ModAttributes.AIR_DRAG_MODIFIER, -0.50, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(
                Component.translatable(
                        "tooltip.irons_spellbooks.passive_ability_no_cooldown",
                        Component.literal(Utils.timeFromTicks(Utils.applyCooldownReduction(COOLDOWN_TICKS, MinecraftInstanceHelper.getPlayer()), 1)).withStyle(ChatFormatting.AQUA)
                ).withStyle(ChatFormatting.DARK_PURPLE)
        );
        tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(ChatFormatting.GREEN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new SlimeBootsArmorModel());
    }
}
