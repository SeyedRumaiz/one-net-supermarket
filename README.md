# OneNet Supermarket

A JavaFX desktop application for managing supermarket inventory and dealer information. This project demonstrates a small JavaFX app with file-based persistence, Java object modelling, validation logic, and navigation across multiple screens.

## Key Features

- Add, view, update, and delete supermarket items
- Display low-stock items in the main dashboard
- Sort and view inventory items by category and item code
- Select four random dealers from predefined dealer data
- View selected dealer details and inspect the items each dealer offers
- Validation for item fields, numeric values, dates, and image selection

## Architecture Overview

The app is organized into the following logical layers:

- **Presentation / UI**
  - FXML views define the layout for screens such as `MainMenu`, `AddItem`, `ViewItems`, `DeleteItem`, `UpdateItem`, and dealer-related pages.
  - Controllers manage UI events and navigation.

- **Scene Management**
  - `SceneManager` provides a shared `openScene()` method for switching JavaFX scenes with a consistent stylesheet and window size.

- **Domain Model**
  - `Item` represents a supermarket product.
  - `Dealer` represents a supplier and includes an array of `DealerItem` objects.
  - `DealerItem` represents a product supplied by a dealer.

- **Persistence and Business Logic**
  - `ItemFileHandler` handles reading and writing `Items.txt`, item sorting, low-stock detection, and inventory calculations.
  - `DealersFileHandler` handles reading `Dealers.txt` and selecting / sorting random dealers.

- **Validation**
  - `Validation` is a static utility class that validates user input before saving data.

## Important Classes

- `com.example.coursework.Main`
  - Entry point for JavaFX application
  - Loads the main menu and initializes item and dealer data from text files

- `com.example.coursework.SceneManager`
  - Abstract base class used by controllers for scene switching

- `com.example.coursework.ItemFileHandler`
  - Manages item persistence and business operations

- `com.example.coursework.DealersFileHandler`
  - Manages dealer data and random dealer selection

- `com.example.coursework.Validation`
  - Validates form input and returns user-facing messages

## Data Files

The project uses two text data files:

- `Items.txt`
  - Each line contains: `itemCode,itemName,itemBrand,itemPrice,itemQuantity,itemCategory,itemPurchasedDate,itemPath,minQuantity`
  - Example:
    `1,Milk,Anchor,400.00,23,Dairy,12-06-2025,src/main/resources/images/anchormilk.jpeg,50`

- `Dealers.txt`
  - Stored in groups of four lines per dealer:
    - Dealer line: `name,phone,location`
    - Next three lines: dealer item records
  - Example:
    ```
    Dilshan,0777345123,Colombo
    Cereal,Kelloggs,950.00,12
    Orange Juice,Tropicana,450.00,20
    Cheese,Anchor,1200.00,15
    ```

## Run Instructions

### From an IDE

1. Import the project as a Maven project.
2. Ensure Java 11 or later is configured.
3. Run `com.example.coursework.Main`.

### From Maven

The project uses JavaFX and a module definition in `module-info.java`.

If your environment is configured correctly for JavaFX, run:

```bash
mvn test
```

To launch the JavaFX application from an IDE, run the `Main` class.

## Tests

JUnit tests are available for:

- `Validation` logic (`ValidationTest`)
- `ItemFileHandler` operations (`ItemFileHandlerTest`)
- `DealersFileHandler` dealer loading and random selection (`DealersFileHandlerTest`)

Run tests with:

```bash
mvn test
```

## Possible Improvements

- Replace text file persistence with a structured database or JSON storage.
- Refactor shared alert/dialog code to a utility class.

## Project Structure

- `src/main/java/com/example/coursework/` — application code
- `src/main/resources/com/example/coursework/` — FXML views
- `src/main/resources/images/` — image assets
- `src/test/java/com/example/coursework/` — unit tests

---