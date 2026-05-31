package com.example.coursework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ItemFileHandlerTest {

    private ItemFileHandler itemFileHandler;
    private final String TEST_PATH = "test_items.txt";
    File file;

    @BeforeEach
    void setUp() {
        itemFileHandler = new ItemFileHandler(TEST_PATH);       // testing, so we use the parameter
        file = new File(TEST_PATH);             // make a new file for each test case
    }

    @AfterEach
    void tearDown() {
        if (file.exists()) {
            boolean deleted = file.delete();        // delete the created file after each test case
        }
    }

    @Test
    void loadItems() {

        Item item1 = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        Item item2 = new Item(2, "Pencil", "Maped", 200.0, (short) 50, "Pencils", "04-05-2025", "", (byte) 100);
        Item item3 = new Item(3, "Eraser", "Atlas", 150.0, (short) 60, "Erasers", "04-05-2025", "", (byte) 50);

        try {                       // Need to read the file first before loading
            FileWriter writer = new FileWriter(TEST_PATH);
            writer.write(item1.toString() + "\n");
            writer.write(item2.toString() + "\n");
            writer.write(item3.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        List<Item> items = itemFileHandler.loadItems();

        assertFalse(items.isEmpty(), "Items list should not be empty.");    // check if there is items
    }

    @Test
    void lowStockItems() {
        Item item1 = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        Item item2 = new Item(2, "Pencil", "Maped", 200.0, (short) 50, "Pencils", "04-05-2025", "", (byte) 100);
        Item item3 = new Item(3, "Eraser", "Atlas", 150.0, (short) 60, "Erasers", "04-05-2025", "", (byte) 50);

        itemFileHandler.items.add(item1);
        itemFileHandler.items.add(item2);
        itemFileHandler.items.add(item3);

        List<Item> lowStockItems = itemFileHandler.lowStockItems();

        assertEquals(2, lowStockItems.size());  // we made just 2 low stock items
    }

    @Test
    void addItem() {

        itemFileHandler.addItem(5, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);

        boolean added = false;

        for (Item item : itemFileHandler.items) {
            if (item.getItemCode() == 5) {
                added = true;
                break;
            }
        }
        assertTrue(added, "Item should have been added to the list.");
    }

    @Test
    void codeExists() {
        Item testItem = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        itemFileHandler.items.add(testItem);

        assertTrue(itemFileHandler.codeExists(1));
    }

    @Test
    void codeDoesNotExist() {
        Item testItem = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        itemFileHandler.items.add(testItem);

        assertFalse(itemFileHandler.codeExists(2));
    }

    @Test
    void deleteItem() {
        Item testItem = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        itemFileHandler.items.add(testItem);
        itemFileHandler.deleteItem(testItem);

        assertFalse(itemFileHandler.items.contains(testItem));
    }

    @Test
    void notDeleteItem() {
        Item testItem = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        assertFalse(itemFileHandler.items.contains(testItem));
        itemFileHandler.deleteItem(testItem);
        assertFalse(itemFileHandler.items.contains(testItem));
    }

    @Test
    void updateItem() {
        Item testItem = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        itemFileHandler.items.add(testItem);

        double oldPrice = testItem.getItemPrice();
        short oldQuantity = testItem.getItemQuantity();

        itemFileHandler.updateItem(1, "Pen", "Atlas", 1000.0, (short) 500, "Pens", "");

        double newPrice = testItem.getItemPrice();
        short newQuantity = testItem.getItemQuantity();

        assertNotEquals(oldPrice, newPrice, "Price should be updated.");
        assertNotEquals(oldQuantity, newQuantity, "Quantity should be updated.");
    }

    @Test
    void notUpdateItem() {
        Item testItem = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
            // item was never added to the items list, cannot be updated
        String oldBrand = testItem.getItemBrand();

        itemFileHandler.updateItem(1, "Pen", "Maped", 500.0, (short) 20, "Pens", "");
        String newBrand = testItem.getItemBrand();

        assertEquals(oldBrand, newBrand, "Item brand should not be updated.");
    }

    @Test
    void sortingItemsByCategoryAndCode() {
        List<Item> testList = new ArrayList<>();

        // first make 5 items
        Item testItem1 = new Item(2, "Pen", "Atlas", 1000.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        Item testItem2 = new Item(1, "Pen", "Pilot", 1500.0, (short) 200, "Pens", "03-04-2025", "", (byte) 40);
        Item testItem3 = new Item(3, "Eraser", "Maped", 300.0, (short) 67, "Erasers", "04-05-2025", "", (byte) 100);

        testList.add(testItem3);        // arranging them in the expected order
        testList.add(testItem2);
        testList.add(testItem1);

        itemFileHandler.items.add(testItem1);
        itemFileHandler.items.add(testItem2);
        itemFileHandler.items.add(testItem3);

        List<Item> sortedItems = itemFileHandler.viewItems();
        assertEquals(testList, sortedItems);
    }

    @Test
    void calculateInventoryValue() {
        Item testItem1 = new Item(2, "Pen", "Atlas", 1000.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        Item testItem2 = new Item(1, "Pen", "Pilot", 1500.0, (short) 200, "Pens", "03-04-2025", "", (byte) 40);

        double testTotalValue = testItem1.getItemPrice() * testItem1.getItemQuantity()
                + testItem2.getItemPrice() * testItem2.getItemQuantity();

        itemFileHandler.items.add(testItem1);
        itemFileHandler.items.add(testItem2);
        itemFileHandler.viewItems();

        assertEquals(testTotalValue, itemFileHandler.inventoryValue);
    }

    @Test
    void calculateTotalItems() {
        Item testItem1 = new Item(2, "Pen", "Atlas", 1000.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        Item testItem2 = new Item(1, "Pen", "Pilot", 1500.0, (short) 200, "Pens", "03-04-2025", "", (byte) 40);

        int testTotalItems = testItem1.getItemQuantity() + testItem2.getItemQuantity();

        itemFileHandler.items.add(testItem1);
        itemFileHandler.items.add(testItem2);
        itemFileHandler.viewItems();

        assertEquals(testTotalItems, itemFileHandler.totalItems);
    }

    @Test
    void saveOneItem() {
        Item testItem1 = new Item(10, "Noodles", "Maggi", 600.0, (short) 140, "Noodles", "03-05-2025", "", (byte) 50);
        itemFileHandler.saveItems(testItem1);

        List<Item> items = itemFileHandler.loadItems();

        boolean saved = false;

        for (Item item : items) {
            if (item.getItemCode() == 10) {
                saved = true;
                break;
            }
        }
        assertTrue(saved, "The item should have been saved.");
    }

    @Test
    void saveListOfItems() {

        Item testItem1 = new Item(10, "Noodles", "Maggi", 600.0, (short) 140, "Noodles", "03-05-2025", "", (byte) 50);
        Item testItem2 = new Item(11, "Pen", "Atlas", 1000.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);

        List<Item> testList = new ArrayList<>();

        testList.add(testItem1);
        testList.add(testItem2);

        itemFileHandler.saveItems(testList);

        List<Item> loadedItems = itemFileHandler.loadItems();

        int testListSize = testList.size();
        int loadedItemsSize = loadedItems.size();
        assertEquals(testListSize, loadedItemsSize);
    }
}