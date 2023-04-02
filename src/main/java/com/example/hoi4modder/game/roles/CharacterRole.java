package com.example.hoi4modder.game.roles;


import com.example.hoi4modder.model.files.iovisitor.Visitor;

import java.util.Set;

/**
 * Role for game character
 */
public interface CharacterRole {
    /**
     *
     * @return set of role traits
     */
    Set<String> getTraits();

    /**
     *
     * @param traitKeys - traits to set
     */
    void setTraits(Set<String> traitKeys);

    /**
     *
     * @param visitor - visitor to get
     */
    void acceptVisitor(Visitor visitor);

    /**
     *
     * @return title, under which the role saved in files
     */
    String getTitle();
}
