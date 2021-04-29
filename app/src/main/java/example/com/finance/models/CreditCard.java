package example.com.finance.models;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class CreditCard extends RealmObject{
    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    private String flag;
    private String owner;
    private double limit;
    private double currentLimit;

    public void save(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(this);

        realm.commitTransaction();
        realm.close();
    }

    public CreditCard(){

    }


    public CreditCard(String flag, String owner, double limit, double currentLimit) {
        this.flag = flag;
        this.owner = owner;
        this.limit = limit;
        this.currentLimit = currentLimit;
    }

    public double getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(double currentLimit) {
        this.currentLimit = currentLimit;
    }

    public String getId() {
        return id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
