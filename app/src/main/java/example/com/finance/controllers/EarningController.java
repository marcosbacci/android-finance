package example.com.finance.controllers;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;
import example.com.finance.helpers.ConvertDate;
import example.com.finance.models.Earning;

public class EarningController {
    public static void newEarning(float value, String description, String dateTEXT, String category, boolean consolidated){
        Calendar date = ConvertDate.stringToDate(dateTEXT);
        Earning earning = new Earning(description,category,value,date.get(Calendar.DAY_OF_MONTH),date.get(Calendar.MONTH) + 1,
                date.get(Calendar.YEAR), consolidated
        );
        earning.save();
    }

    public static float getMonthTotal(){
        Calendar date = Calendar.getInstance();

        int month = date.get(Calendar.MONTH) + 1;
        int year = date.get(Calendar.YEAR);
        float total = 0.0f;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Earning> earnings = realm.where(Earning.class).equalTo("month",month).
                equalTo("year",year).
                equalTo("consolidated",true).findAll();

        for(int i = 0; i < earnings.size();i++){
            total += earnings.get(i).getValue();
        }

        realm.commitTransaction();
        realm.close();

        return  total;
    }


    public static float getMonthEarning(int month){
        Calendar date = Calendar.getInstance();

        int year = date.get(Calendar.YEAR);
        float total = 0.0f;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Earning> earnings = realm.where(Earning.class).equalTo("month",month).
                equalTo("year",year).
                equalTo("consolidated",true).findAll();

        for(int i = 0; i < earnings.size();i++){
            total += earnings.get(i).getValue();
        }

        realm.commitTransaction();
        realm.close();

        return  total;
    }


}
