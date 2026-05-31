package com.example.coursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewDealerItemsControllerTest {

    private ViewDealerItemsController controller;

    @BeforeEach
    void setUp() {
        controller = new ViewDealerItemsController();
    }

    @Test
    void emptyDealerListTest() {
        controller.randomDealerInput(null);
        String testDealerName = "Joe";
        String emptyDealerResult = controller.dealerValidator(testDealerName);
        assertEquals("You have not selected any random dealers!", emptyDealerResult);
    }

    @Test
    void dealerNotPresentTest() {
        ObservableList<Dealer> dealers = FXCollections.observableArrayList();

        DealerItem firstDealerFirstItem = new DealerItem("Italian Pasta", "Barilla", 600.0, (short) 25);
        DealerItem firstDealerSecondItem = new DealerItem("Frozen Pizza", "DiGiorno", 1200.0, (short) 95);
        DealerItem firstDealerThirdItem = new DealerItem("Greek Yoghurt", "Chobani", 800.0, (short) 55);
        DealerItem[] firstDealerItems = {firstDealerFirstItem, firstDealerSecondItem, firstDealerThirdItem};
        Dealer firstDealer = new Dealer("Joe", "0777111222", "Canada", firstDealerItems);

        DealerItem secondDealerFirsItem = new DealerItem("Organic Whole Milk", "Horizon", 750.0, (short) 30);
        DealerItem secondDealerSecondItem = new DealerItem("Crunchy Peanut Butter", "Jif", 450.0, (short) 40);
        DealerItem secondDealerThirdItem = new DealerItem("Dark Chocolate Bars", "Lindt", 350.0, (short) 60);
        DealerItem[] secondDealerItems = {secondDealerFirsItem, secondDealerSecondItem, secondDealerThirdItem};
        Dealer secondDealer = new Dealer("Sarah", "0777444555", "Australia", secondDealerItems);

        dealers.add(firstDealer);
        dealers.add(secondDealer);

        controller.randomDealerInput(dealers);

        String testDealerName = "Mark";
        String dealerResult = controller.dealerValidator(testDealerName);
        assertEquals("Please choose one of the random dealers!", dealerResult);
    }
    @Test
    void dealerPresentTest() {
        ObservableList<Dealer> dealers = FXCollections.observableArrayList();

        DealerItem firstDealerFirstItem = new DealerItem("Italian Pasta", "Barilla", 600.0, (short) 25);
        DealerItem firstDealerSecondItem = new DealerItem("Frozen Pizza", "DiGiorno", 1200.0, (short) 95);
        DealerItem firstDealerThirdItem = new DealerItem("Greek Yoghurt", "Chobani", 800.0, (short) 55);
        DealerItem[] firstDealerItems = {firstDealerFirstItem, firstDealerSecondItem, firstDealerThirdItem};
        Dealer firstDealer = new Dealer("Joe", "0777111222", "Canada", firstDealerItems);

        dealers.add(firstDealer);

        controller.randomDealerInput(dealers);
        String testDealerName = "Joe";
        String dealerResult = controller.dealerValidator(testDealerName);
        assertEquals("", dealerResult);
    }
}
