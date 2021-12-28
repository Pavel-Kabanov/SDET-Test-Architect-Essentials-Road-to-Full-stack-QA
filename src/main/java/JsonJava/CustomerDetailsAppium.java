package JsonJava;

public class CustomerDetailsAppium {
    private String courseName;
    private String purchaseDate;
    private int amount;
    private String location;
    private String studentName;

    public CustomerDetailsAppium() {
    }

    public CustomerDetailsAppium(String courseName, String purchaseDate, int amount, String location) {
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
