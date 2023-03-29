package com.example.hoi4modder.game.roles;

import com.example.hoi4modder.model.files.iovisitor.Visitor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class CountryLeader extends Role {
    private String ideology;

    public static CountryLeader createCountryLeader() {
        CountryLeader leader = new CountryLeader();
        leader.setTraits(new HashSet<>());
        leader.setIdeology("socialism");
        return leader;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitCountryLeader(this);
    }

    @Override
    public String getTitle() {
        return "country_leader";
    }
}
