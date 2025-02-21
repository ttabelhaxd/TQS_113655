package tqs;

public class Product {
    private int id;
    private String image;
    private String description;
    private double price;
    private String title;
    private String category;

    public Product(int id, String image, String description, double price, String title, String category) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.price = price;
        this.title = title;
        this.category = category;
    }

    public Product() {

    }

    public int getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategory() {
        return this.category;
    }
}
