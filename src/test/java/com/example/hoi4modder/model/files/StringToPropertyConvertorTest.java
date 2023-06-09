package com.example.hoi4modder.model.files;

import com.example.hoi4modder.model.files.properties.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringToPropertyConvertorTest {

    @Test
    void testBuilding() {
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String sampleFile = """
                            
                state={
                	id=15
                	name="STATE_15"
                	manpower = 2332100
                	resources={
                		steel=21 # was: 36
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
        String origin = convertor.normalizeString(convertor.toLines(sampleFile)).replace(" ","");
        Property property = convertor.forStructuredFile(sampleFile);
        String toFile = property.toFile();
        toFile = toFile.replace("\n", "");
        toFile = toFile.replace("\t", "");
        toFile = toFile.replace(" ", "");
        assertEquals(origin, toFile);
    }
    @Test
    void testLocalisation() {
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String localisationFile = """
                l_russian:
                 fascist_influence:0 "Влияние фашизма"
                 fascist_influence_desc:0 "Влияние фашизма сделало эту идеологию чрезвычайно популярной, и страна охотнее поддержит другие фашистские государства."
                 communist_influence:0 "Влияние коммунизма"
                 communist_influence_desc:0 "Влияние коммунизма сделало эту идеологию чрезвычайно популярной, и страна охотнее поддержит другие коммунистические государства."
                 democratic_influence:0 "Влияние демократии"
                 democratic_influence_desc:0 "Влияние демократии сделало эту идеологию чрезвычайно популярной, и страна охотнее поддержит другие демократические государства."
                 neutrality_influence:0 "Авторитарное влияние"
                 neutrality_influence_desc:0 "Влияние авторитаризма сделало государства с сильным правительством и благородными королевскими домами чрезвычайно популярными, и страна охотнее поддержит другие авторитарные государства."
                 soviet_german_friendship:0 "Дружба между СССР и Германией"
                 soviet_german_friendship_desc:0 "Третий рейх и Советский Союз объединили усилия в борьбе против разлагающегося Запада."
                 sour_loser:0 "Горечь поражения"
                 sour_loser_desc:0 "Политику этого государства определяет горечь поражения в Великой войне."
                 received_attache:0 "Прибыл атташе"
                 received_attache_desc:0 "Иностранные военные атташе на нашей территории."
                 ATTACHE_LIST:0 "Прибыли военные атташе ($LIST$). Они окажут следующий эффект:"
                 porsche:0 "«Порше»"
                 porsche_desc:0 "«Порше» отдает предпочтение тяжелой бронетехнике."
                 henschel:0 "«Хеншель»"
                 henschel_desc:1 "«Хеншель» отвечал за все серийные модели «Тигров». После того как ранние проблемы с надежностью были устранены, танки показали себя вполне достойно."
                 GER_MAN:0 "MAN\"""";
        Property property = convertor.forStructuredFile(convertor.toLines(localisationFile));
        String toFile = property.toFile();
        while (toFile.endsWith("\n")) {
            toFile = toFile.substring(0, toFile.length()-1);
        }
        assertEquals(localisationFile, toFile);
    }

}