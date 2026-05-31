package com.example.coursework;

import javafx.scene.control.Alert;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DealersFileHandler {

    List<Dealer> dealersList = new ArrayList<>();   // the array list which stores all 6 dealers

    /// method to select four random dealers
    public List<Dealer> selectRandomDealers() {

        List<Dealer> randomDealers = new ArrayList<>();     // 4 random dealers
        List<Dealer> allDealers = loadDealers();            // all 6 dealers

       while (randomDealers.size() < 4) {
            int randomIndex = (int) (Math.random() * allDealers.size());
            Dealer selectedDealer = allDealers.get(randomIndex);

            boolean duplicate = false;

            for (Dealer dealer : randomDealers) {
                if (dealer.getName().equals(selectedDealer.getName())) {
                    duplicate = true;       // objects in different memory so used loop
                    break;
                }
            }
            if (!duplicate) {
                randomDealers.add(selectedDealer);
            }
       }
        return randomDealers;
    }

    /// method to sort the randomly chosen 4 dealers
    public List<Dealer> randomDealerDetails(List<Dealer> randomDealers) {

        if (randomDealers.isEmpty()) {
            return randomDealers;     // if random dealers not selected
        }

        // sort according to location
        for (int i = 0; i < randomDealers.size()-1; i++) {
            for (int j = 0; j < randomDealers.size()-i-1; j++) {
                String currentLocation = randomDealers.get(j).getLocation();
                String nextLocation = randomDealers.get(j+1).getLocation();

                if (currentLocation.compareTo(nextLocation) > 0) {
                    Dealer temp = randomDealers.get(j);
                    randomDealers.set(j, randomDealers.get(j+1));
                    randomDealers.set(j+1, temp);
                }
            }
        }
        return randomDealers;
    }

    /// method used to load/initialize the dealers into the dealersList
    public List<Dealer> loadDealers() {
        List<String> dealersData = readDealersFile();

        for (int i = 0; i < dealersData.size(); i+=4) {

            String[] info = dealersData.get(i).split(",");
            String name = info[0];
            String phoneNumber = info[1];
            String location = info[2];
            DealerItem[] dealerItems = new DealerItem[3];

            for (int j = 0; j < 3; j++) {
                String[] itemInfo = dealersData.get(i+j+1).split(",");          // for the respective dealer
                String itemName = itemInfo[0];
                String itemBrand = itemInfo[1];
                double itemPrice = Double.parseDouble(itemInfo[2]);
                short itemQuantity = Short.parseShort(itemInfo[3]);

                DealerItem dealerItem = new DealerItem(itemName, itemBrand, itemPrice, itemQuantity);
                dealerItems[j] = dealerItem;
            }
            Dealer dealer = new Dealer(name, phoneNumber, location, dealerItems);
            dealersList.add(dealer);
        }
        return dealersList;
    }

    /// method to read the Dealers.txt file and store the data in a string list
    private List<String> readDealersFile() {
        List<String> dealersWithItems = new ArrayList<>();
            try {
                File dealersFile = new File("Dealers.txt");
                Scanner scanner = new Scanner(dealersFile);
                while (scanner.hasNextLine()) {
                    dealersWithItems.add(scanner.nextLine());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                showAlert("File Not Found Error", "Dealers.txt file was not found. A default file will be created.");
                File itemsFile = new File("Dealers.txt");
                try {
                    FileWriter itemsWriter = new FileWriter(itemsFile);     // default data when file is not found
                    String defaultData = "Dilshan,0777345123,Colombo\n" +
                            "Cereal,Kelloggs,950.00,12\n" +
                            "Orange Juice,Tropicana,450.00,20\n" +
                            "Cheese,Anchor,1200.00,15\n" +
                            "Jayawardana,0777123345,Galle\n" +
                            "Toothpaste,Signal,180.00,30\n" +
                            "Dishwash,Vim,350.00,25\n" +
                            "Chocolate,KitKat,220.00,50\n" +
                            "Nimal,0789123456,Bampalapitiya\n" +
                            "Shampoo,Sunsilk,550.00,18\n" +
                            "Biscuits,Munchee,300.00,40\n" +
                            "Salt,MD,100.00,60\n" +
                            "Kevinesh,0779871234,Ragama\n" +
                            "Laundry Powder,Ariel,1150.00,12\n" +
                            "Yogurt,Anchor,250.00,25\n" +
                            "Peanut Butter,MD,700.00,10\n" +
                            "Neeshan,0776788760,Wattala\n" +
                            "Instant Noodles,Maggi,120.00,35\n" +
                            "Juice Box,Ribena,160.00,40\n" +
                            "Tissues,Softy,300.00,30\n" +
                            "Lasith,0779991111,Negombo\n" +
                            "Soap,Lifebuoy,140.00,45\n" +
                            "Jam,MD,380.00,20\n" +
                            "HandWash,Dettol,600.00,15\n";
                    itemsWriter.write(defaultData);
                    itemsWriter.close();
                } catch (IOException er) {
                    showAlert("Writing Error", er.getMessage());
                }
            }
            return dealersWithItems;
        }

    /// method to show error messages to the user
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}