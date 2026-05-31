package com.example.coursework;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DealersFileHandlerTest {

    DealersFileHandler dealersFileHandler = new DealersFileHandler();

    @Test
    void selectFourRandomDealers() {
        List<Dealer> randomDealersSet = dealersFileHandler.selectRandomDealers();
        assertEquals(4, randomDealersSet.size(), "Must be 4 random dealers");
    }

    @Test
    void distinctRandomDealers() {
        List<Dealer> firstRandomDealersSet = dealersFileHandler.selectRandomDealers();
        List<Dealer> secondRandomDealersSet = dealersFileHandler.selectRandomDealers();

        assertNotEquals(firstRandomDealersSet, secondRandomDealersSet, "Expected atleast one dealer to be different.");
        // if they are the exact same list of dealers
    }

    @Test
    void sortedDealers() {
        DealerItem firstDealerFirstItem = new DealerItem("Italian Pasta", "Barilla", 600.0, (short) 25);
        DealerItem firstDealerSecondItem = new DealerItem("Frozen Pizza", "DiGiorno", 1200.0, (short) 95);
        DealerItem firstDealerThirdItem = new DealerItem("Greek Yoghurt", "Chobani", 800.0, (short) 55);
        DealerItem[] firstDealerItems = {firstDealerFirstItem, firstDealerSecondItem, firstDealerThirdItem};
        Dealer firstDealer = new Dealer("Joe", "0777111222", "Canada", firstDealerItems);

        DealerItem secondDealerFirstItem = new DealerItem("Organic Whole Milk", "Horizon", 750.0, (short) 30);
        DealerItem secondDealerSecondItem = new DealerItem("Crunchy Peanut Butter", "Jif", 450.0, (short) 40);
        DealerItem secondDealerThirdItem = new DealerItem("Dark Chocolate Bars", "Lindt", 350.0, (short) 60);
        DealerItem[] secondDealerItems = {secondDealerFirstItem, secondDealerSecondItem, secondDealerThirdItem};
        Dealer secondDealer = new Dealer("Sarah", "0777444555", "Australia", secondDealerItems);

        List<Dealer> testSortedDealers = new ArrayList<>();
        testSortedDealers.add(secondDealer);
        testSortedDealers.add(firstDealer);        // expected sorted dealer list, according to the location

        List<Dealer> dealers = new ArrayList<>();
        dealers.add(firstDealer);
        dealers.add(secondDealer);

        List<Dealer> actualSortedDealers = dealersFileHandler.randomDealerDetails(dealers);
        assertEquals(testSortedDealers, actualSortedDealers);       // checks for order too
    }

    @Test
    void loadDealers() {
        List<Dealer> dealers = dealersFileHandler.loadDealers();
        assertEquals(6, dealers.size(), "Must be 6 dealers.");
    }
}