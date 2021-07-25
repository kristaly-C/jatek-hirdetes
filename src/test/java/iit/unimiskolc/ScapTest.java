package iit.unimiskolc;

import iit.unimiskolc.domain.Scrap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ScapTest {

    Scrap scrapper = new Scrap();


    @Test
    @DisplayName("change searchable name")
    void scrapy(){
        String ret = scrapper.getGameSearchName("Battlefield 5");
        assertEquals("https://store.steampowered.com/search/?term=Battlefield+5",ret);
    }

    @Test
    @DisplayName("get gameurl")
    void urlscrap(){
        String url = scrapper.getGameSearchName("Grand Theft Auto 5");
        String ret = scrapper.getSiteUrl(url);
        System.out.println(scrapper.scrapGameName(ret));
        System.out.println(scrapper.scrapPicture(ret));
        assertEquals("https://store.steampowered.com/app/271590/Grand_Theft_Auto_V/?snr=1_7_7_151_150_1", ret);
    }
}
