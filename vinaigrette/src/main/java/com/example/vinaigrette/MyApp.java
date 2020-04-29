package com.example.vinaigrette;

import android.app.Application;
import android.content.Context;

import com.dpcsa.compon.db.DatabaseManager;
import com.dpcsa.compon.interfaces_classes.ParamDB;
import com.dpcsa.compon.single.DeclareParam;

public class MyApp extends Application {

    private static MyApp instance;
    private Context context;

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

        ParamDB paramDB = new ParamDB(SQL.DB_NAME, 5);
        paramDB.addTable(SQL.PRODUCT_ORDER, SQL.PRODUCT_ORDER_FIELDS, SQL.PRODUCT_ORDER_INDEX_NAME, SQL.PRODUCT_ORDER_INDEX_COLUMN);
        paramDB.addTable(SQL.ORDER_TAB, SQL.ORDER_FIELDS);

        DeclareParam.build(context)
                .setAppParams(new MyParams())
                .setDeclareScreens(new MyDeclareScreens())
                .setDB(new DatabaseManager(context, paramDB));
    }
}
