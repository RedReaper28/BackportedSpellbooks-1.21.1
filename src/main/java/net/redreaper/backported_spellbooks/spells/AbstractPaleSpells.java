package net.redreaper.backported_spellbooks.spells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;

public abstract class AbstractPaleSpells extends AbstractSpell {

    @Override
    public boolean allowLooting() {
        return false;
    }
}