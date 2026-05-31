package com.example.coursework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    ItemFileHandler itemFileHandler;
    File file;

    @BeforeEach
    void setUp() {
        file = new File("test_items.txt");          // for item code existence input
        itemFileHandler = new ItemFileHandler("test_items.txt");
        Item item = new Item(3, "Pen", "Atlas", 500.0, (short) 20, "Pens", "03-04-2025", "", (byte) 50);
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
        String emptyCodeResult = Validation.validateCode(emptyCode, itemFileHandler);
        assertEquals("Field is mandatory.", emptyCodeResult);               // empty field
    }

    @Test
    void validateNegativeCode() {
        String negativeCode = "-3";
        String negativeCodeResult = Validation.validateCode(negativeCode, itemFileHandler);
        assertEquals("Code cannot be less than or equal to 0.", negativeCodeResult);       // negative code
    }

    @Test
    void validateNonNumericCode() {
        String nonNumericCode = "abc";
        String nonNumericCodeResult = Validation.validateCode(nonNumericCode, itemFileHandler);
        assertEquals("Please enter a numeric item code!", nonNumericCodeResult);         // non-numeric code
    }

    @Test
    void validateExistingCode() {
        String existingCode = "3";
        String existingCodeResult = Validation.validateCode(existingCode, itemFileHandler);
        assertEquals("This item already exists!", existingCodeResult);     // existent code
    }

    @Test
    void validateValidCode() {
        String validCode = "37";
        String validCodeResult = Validation.validateCode(validCode, itemFileHandler);
        assertEquals("", validCodeResult);                          // valid code
    }

    /** These input test cases include the item name, brand, and category **/
    @Test
    void validateEmptyInput() {
        String emptyInput = "";
        String emptyInputResult = Validation.validateInput(emptyInput);
        assertEquals("Field is mandatory.", emptyInputResult);       // empty field
    }
    @Test
    void validateValidInput() {
        String validInput = "lasagna";
        String validateInputResult = Validation.validateInput(validInput);
        assertEquals("", validateInputResult);              // valid field
    }

    @Test
    void validateEmptyPrice() {
        String emptyPrice = "";
        String emptyPriceResult = Validation.validatePrice(emptyPrice);
        assertEquals("Field is mandatory.", emptyPriceResult);       // empty field
    }
    @Test
    void validateNegativePrice() {
        String negativePrice = "-5";
        String negativePriceResult = Validation.validatePrice(negativePrice);
        assertEquals("Price must be greater than 0!", negativePriceResult);     // negative price
    }

    @Test
    void validateNonNumericPrice() {
        String nonNumericPrice = "abc";
        String nonNumericPriceResult = Validation.validatePrice(nonNumericPrice);
        assertEquals("Please enter a numeric item price!", nonNumericPriceResult);        // non-numeric price
    }

    @Test
    void validateValidPrice() {
        String validPrice = "780.0";
        String validPriceResult = Validation.validatePrice(validPrice);          // valid price
        assertEquals("", validPriceResult);
    }


    @Test
    void validateEmptyQuantity() {
        String emptyQuantity = "";
        String emptyQuantityResult = Validation.validateQuantity(emptyQuantity);        // empty field
        assertEquals("Field is mandatory.", emptyQuantityResult);
    }

    @Test
    void validateNegativeQuantity() {
        String negativeQuantity = "-500";
        String negativeQuantityResult = Validation.validateQuantity(negativeQuantity);
        assertEquals("Quantity must be greater than 0!", negativeQuantityResult);      // negative quantity
    }

    @Test
    void validateNonNumericQuantity() {
        String nonNumericQuantity = "abc";
        String nonNumericQuantityResult = Validation.validateQuantity(nonNumericQuantity);
        assertEquals("Please enter a numeric item quantity!", nonNumericQuantityResult);     // non-numeric quantity
    }

    @Test
    void validateValidQuantity() {
        String validQuantity = "35";
        String validQuantityResult = Validation.validateQuantity(validQuantity);
        assertEquals("", validQuantityResult);                          // valid quantity
    }


    @Test
    void validateEmptyThresholdQuantity() {
        String emptyThreshold = "";
        String emptyThresholdResult = Validation.validateThresholdQuantity(emptyThreshold);
        assertEquals("Field is mandatory.", emptyThresholdResult);           // empty field
    }

    @Test
    void validateNegativeThresholdQuantity() {
        String negativeThreshold = "-30";
        String negativeThresholdResult = Validation.validateThresholdQuantity(negativeThreshold);
        assertEquals("Threshold quantity must be greater than 0!", negativeThresholdResult);        // negative threshold quantity
    }

    @Test
    void validateNonNumericThresholdQuantity() {
        String nonNumericThreshold = "abc";
        String nonNumericThresholdResult = Validation.validateThresholdQuantity(nonNumericThreshold);
        assertEquals("Please enter a numeric threshold quantity!", nonNumericThresholdResult);        // non-numeric threshold quantity
    }

    @Test
    void validateValidThresholdQuantity() {
        String validThreshold = "80";
        String validThresholdResult = Validation.validateThresholdQuantity(validThreshold);      // valid threshold quantity
        assertEquals("", validThresholdResult);
    }


    @Test
    void validateEmptyDate() {
        LocalDate noDate = null;                                        // No date
        String noDateResult = Validation.validateDate(noDate);
        assertEquals("Field is mandatory.", noDateResult);
    }

    @Test
    void validateFutureDate() {
        LocalDate futureDate = LocalDate.of(2030, 12,12);       // from the future
        String futureDateResult = Validation.validateDate(futureDate);
        assertEquals("Please enter a date in the past!", futureDateResult);
    }

    @Test
    void validateValidDate() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);        // valid date
        String validDateResult = Validation.validateDate(validDate);
        assertEquals("", validDateResult);
    }

    @Test
    void validateEmptyImagePath() {
        File noFile = null;
        String noFileResult = Validation.validateImagePath(noFile);     // no image selected
        assertEquals("A default image will be provided if no image was found.", noFileResult);
    }

    @Test
    void validateValidImagePath() {
        File validFile = new File("test.jpg");
        String validResult = Validation.validateImagePath(validFile);
        assertEquals("", validResult);                  // valid image
    }
}