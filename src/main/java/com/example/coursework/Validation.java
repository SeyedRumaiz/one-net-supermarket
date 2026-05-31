package com.example.coursework;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Validation utility class for update and add operations **/
public class Validation {

    /// Code validation
    public static int handleCode(TextField codeField, Label errorLabel) {
        String code = codeField.getText().replace(" ", ""); // if the user enters a space
        ItemFileHandler itemFileHandler = new ItemFileHandler();
        itemFileHandler.loadItems();
        String error = validateCode(code, itemFileHandler);
        errorLabel.setText(error);

        if (error.isEmpty()) {
            return Integer.parseInt(code);
        } else {
            return -1;
        }
    }

    ///  Helper method for code validation
    public static String validateCode(String codeText, ItemFileHandler handler) {
        if (codeText.isEmpty()) {
            return "Field is mandatory.";
        }
        int code;
        try {
            code = Integer.parseInt(codeText);
            if (code <= 0) {
                return "Code cannot be less than or equal to 0.";
            } else if (handler.codeExists(code)) {
                return "This item already exists!";
            }
        } catch (NumberFormatException e) {
            return "Please enter a numeric item code!";
        }
        return "";      // if a valid code is entered
    }

    /// used by item category, name and brand
    public static String handleInput(TextField inputField, Label errorLabel) {
        String itemInput = inputField.getText().trim();
        String error = validateInput(itemInput);
        errorLabel.setText(error);

        if (error.isEmpty()) {      // if no error
            return itemInput.substring(0, 1).toUpperCase() + itemInput.substring(1).toLowerCase();
        }               // capitalizing
        return "";
    }

    ///  Helper method for input validation
    public static String validateInput(String inputText) {
        if (inputText.isEmpty()) {
            return "Field is mandatory.";
        }
        return "";
    }

    /// price validation
    public static double handlePrice(TextField priceField, Label errorLabel) {
        String itemPriceInput = priceField.getText().replace(" ","");

        String error = validatePrice(itemPriceInput);
        errorLabel.setText(error);

        if (error.isEmpty()) {      // if valid
            return Double.parseDouble(itemPriceInput);
        }
        return -1;      // if invalid
    }

    /// Helper method for price validation
    public static String validatePrice(String priceText) {
        if (priceText.isEmpty()) {
            return "Field is mandatory.";
        }
        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) {
                return "Price must be greater than 0!";
            }
        } catch (NumberFormatException e) {
            return "Please enter a numeric item price!";
        }
        return "";
    }

    /// quantity validation
    public static short handleQuantity(TextField quantityField, Label errorLabel) {
        String itemQuantityInput = quantityField.getText().replace(" ", "");

        String error = validateQuantity(itemQuantityInput);
        errorLabel.setText(error);

        if (error.isEmpty()) {
            return Short.parseShort(itemQuantityInput);
        }
        return -1;
    }

    /// Helper method for quantity validation
    public static String validateQuantity(String quantityText) {
        if (quantityText.isEmpty()) {
            return "Field is mandatory.";
        }
        short quantity;
        try {
            quantity = Short.parseShort(quantityText);
            if (quantity <= 0) {
                return "Quantity must be greater than 0!";
            }
        } catch (NumberFormatException e) {
            return "Please enter a numeric item quantity!";
        }
        return "";
    }


    /// threshold quantity validation
    public static byte handleThresholdQuantity(TextField thresholdQuantityField, Label errorLabel) {
        String itemThresholdQuantityInput = thresholdQuantityField.getText().replace(" ", "");

        String error = validateThresholdQuantity(itemThresholdQuantityInput);
        errorLabel.setText(error);

        if (error.isEmpty()) {
            return Byte.parseByte(itemThresholdQuantityInput);
        }
        return -1;
    }

    /// Helper method for threshold quantity validation
    public static String validateThresholdQuantity(String thresholdQuantityText) {
        if (thresholdQuantityText.isEmpty()) {
            return "Field is mandatory.";
        }
        byte thresholdQuantity;
        try {
            thresholdQuantity = Byte.parseByte(thresholdQuantityText);
            if (thresholdQuantity <= 0) {
                return "Threshold quantity must be greater than 0!";
            }
        } catch (NumberFormatException e) {
            return "Please enter a numeric threshold quantity!";
        }
        return "";
    }

    /// purchased date validation
    public static String handleDate(DatePicker dateField, Label errorLabel) {
        LocalDate itemDateInput = dateField.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String error = validateDate(itemDateInput);
        errorLabel.setText(error);

        if (error.isEmpty()) {
            return itemDateInput.format(formatter);
        }
        return "";
    }

    /// Helper method for date validation
    public static String validateDate(LocalDate date) {
        if (date == null) {
            return "Field is mandatory.";
        }
        else if (date.isAfter(LocalDate.now())) {
            return "Please enter a date in the past!";
        }
        return "";
    }

    ///  Image validation
    public static String handleImagePath(Stage stage, Button imageField, Label errorLabel) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")  // allowable files
        );
        File selectedFile = fileChooser.showOpenDialog(stage.getScene().getWindow());
                            // getting the current window
        String error = validateImagePath(selectedFile);

        if (error.isEmpty()) {
            imageField.setText(selectedFile.getPath());
            errorLabel.setText(error);
            return selectedFile.getPath();
        } else {
            errorLabel.setText(error);
            String defaultPath = "/images/defaultimage.jpg";
            imageField.setText(defaultPath);
            return defaultPath;
        }
    }

    /// Helper method for image validation
    public static String validateImagePath(File file) {
        if (file == null) {
            return "A default image will be provided if no image was found.";
        }
        return "";
    }
}