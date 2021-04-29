package example.com.finance.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import example.com.finance.helpers.ConvertDate;
import example.com.finance.models.Bill;

public class BillController {

    public static void createNew(String description, double value, String date){
        Calendar cDate = ConvertDate.stringToDate(date);
        Bill bill = new Bill(description,cDate.get(Calendar.DAY_OF_MONTH),cDate.get(Calendar.MONTH)+1,cDate.get(Calendar.YEAR), value,false);
        bill.save();
    }

    public static List<Bill> getAll(){
        List<Bill> billList = new ArrayList<Bill>();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Bill> tempList = realm.where(Bill.class).equalTo("payed",false).findAll();

        for(int i = 0; i < tempList.size();i++){
            Bill tempBill = new Bill(
                    tempList.get(i).getId(),
                    tempList.get(i).getDescription(),
                    tempList.get(i).getDay(),
                    tempList.get(i).getMonth(),
                    tempList.get(i).getYear(),
                    tempList.get(i).getValue(),
                    tempList.get(i).isPayed()
            );
            billList.add(tempBill);
        }

        realm.commitTransaction();
        realm.close();

        return billList;
    }

    public static void setPayed(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bill b = realm.where(Bill.class).equalTo("id",id).findFirst();
        b.setPayed(true);

        realm.commitTransaction();
        realm.close();
    }
}
