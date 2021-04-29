package example.com.finance.models;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

// Model de despesa
public class Expense extends RealmObject{
    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    @Required
    private String description;
    @Required
    private String category;
    private double value;
    private int day, month, year;
    private boolean consolidated;


    public Expense(){

    }

    public void save(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(this);

        realm.commitTransaction();
        realm.close();
    }

    public Expense(String id, String description, String category, double value, int day, int month, int year, boolean consolidated) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.value = value;
        this.day = day;
        this.month = month;
        this.year = year;
        this.consolidated = consolidated;
    }


    public Expense(String description, String category, double value, int day, int month, int year, boolean consolidated) {
        this.description = description;
        this.category = category;
        this.value = value;
        this.day = day;
        this.month = month;
        this.year = year;
        this.consolidated = consolidated;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isConsolidated() {
        return consolidated;
    }

    public void setConsolidated(boolean consolidated) {
        this.consolidated = consolidated;
    }

}
