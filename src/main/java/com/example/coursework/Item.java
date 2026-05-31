package com.example.coursework;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Item {
    private final int ITEM_CODE;
    private String itemName;
    private String itemBrand;
    private double itemPrice;
    private short itemQuantity;     // assuming quantity isn't very large/ >// 32000
    private String itemCategory;
    private final String ITEM_PURCHASED_DATE;
    private String itemPath;
    private final byte MIN_QUANTITY;
    private ImageView itemImageView;

    public Item(int ITEM_CODE, String itemName, String itemBrand, double itemPrice, short itemQuantity, String itemCategory, String ITEM_PURCHASED_DATE, String itemPath, byte MIN_QUANTITY) {
        this.ITEM_CODE = ITEM_CODE;
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemCategory = itemCategory;
        this.ITEM_PURCHASED_DATE = ITEM_PURCHASED_DATE;
        this.itemPath = itemPath;
        this.MIN_QUANTITY = MIN_QUANTITY;

        try {
            Image img = new Image(new FileInputStream(itemPath), 200, 130, true, true);
            this.itemImageView = new ImageView(img);
        } catch (FileNotFoundException e) {
            Image defaultImg = new Image(Objects.requireNonNull(Item.class.getResourceAsStream("/images/defaultimg.jpg")), 200, 130, true, true);
            this.itemImageView = new ImageView(defaultImg);         // a default image is given if there is no image found
        }
    }

    public int getItemCode() {return ITEM_CODE;}
    public String getItemName() {return itemName;}
    public String getItemBrand() {return itemBrand;}
    public String getItemPath() {return itemPath;}
    public double getItemPrice() {return itemPrice;}
    public short getItemQuantity() {return itemQuantity;}
    public String getItemCategory() {return itemCategory;}
    public String getItemPurchasedDate() {return ITEM_PURCHASED_DATE;}
    public byte getMinQuantity() {return MIN_QUANTITY;}
    public ImageView getItemImageView() {return itemImageView;}


    public void setItemName(String itemName) {this.itemName= itemName;}
    public void setItemBrand(String itemBrand) {this.itemBrand = itemBrand;}
    public void setItemPath(String itemPath) {this.itemPath = itemPath;}
    public void setItemPrice(double itemPrice) {this.itemPrice = itemPrice;}
    public void setItemQuantity(short itemQuantity) {this.itemQuantity = itemQuantity;}
    public void setItemCategory(String itemCategory) {this.itemCategory = itemCategory;}

    @Override
    public String toString() {
        return ITEM_CODE +  "," + itemName + "," + itemBrand + "," + itemPrice + "," + itemQuantity
                + "," + itemCategory + "," + ITEM_PURCHASED_DATE
                + "," + itemPath + "," + MIN_QUANTITY;
    }
}