package JsonJava;

public class CustomerDetails {
    private String courseName;
    private String purchaseDate;
    private int amount;
    private String location;

    public CustomerDetails() {
    }

    public CustomerDetails(String courseName, String purchaseDate, int amount, String location) {
        this.courseName = courseName;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.location = location;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
