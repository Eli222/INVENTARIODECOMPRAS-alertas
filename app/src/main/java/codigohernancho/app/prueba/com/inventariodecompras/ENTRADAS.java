package codigohernancho.app.prueba.com.inventariodecompras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ENTRADAS extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText edit_Producto_ID;
    EditText edit_Producto_Actual;
    EditText edit_EXP_Ano;
    EditText edit_EXP_Mes;
    EditText edit_EXP_Dia;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        myDB = new DatabaseHelper(this);

        edit_Producto_ID = (EditText) findViewById(R.id.editText_Producto_ID);
        edit_Producto_Actual = (EditText) findViewById(R.id.editText_Producto_Actual);
        edit_EXP_Ano = (EditText) findViewById(R.id.editText_EXP_Ano);
        edit_EXP_Mes = (EditText) findViewById(R.id.editText_EXP_Mes);
        edit_EXP_Dia = (EditText) findViewById(R.id.editText_EXP_Dia);
        btnAddData = (Button) findViewById(R.id.button_Insert_Data);
        AddData();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData3(
                                Integer.parseInt(edit_Producto_ID.getText().toString()),
                                Integer.parseInt(edit_Producto_Actual.getText().toString()),
                                Integer.parseInt(edit_EXP_Ano.getText().toString()),
                                Integer.parseInt(edit_EXP_Mes.getText().toString()),
                                Integer.parseInt(edit_EXP_Dia.getText().toString()));
                        if(isInserted == true)
                            Toast.makeText(ENTRADAS.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ENTRADAS.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
