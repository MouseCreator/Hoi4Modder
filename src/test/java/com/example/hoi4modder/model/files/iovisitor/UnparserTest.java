package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.properties.Property;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class UnparserTest {

    @Test
    void testUnparser() {
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
        testForSample(sample);
    }

    private static void testForSample(String sample) {
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        Property baseProperty = convertor.forStructuredFile(sample);
        Parser parser = new Parser();
        GameCharacterList list = new GameCharacterList(new LinkedList<>());
        parser.setBlock(baseProperty);
        parser.visitCharacterList(list);

        Unparser unparser = new Unparser();
        unparser.visitCharacterList(list);
        Property property = unparser.getBlock();
        System.out.println(property.toFile());
    }


    @Test
    void testLocalisation() {
        String sample = """
                l_russian:
                  #SOVIET UNION
                  SOV_iosif_stalin:0 "Иосиф Сталин"
                  SOV_konstantin_rodzayevsky:0 "Константин Родзаевский"
                  SOV_aleksandr_kerensky:0 "Александр Керенский"
                  SOV_supreme_soviet:0 "Верховный Совет"
                  SOV_provisional_government:0 "Временное правительство"
                 
                  SOV_georgy_zhukov:0 "Георгий Жуков"
                  SOV_konstantin_rokossovsky:0 "Константин Рокоссовский"
                	
                	""";
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        convertor.forStructuredFile(sample);
    }
}