package app.infosys.volleydemoapp;

import java.util.ArrayList;
import java.util.List;

import app.infosys.volleydemoapp.Models.UserModel;


public class UserRvData {
    public static int ExpenseArraySize;
    public static String[] AccountNameDummy;
    public static String[] PhoneDummy;


    public UserRvData(int i){

        AccountNameDummy = new String[i];
        PhoneDummy = new String[i];

        ExpenseArraySize=i;

    }

    public static List<UserModel> getListData(){
        List<UserModel> data = new ArrayList<>();
        for (int i = 0; i < (ExpenseArraySize); i++) {
            UserModel DataMdl = new UserModel();
            DataMdl.setName(AccountNameDummy[i]);
            DataMdl.setPhone(PhoneDummy[i]);

            data.add(DataMdl);
        }
        return data;
    }
}

