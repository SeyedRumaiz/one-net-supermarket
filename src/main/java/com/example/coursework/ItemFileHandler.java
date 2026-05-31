package com.example.coursework;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ItemFileHandler {

    private final String PATH;

    public ItemFileHandler(String path) {       // when testing, using temp file
        this.PATH = path;
    }

    public ItemFileHandler() {
        this.PATH = "Items.txt";        // when doing normal item operations
    }

    public List<Item> items = new ArrayList<>();
    int totalItems = 0;
    double inventoryValue = 0;
    String formattedInventoryValue;         // to 2 decimal places

    /// This method reads the Items.txt file
    private List<String> readFile() {
        List<String> items = new ArrayList<>();
        try {
            File itemsFile = new File(PATH);
            Scanner scanner = new Scanner(itemsFile);
            while (scanner.hasNextLine()) {
                items.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            showAlert("File Not Found Error", "Items.txt file was not found. A default file will be created.");

            File itemsFile = new File("Items.txt");
            try {
                // default data if file is not found
            FileWriter itemsWriter = new FileWriter(itemsFile);
            String defaultData = "1,Milk,Anchor,400.00,23,Dairy,12-06-2025,src/main/resources/images/anchormilk.jpeg,50\n" +
                    "2,Bread,Wonder,120.00,10,Bakery,10-06-2025,src/main/resources/images/wonderbread.jpeg,20\n" +
                    "3,Eggs,Happy Egg,300.00,145,Dairy,11-06-2025,src/main/resources/images/happyegg.png,30\n" +
                    "4,Rice,Golden Harvest,950.00,75,Grains,15-06-2025,src/main/resources/images/goldenharvest.jpg,70\n" +
                    "5,Pen,Atlas,350.00,45,Pens,12-06-2025,src/main/resources/images/atlaspen.jpg,80\n" +
                    "6,Eraser,Maped,550.00,45,Erasers,15-05-2025,src/main/resources/images/mapederaser.jpg,100\n";
            itemsWriter.write(defaultData);
            itemsWriter.close();

            } catch (IOException er) {
                showAlert("Writing Error", er.getMessage());
            }
        }
        return items;
    }

    /// This method initializes items
    public List<Item> loadItems() {      // not going to need it for other classes
        items.clear();
        List<String> data = readFile();
        for (String line : data) {
            String[] info = line.split(",");
            int code = Integer.parseInt(info[0]);
            String name = info[1];
            String brand = info[2];
            double price = Double.parseDouble(info[3]);
            short quantity = Short.parseShort(info[4]);
            String category = info[5];
            String date = info[6];
            String itemPath = info[7];
            byte minQuantity = Byte.parseByte(info[8]);
            Item thisItem = new Item(code,name,brand,price,quantity,category,date,itemPath, minQuantity);
            items.add(thisItem);
        }
        return items;
    }

    /// This method loads low stock items
    public List<Item> lowStockItems() {
        List<Item> lowStockItems = new ArrayList<>();

        for (Item item : items) {        // storing all low stock items
            if (item.getItemQuantity() < item.getMinQuantity()) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }

    /// This method adds the new Item
    public void addItem(int ITEM_CODE, String itemName, String itemBrand, double itemPrice,
                        short itemQuantity, String itemCategory, String ITEM_PURCHASED_DATE, String itemPath, byte MIN_QUANTITY) {
        Item item = new Item(ITEM_CODE, itemName, itemBrand,itemPrice,itemQuantity,
                itemCategory,ITEM_PURCHASED_DATE,itemPath, MIN_QUANTITY);
        items.add(item);
        saveItems(item);
    }

    /// This method checks if an item exists by the code
    public boolean codeExists(int code) {
        for (Item item : items) {
            if (item.getItemCode() == code) {
                return true;
            }
        }
        return false;
    }

    /// This method deletes an item
    public void deleteItem(Item item) {

        Item itemToRemove = null;
        for (Item value : items) {
            if (value.getItemCode() == item.getItemCode()) {
                itemToRemove = value;
                break;
            }
        }
        items.remove(itemToRemove);         // guaranteed to be in the items list
        saveItems(items);
    }

    /// This method updates an item
    public void updateItem(int oldCode, String newName, String newBrand, double newPrice, short newQuantity, String newCategory, String newItemPath) {

        for (Item current : items) {
            if (current.getItemCode() == oldCode) {
                current.setItemName(newName);
                current.setItemBrand(newBrand);
                current.setItemPrice(newPrice);
                current.setItemQuantity(newQuantity);
                current.setItemCategory(newCategory);
                current.setItemPath(newItemPath);
                saveItems(items);
            }
        }
    }

    /// This method displays all items, organized by their category and ID: with respective to their category
    public List<Item> viewItems() {

        // Sorting by itemCategory
        int n = items.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                Item current = items.get(j);
                Item next = items.get(j+1);
                if (current.getItemCategory().compareTo(next.getItemCategory()) > 0) {
                    items.set(j, next);      // swap the category at start of the alphabet to the front
                    items.set(j+1, current);
                }
            }
        }
        // Sorting the item codes for each category
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < n;) {
            List<Item> temp = new ArrayList<>();
            Item start = items.get(i);
            // extracting portions of the same categories first and insert them to a temp list
            while (i < n && items.get(i).getItemCategory().equals(start.getItemCategory()))  {
                Item current = items.get(i);
                temp.add(current);
                i++;         // incrementing i so that we can use it for the next set of item codes for a category
            }
            int tempSize = temp.size();
            for (int j = 0; j < tempSize-1; j++) {     // Now sort the item codes for the group (category)
                for (int k = 0; k < tempSize-1- j; k++) {
                    Item now = temp.get(k);
                    Item after = temp.get(k +1);
                    if (now.getItemCode() > after.getItemCode()) {
                        temp.set(k, after);  // swap the lowest code to the front using bubble sort
                        temp.set(k + 1, now);
                    }
                }
            }
            result.addAll(temp);        // add all sorted ones to this semi-final list
        }
        items = result;      // change the original one to this new fully sorted list

        for (Item item : items) {           // getting the total monetary inventory value and total items
            double price = item.getItemPrice();
            int quantity = item.getItemQuantity();
            inventoryValue += price * quantity;
            formattedInventoryValue = String.format("%.2f", inventoryValue);
            totalItems += quantity;
        }
        return result;
    }

    /// method to save after adding new item
    public void saveItems(Item item) {
        try {
            File itemsFile = new File(PATH);
            FileWriter itemsWriter = new FileWriter(itemsFile, true);       // append mode
            itemsWriter.write(item.toString() + "\n");         // in the right format
            itemsWriter.close();
        } catch (IOException e) {
            showAlert("Saving Error", "An error occurred while writing file: " + e.getMessage());
        }
    }

    /// method to save new details after updating/deleting (overwrite file)
    public void saveItems(List<Item> items) {
        try {
            FileWriter itemsWriter = new FileWriter(PATH);
            for (Item item : items) {
                itemsWriter.write(item.toString() + "\n");
            }
            itemsWriter.close();
        } catch (IOException e) {
            showAlert("Saving Error", "An error occurred while writing file: " + e.getMessage());
        }
    }

    ///  method to show alerts to the user for any errors
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}