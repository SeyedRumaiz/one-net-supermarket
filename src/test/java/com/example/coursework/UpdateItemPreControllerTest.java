package com.example.coursework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class UpdateItemPreControllerTest {

    private UpdateItemPreController controller;
    File file;

    @BeforeEach
    void setUp() {
        file = new File("test_items.txt");
        controller = new UpdateItemPreController();
        ItemFileHandler itemFileHandler = new ItemFileHandler("test_items.txt");
        Item item = new Item(1, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
        itemFileHandler.items.add(item);
        itemFileHandler.saveItems(item);
    }

    @AfterEach
    void tearDown() {
        if (file.exists()) {
            boolean deleted = file.delete();
        }
    }

    @Test
    void validateEmptyCode() {
        String emptyCode = "";
        String emptyCodeResult = controller.errorValidation(emptyCode);
        assertEquals("Please enter a code to update!", emptyCodeResult);    // empty field
    }

    @Test
    void validateNonExistentCode() {
        String nonExistingCode = "100";
        String existingCodeResult = controller.errorValidation(nonExistingCode);        // non-existent item code
        assertEquals("This item does not exist!", existingCodeResult);
    }

    @Test
    void validateNonNumericCode() {
        String nonNumericCode = "abc";
        String nonNumericCodeResult = controller.errorValidation(nonNumericCode);   // non-numeric item code
        assertEquals("Please enter a numeric item code!", nonNumericCodeResult);
    }

    @Test
    void validateValidCode() {
        String validCode = "1";                                             // valid code
        String validCodeResult = controller.errorValidation(validCode);
        assertEquals("", validCodeResult);
    }
}