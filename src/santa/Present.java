package santa;

public class Present implements Comparable<Present> {
    private String productName;
    private Double price;
    private String category;

    public Present(final String productName, final Double price,
                   final String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public final String getProductName() {
        return productName;
    }

    public final Double getPrice() {
        return price;
    }

    public final String getCategory() {
        return category;
    }

    /**
     * method that compares the price of the presents
     * and so implements the method from the Comparable interface
     * @param o other present that we compare to
     * @return 1 - if this > o / -1 this < o 0 - this = o
     */
    @Override
    public int compareTo(final Present o) {
        return Double.compare(price, o.price);
    }

    @Override
    public final String toString() {
        return "Present{"
                + "productName='" + productName + '\''
                + ", price=" + price
                + ", category='" + category + '\''
                + '}';
    }
}
