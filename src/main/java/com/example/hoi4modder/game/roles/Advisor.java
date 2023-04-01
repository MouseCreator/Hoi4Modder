package com.example.hoi4modder.game.roles;

import com.example.hoi4modder.model.files.iovisitor.Visitor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class Advisor extends Role{
    private String slot;
    private String token;
    private String ledger;
    private int cost;

    public Advisor() {
    }

    public static Advisor createAdvisor() {
        Advisor result = new Advisor();
        result.cost = 150;
        result.slot = "political_advisor";
        result.ledger = "";
        result.token = "NO_TOKEN";
        result.setTraits(new HashSet<>());
        return result;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitAdvisor(this);
    }

    @Override
    public String getTitle() {
        return "advisor";
    }

    public void toLedger() {
        if (slot.equals("high_command") || slot.equals("theorist")) {
            for (String trait : traits) {
                if (trait.contains("army")) {
                    setLedger("army");
                    return;
                } else if (trait.contains("air")) {
                    setLedger("air");
                    return;
                } else if (trait.contains("navy")) {
                    setLedger("navy");
                    return;
                }
            }
        }
        setLedger("");
    }
}
