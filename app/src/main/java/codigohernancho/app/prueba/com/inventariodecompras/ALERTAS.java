package codigohernancho.app.prueba.com.inventariodecompras;
// codigo Elianeth Alertas
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ALERTAS extends AppCompatActivity{
    DatabaseHelper myDB;
    EditText editProducto_ID;
    EditText editStock_Minimo;
    Button btnAddAlertaData;
    Button btnCheckAlertas;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);
        myDB = new DatabaseHelper(this);

        editProducto_ID = (EditText) findViewById(R.id.editText_Producto_ID);
       // editStock_Minimo = (EditText) findViewById(R.id.editText_Stock_Minimo);
       // btnAddAlertaData = (Button) findViewById(R.id.button_Insert_Test_Data);
        btnCheckAlertas = (Button) findViewById(R.id.button_Vigilar_Alertas);
       // AddAlertaData();
        getTable_Data();
    }

    //for testing database
    //public  void AddAlertaData() {
      //  btnAddAlertaData.setOnClickListener(
        //    new View.OnClickListener() {
          //      @Override
            //     public void onClick(View v) {
              //          boolean isInserted = myDB.insertData2(
                //                Integer.parseInt(editProducto_ID.getText().toString()),
                  //              Integer.parseInt(editStock_Minimo.getText().toString()),
                    //            4,5,6,7);
          //              if(isInserted == true)
          //                Toast.makeText(ALERTAS.this,"Data Inserted",Toast.LENGTH_LONG).show();
          //              else
           //               Toast.makeText(ALERTAS.this,"Data not Inserted",Toast.LENGTH_LONG).show();
           //        }
          //      }
      //  );
    //}
    // coloca Datos de la "Lista_Crear" Tabla y "Inventario" Tabla into Cursor Class Objects
    public void getTable_Data() {
        btnCheckAlertas.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res_Crear = myDB.getTable1_Data();
                        Cursor res_Inventorio = myDB.getTable2_Data();
                        if(res_Inventorio.getCount() == 0) {
                            // show message
                            Toast.makeText(ALERTAS.this,"Error Nothing Found",Toast.LENGTH_LONG).show();
                            return;
                        }

                        StringBuffer buffer_Low_Stock = new StringBuffer();
                        StringBuffer buffer_Expiration = new StringBuffer();
                        Calendar Current_Date = Calendar.getInstance();
                        Calendar Producto_Expiration = Calendar.getInstance();
                        Calendar Producto_Expiration_Alerta = Calendar.getInstance();
                        while (res_Inventorio.moveToNext()) {
                            int Producto_Lookup = (res_Inventorio.getInt(1));
                            res_Crear.moveToFirst();
                            while (res_Crear.getInt(0) != Producto_Lookup) {
                                res_Crear.moveToNext();
                            }
                            // check por miimo stock
                            if (res_Inventorio.getInt(2)<= (res_Crear.getInt(5))) {
                                buffer_Low_Stock.append("Producto_ID :" + res_Crear.getString(0) + "\n");
                                buffer_Low_Stock.append("Nombre :" + res_Crear.getString(2) + "\n\n");
                            }

                            // check por producto vencimiento alerta, note:Java mes January=0
                            Producto_Expiration.set(res_Inventorio.getInt(3), (res_Inventorio.getInt(4) -1), res_Inventorio.getInt(5));
                            Producto_Expiration_Alerta = Producto_Expiration;
                            Producto_Expiration_Alerta.add(Calendar.DATE, - (res_Crear.getInt(10)));

                            if ((Current_Date.compareTo(Producto_Expiration_Alerta)) > 0 ) {
                                buffer_Expiration.append("Producto_ID :" + res_Crear.getString(0) + "\n");
                                buffer_Expiration.append("Nombre :" + res_Crear.getString(2) + "\n\n");

                            }
                        }
                         // muestra todos los datos
                        showMessage("Low Stock",buffer_Low_Stock.toString());
                        showMessage("Expiration",buffer_Expiration.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}