package codigohernancho.app.prueba.com.inventariodecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Inventoriocompras.db";
    public static final String TABLE1_NAME = "List_Crear";
    public static final String T1_COL_1 = "Producto_ID";
    public static final String T1_COL_2 = "SKU";
    public static final String T1_COL_3 = "Nombre";
    public static final String T1_COL_4 = "Marca";
    public static final String T1_COL_5 = "Unidad";
    // public static final String T1_COL_6 = "Foto";
    public static final String T1_COL_6 = "Alerta_Min_Stock_SP";
    public static final String T1_COL_7 = "Alerta_Max_Stock_SP";
    public static final String T1_COL_8 = "Alerta_Low_Consump_Quantity_SP";
    public static final String T1_COL_9 = "Alerta_Low_Consump_Time_Dias_SP";
    public static final String T1_COL_10 = "Alerta_Inactivity_Time_Dias_SP";
    public static final String T1_COL_11 = "Alerta_Expiration_Dias_Before_SP";
    public static final String T1_COL_12 = "Monitor_Alertas";

    public static final String TABLE2_NAME = "Inventorio";
    public static final String T2_COL_1 = "Inventorio_ID";
    public static final String T2_COL_2 = "Producto_ID";
    public static final String T2_COL_3 = "Producto_Actual";
    public static final String T2_COL_4 = "Exp_Ano";
    public static final String T2_COL_5 = "Exp_Mes";
    public static final String T2_COL_6 = "Exp_Dia";

    public static final String TABLE3_NAME = "Alertas";
    public static final String T3_COL_1 = "Alerta_ID";
    public static final String T3_COL_2 = "Producto_ID";
    public static final String T3_COL_3 = "Stock_Minimo";
    public static final String T3_COL_4 = "Stock_Maximo";
    public static final String T3_COL_5 = "Menor_Consumo";
    public static final String T3_COL_6 = "Inactividad";
    public static final String T3_COL_7 = "Vencimiento";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1_NAME + " (Producto_ID INTEGER PRIMARY KEY AUTOINCREMENT, SKU INTEGER, Nombre TEXT, Marca TEXT, Unidad TEXT, Alerta_Min_Stock_SP INTEGER, Alerta_Max_Stock_SP INTEGER, Alerta_Low_Consump_Quantity_SP INTEGER, Alerta_Low_Consump_Time_Dias_SP INTEGER, Alerta_Inactivity_Time_Dias_SP INTEGER, Alerta_Expiration_Dias_Before_SP INTEGER, Monitor_Alertas INTEGER)");
        db.execSQL("create table " + TABLE2_NAME + " (Inventorio_ID INTEGER PRIMARY KEY AUTOINCREMENT, Producto_ID INTEGER  REFERENCES \"List_Crear\" (\"Producto_ID\"), Producto_Actual INTEGER, EXP_Ano INTEGER, EXP_Mes INTEGER, EXP_Dia INTEGER)");
        db.execSQL("create table " + TABLE3_NAME + " (Alerta_ID INTEGER PRIMARY KEY AUTOINCREMENT, Producto_ID INTEGER  REFERENCES \"List_Crear\" (\"Producto_ID\"), Stock_Minimo TINYINT, Stock_Maximo TINYINT, Menor_Consumo TINYINT, Inactividad TINYINT, Vencimiento TINYINT)");
    }
// Elianeth crear, leer, borrar ,datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE3_NAME);
        onCreate(db);
    }

    public boolean insertData(Integer SKU, String Nombre, String Marca, String Unidad, Integer Alerta_Min_Stock_SP, Integer Alerta_Max_Stock_SP, Integer Alerta_Low_Consump_Quantity_SP, Integer Alerta_Low_Consump_Time_Dias_SP, Integer Alerta_Inactivity_Time_Dias_SP, Integer Alerta_Expiration_Dias_Before_SP, Integer Monitor_Alertas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1_COL_2, SKU);
        contentValues.put(T1_COL_3, Nombre);
        contentValues.put(T1_COL_4, Marca);
        contentValues.put(T1_COL_5, Unidad);
        // contentValues.put(T1_COL_5,Foto);
        contentValues.put(T1_COL_6, Alerta_Min_Stock_SP);
        contentValues.put(T1_COL_7, Alerta_Max_Stock_SP);
        contentValues.put(T1_COL_8, Alerta_Low_Consump_Quantity_SP);
        contentValues.put(T1_COL_9, Alerta_Low_Consump_Time_Dias_SP);
        contentValues.put(T1_COL_10, Alerta_Inactivity_Time_Dias_SP);
        contentValues.put(T1_COL_11, Alerta_Expiration_Dias_Before_SP);
        contentValues.put(T1_COL_12, Monitor_Alertas);
        long result = db.insert(TABLE1_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
        }

    public boolean insertData2(Integer Producto_ID, Integer Stock_Minimo, Integer Stock_Maximo, Integer Menor_Consumo, Integer Inactividad, Integer Vencimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T3_COL_2,Producto_ID);
        contentValues.put(T3_COL_3,Stock_Minimo);
        contentValues.put(T3_COL_4,Stock_Maximo);
        contentValues.put(T3_COL_5,Menor_Consumo);
        contentValues.put(T3_COL_6,Inactividad);
        contentValues.put(T3_COL_7,Vencimiento);
        long result = db.insert(TABLE3_NAME, null ,contentValues);
        if(result == -1)
            return false;
         else
            return true;
        }

    public boolean insertData3(Integer Producto_ID, Integer Producto_Actual, Integer Exp_Ano, Integer Exp_Mes, Integer Exp_Dia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T2_COL_2,Producto_ID);
        contentValues.put(T2_COL_3,Producto_Actual);
        contentValues.put(T2_COL_4,Exp_Ano);
        contentValues.put(T2_COL_5,Exp_Mes);
        contentValues.put(T2_COL_6,Exp_Dia);
        long result = db.insert(TABLE2_NAME, null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getTable1_Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result_table1 = db.rawQuery("select * from "+TABLE1_NAME,null);
        return result_table1;
    }

    public Cursor getTable2_Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result_table2 = db.rawQuery("select * from "+TABLE2_NAME,null);
        return result_table2;
    }

}

