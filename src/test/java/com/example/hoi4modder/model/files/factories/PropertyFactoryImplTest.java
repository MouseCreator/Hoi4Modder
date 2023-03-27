package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.factories.PropertyFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFactoryImplTest {

    @Test
    void toProperty() {
        String file = """
                characters = {
                	BAT_leader = {
                		name = BAT_leader
                		portraits = {
                			civilian = {
                				large = GFX_BAT_current_leader
                				small = "GFX_idea_BAT_advisor9"
                			}
                		}
                		country_leader = {
                			ideology = centrism
                			expire = "1955.1.1.1"
                			id = -1
                		}
                		advisor={
                			slot = political_advisor
                			idea_token = bat_leader
                			allowed = {
                				original_tag = BAT
                			}
                			traits = {
                				bat_neutrality_enjoyer
                			}
                			ai_will_do = {
                				factor = 1.000
                			}
                		}
                	}
                }
                """;
        PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
        //Property result = propertyFactory.toProperty(file);
    }
}