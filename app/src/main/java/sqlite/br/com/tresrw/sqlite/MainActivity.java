package sqlite.br.com.tresrw.sqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criar Banco de Dados SQLite
        // Variável bancoDados do tipo SQLiteDataBase =
        SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);


        // Testes de sincronização do Android Studio com o GitHub




    }
}
