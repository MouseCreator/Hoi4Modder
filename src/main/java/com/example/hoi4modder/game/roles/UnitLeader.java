package com.example.hoi4modder.game.roles;

import com.example.hoi4modder.model.files.iovisitor.Visitor;
import lombok.*;

import java.util.HashSet;

/**
 * Army commander
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnitLeader extends Role {
    private int skill;
    @Getter(AccessLevel.NONE)
    private boolean fieldMarshal;
    private int attackSkill;
    private int defenceSkill;
    private int planningSkill;
    private int logisticsSkill;

    /**
     *
     * @return sample corps commander
     */
    public static UnitLeader createCorpsCommander() {
        UnitLeader commander = weakUnitLeader();
        commander.fieldMarshal=false;
        return commander;
    }
    /**
     *
     * @return sample field marshal
     */
    public static UnitLeader createFieldMarshal() {
        UnitLeader commander = weakUnitLeader();
        commander.fieldMarshal=true;
        return commander;
    }
    private static UnitLeader weakUnitLeader() {
        UnitLeader commander = new UnitLeader();
        commander.setSkill(1);
        commander.setAttackSkill(1);
        commander.setDefenceSkill(1);
        commander.setPlanningSkill(1);
        commander.setLogisticsSkill(1);
        commander.setTraits(new HashSet<>());
        return commander;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitUnitLeader(this);
    }

    @Override
    public String getTitle() {
        return fieldMarshal ? "field_marshal" : "corps_commander";
    }

    /**
     *
     * @return true, if the leader is field marshal
     */
    public boolean getFieldMarshal() {
        return fieldMarshal;
    }
}
