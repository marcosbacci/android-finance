package example.com.finance.controllers;

import io.realm.Realm;
import example.com.finance.models.Bank;

public class BankController {
    public static void create(String owner, double balance,String name, String agency){
        Bank newBank = new Bank(owner,balance,name,agency);
        newBank.save();
    }

    public static Bank get(){
        Bank info;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bank tmpBank = realm.where(Bank.class).findFirst();

        if(tmpBank != null){
            info = new Bank(tmpBank.getOwner(),tmpBank.getBalance(), tmpBank.getName(),tmpBank.getAgency());
        }else{
            info = null;
        }

        realm.commitTransaction();
        realm.close();

        return info;
    }

    public static double getBalance(){
        Bank bank = get();
        if(bank != null){
            return bank.getBalance();
        }else{
            return 0.0;
        }
    }

    public static void deposit(double value){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bank myBank = realm.where(Bank.class).findFirst();
        myBank.setBalance(myBank.getBalance()+value);

        realm.commitTransaction();
        realm.close();
    }

    public static void withdraw(double value){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Bank myBank = realm.where(Bank.class).findFirst();
        myBank.setBalance(myBank.getBalance()-value);

        realm.commitTransaction();
        realm.close();
    }
}
