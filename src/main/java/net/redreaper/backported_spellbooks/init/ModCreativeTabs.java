package net.redreaper.backported_spellbooks.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BackportedSpellbooks.MOD_ID);


    public static final Supplier<CreativeModeTab> MONSTERS_AND_GEAR_TAB=CREATIVE_MODE_TAB.register("backportedspellbooks",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.PALE_GUIDE.get()))
                    .title(Component.translatable("creative_tab.backportedspellbooks.backported_spellbooks"))
                    .displayItems((itemDisplayParameters, output) ->{


                        output.accept(ModItems.EYEBLOOSOM_STAFF.get());
                        output.accept(ModItems.MIASMA_STAFF.get());
                        output.accept(ModItems.PALE_GUIDE.get());
                        output.accept(ModItems.QUICKSILVER_SPELLBOOK.get());
                        output.accept(ModItems.GARDEN_RAPIER.get());

                        output.accept(ModItems.PALE_OBSERVER_HELMET.get());
                        output.accept(ModItems.PALE_OBSERVER_CHESTPLATE.get());
                        output.accept(ModItems.PALE_OBSERVER_LEGGINGS.get());
                        output.accept(ModItems.PALE_OBSERVER_BOOTS.get());
                        output.accept(ModItems.SLIME_BOOTS.get());

                        output.accept(ModItems.RESIN_VIAL.get());
                        output.accept(ModItems.PALE_AMBER.get());
                        output.accept(ModItems.CORRODED_FOSSIL.get());
                        output.accept(ModItems.RAW_QUICKSILVER.get());
                        output.accept(ModItems.QUICKSILVER_INGOT.get());

                        output.accept(ModBlocks.CORRODED_FOSSIL_ORE.get());
                        output.accept(ModBlocks.QUICkSILVER_ORE.get());
                        output.accept(ModBlocks.RAW_QUICkSILVER_BLOCK.get());
                        output.accept(ModBlocks.QUICkSILVER_BLOCK.get());
                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
