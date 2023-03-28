package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.StringToSavedElementConvertor;
import com.example.hoi4modder.model.files.properties.Property;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SaverTest {
    @Test
    void testForCharacterList() {
        String sample = """
                characters = {
                	ENT_leyla_athella = {
                		name = ENT_leyla_athella
                		portraits = {
                			civilian = {
                				large = GFX_Portrait_Ent_Leyla
                			}
                		}
                		country_leader = {
                			ideology = despotism
                			traits = { leyla_trait }
                			expire = "1955.1.1.1"
                			id = -1
                		}
                	}
                	
                	ENT_diego_vesicki = {
                 		name = ENT_diego_vesicki
                 		portraits = {
                 			civilian = {
                 				large = GFX_portrait_Ent_Diego
                 			}
                 		}
                 		country_leader = {
                 			ideology = stalinism
                 			traits = { veteran_communist  }
                 			expire = "1955.1.1.1"
                 			id = -1
                 		}
                 	}
                 	
                 	ENT_char3 = {
                  		name = ENT_char3
                  		portraits = {
                  			army = {
                  				large = GFX_Ent_leader3
                  				small = "GFX_idea_ENT_advisor5"
                  			}
                  		}
                  		corps_commander={
                  			traits={ armor_officer career_officer }
                  			skill=4
                  			attack_skill=4
                  			defense_skill=2
                  			planning_skill=3
                  			logistics_skill=2
                  			legacy_id=-1
                  		}
                  		advisor={
                  			cost=100
                  			slot = high_command
                  			ledger = army
                  			idea_token = ent_char3
                  			allowed = {
                  				original_tag =ENT
                  			}
                  			
                  			traits = {
                  				army_armored_2
                  			}
                  			ai_will_do = {
                  				factor = 2
                  			}
                  		}
                  	}
                }
                	
                	""";
        StringToSavedElementConvertor convertor = new StringToSavedElementConvertor();
        Property baseProperty = convertor.forStructuredFile(sample);
        Saver saver = new Saver();
        GameCharacterList list = new GameCharacterList(new LinkedList<>());
        saver.visitCharacterList(list, baseProperty);
        System.out.println(list);
    }
}