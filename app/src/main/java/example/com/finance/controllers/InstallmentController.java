package example.com.finance.controllers;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import example.com.finance.models.Installment;

public class InstallmentController {

    public static void createNew(String description, double value, int amount){
        Installment ist = new Installment(description,value,amount,0,false);
        ist.save();
    }

    public static List<Installment> getAll(){
        List<Installment> defList = new ArrayList<Installment>();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Installment> tempList = realm.where(Installment.class).equalTo("finished",false).findAll();

        for(int i = 0; i < tempList.size();i++){
            Installment tempIns = new Installment(
                    tempList.get(i).getId(),
                    tempList.get(i).getDescription(),
                    tempList.get(i).getValue(),
                    tempList.get(i).getTotalPart(),
                    tempList.get(i).getCurrentPart(),
                    tempList.get(i).isFinished()
            );
            defList.add(tempIns);
        }

        realm.commitTransaction();
        realm.close();

        return defList;
    }

    public static boolean incrementInstallment(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Installment iTemp = realm.where(Installment.class).equalTo("id",id).findFirst();
        int actual = iTemp.getCurrentPart();

        // na ultima parcela
        if(actual+1 >= iTemp.getTotalPart()){
            iTemp.setCurrentPart((iTemp.getCurrentPart()+1));
            iTemp.setFinished(true);
            realm.commitTransaction();
            realm.close();
            return true;
          // so incrementar
        }else{
            iTemp.setCurrentPart((iTemp.getCurrentPart()+1));
            realm.commitTransaction();
            realm.close();
            return false;
        }

    }
}
