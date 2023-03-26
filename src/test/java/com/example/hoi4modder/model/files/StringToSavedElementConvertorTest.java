package com.example.hoi4modder.model.files;

import com.example.hoi4modder.model.files.properties.lists.SavedList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StringToSavedElementConvertorTest {
    private String sampleFile = """
                        
            state={
            	id=15
            	name="STATE_15"
            	manpower = 2332100
            	resources={
            #		steel=21 # was: 36
            	}
            	
            	state_category = city
                        
            	history={
            owner = NZE
             add_core_of = NZE
            \s
            		victory_points = {
            			9434 10
            		}
            		victory_points = {
            			6449 3
            		}
            		victory_points = {
            			11563 5
            		}
            		victory_points = {
            			485 3
            		}
            		buildings = {
            			infrastructure = 4
            #			industrial_complex = 2
            #			air_base = 6
            			6449 = {
            				naval_base = 3
            				coastal_bunker = 1
            			}
            			9434 = {
            				naval_base = 5
            				coastal_bunker = 1
            			}
            			485= {
            				naval_base = 1
            			}
            		}
                        
            	}
                        
            	provinces={
            		485 539 553 3534 3549 3579 6449 6569 6599 9434 9550 9578 11521 11535 11563\s
            	}
                        
            	local_supplies=0.0\s
            }
            """;
    @Test
    void testBuilding() {
        StringToSavedElementConvertor convertor = new StringToSavedElementConvertor();
        SavedList list = convertor.forStructuredFile(toLines());
        list.toString();
    }


    private ArrayList<String> toLines() {
        return new ArrayList<String>(List.of(sampleFile.split("\n")));
    }
}