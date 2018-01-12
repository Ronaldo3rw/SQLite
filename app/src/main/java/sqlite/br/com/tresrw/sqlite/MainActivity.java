package sqlite.br.com.tresrw.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Prevenindo erros com o Try/Catch
        // try -> se der tudo certo
        // catch -> Caso não seja executado por algum motivo ele exibe (printStackTrace) o erro

        try{

            // Criar Banco de Dados SQLite
            // Variável bancoDados do tipo SQLiteDataBase -> utilizando o método openOrCreateDatabase
            // Passando os parâmetros
                // 1º Paramêtro => app -> ? nome do aplicativo (acho)
                // 2º Parãmetro => MODE_PRIVATE -> definindo o Modo -> somente acessível por esse aplicativo
                // 3º Parâmetro => Não será utilizado nesta aula
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);


            // Criar tabela
            // método execSQL executa comandos SQL
            // CREATE TABLE IF NOT EXISTS -> Cria a tabela caso ela não exista
            // pessoas (nome VARCHAR, idade INT(3) -> Tabela pessoas com as colunas Nome do tipo Texto e idade do tipo inteiro definindo o nº de casas
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT(3))");

            // Inserir dados na tabela
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Marcos', 30) ");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Ronaldo', 40) ");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Renata', 20) ");


            // Recuperar os dados
            // Utilizando o cursos podemos percorrer item a item
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null);

            // Recuperar os indices da tabela
            // Cria a variável para receber o indice
            // getColumnIndex -> recupera o indice da coluna em questão
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            // retorna o cursor para o primeiro item
            cursor.moveToFirst();

            // testar: Enquanto o cursor for != (diferente) de null (vazio) temos dados para serem exibidos
            while (cursor != null){

                // Exibindo no log os dados buscando pelo indice
                Log.i("RESULTADO - Nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - Idade: ", cursor.getString(indiceColunaIdade));

                // se não colocar moveToNext o cursor nunca será nulo
                cursor.moveToNext();

            }
        }catch(Exception e){
            // Printa o erro
            e.printStackTrace();
        }
    }
}
