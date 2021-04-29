package example.com.finance.controllers;

import io.realm.Realm;
import example.com.finance.models.CreditCard;

public class CreditCardController {
    public static void create(String flag, String owner,double limit){
        CreditCard cc = new CreditCard(flag,owner,limit,limit);
        cc.save();
    }

public static CreditCard get(){
    CreditCard cc;

    Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();

    CreditCard ccTemp = realm.where(CreditCard.class).findFirst();

    if(ccTemp != null){

        cc = new CreditCard(ccTemp.getFlag(),ccTemp.getOwner(),ccTemp.getLimit(),ccTemp.getCurrentLimit());

    }else{
        cc = null;
    }


    realm.commitTransaction();
    realm.close();

    return cc;
}

    public static void changeLimit(double value){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CreditCard cc = realm.where(CreditCard.class).findFirst();
        cc.setCurrentLimit(cc.getCurrentLimit()-value);

        realm.commitTransaction();
        realm.close();
    }

    public static void resetLimit(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CreditCard cc = realm.where(CreditCard.class).findFirst();
        cc.setCurrentLimit(cc.getLimit());

        realm.commitTransaction();
        realm.close();
    }

}
