package example.com.finance.helpers;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConvertDate {

    public static Calendar stringToDate(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        try{
            c.setTime(sdf.parse(strDate));
        }catch (ParseException e){
            Log.d("Finance",e.getMessage());
        }


        return c;
    }
}
