package net.redreaper.backported_spellbooks.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.redreaper.backported_spellbooks.BackportedSpellbooks;

public class ModTags {
    public static class Items{

        public static final TagKey<Item> PALE_FOCUS=createTag("pale_flora_focus");


        private static TagKey<Item>createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(BackportedSpellbooks.MOD_ID,name));
        }
    }
}
