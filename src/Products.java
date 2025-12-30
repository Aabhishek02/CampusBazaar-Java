public class Products {
    String title;
    double price;
    String category;
    String seller;

    Products(String title, double price, String category, String seller){
        this.title = title;
        this.price = price;
        this.category = category;
        this.seller = seller;
    }

    @Override
    public String toString(){
        return title + "," + price + "," + category + "," + seller;
    }
}
