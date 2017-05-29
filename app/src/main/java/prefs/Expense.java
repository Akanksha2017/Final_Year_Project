package prefs;

/**
 * Created by TheSassyGourmet on 5/28/2017.
 */

public class Expense {

    private int id;
    private String date;
    private String description;
    private float price;

    public Expense() {
        super();
    }

    public Expense(int id, String date, String description, float price) {
        super();
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /*@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }*/

    @Override
    public String toString() {
        return "Expense [id=" + id + ", date=" + date + ", description="
                + description + ", price=" + price + "]";
    }
}
