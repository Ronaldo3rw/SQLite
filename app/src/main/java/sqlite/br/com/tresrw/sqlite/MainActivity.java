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

            /**  Criar Banco de Dados SQLite **/
            // Variável bancoDados do tipo SQLiteDataBase -> utilizando o método openOrCreateDatabase
            // Passando os parâmetros
                // 1º Paramêtro => app -> ? nome do aplicativo (acho)
                // 2º Parãmetro => MODE_PRIVATE -> definindo o Modo -> somente acessível por esse aplicativo
                // 3º Parâmetro => Não será utilizado nesta aula
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            /** Apagando a tabela pessoas DROP TABLE **/
            /* bancoDados.execSQL("DROP TABLE pessoas"); */

            /** Criar tabela **/
            /** CREATE **/
            // método execSQL executa comandos SQL
            // CREATE TABLE IF NOT EXISTS -> Cria a tabela caso ela não exista
            // pessoas (nome VARCHAR, idade INT(3) -> Tabela pessoas com as colunas Nome do tipo Texto e idade do tipo inteiro definindo o nº de casas
            /* bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT(3))"); */

            /** Criando uma tabela com o código para cada registro **/
            // Cria a tabela pessoas com o código id do tipo INTEGER, definindo como Chave primária (PRIMARY KEY) e auto incremento (AUTOINCREMENT)
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INTEGER(3))");

            /** INSERT **/
            // Inserir dados na tabela
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Sergio', 18) ");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Fernando', 40) ");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Ronaldo', 16) ");

            /** Inserindo **/
            /*
            bancoDados.execSQL("INSERT INTO pessoas * VALUES ('Mariana', 40) ");
            bancoDados.execSQL("INSERT INTO pessoas * VALUES ('Ronaldo', 16) ");
            */


            /** UPDATE **/
            // Atualize a tabela pessoas setando(SET) a idade do registro 'Ronaldo' para 28 anos
            /* bancoDados.execSQL("UPDATE pessoas SET idade = 28 WHERE nome = 'Ronaldo' "); */

            // TESTE
            /* bancoDados.execSQL("UPDATE pessoas SET idade = 41 WHERE nome = 'Ronaldo' "); */
            // Atualiza na tabela pessoas o registro 'Ronaldo' para o SET 'Ronaldo Cagliari'
            /* bancoDados.execSQL("UPDATE pessoas SET nome = 'Ronaldo Cagliari' WHERE nome = 'Ronaldo' "); */

            /** DELETE **/
            // DELETE da tabela pessoas o registro de nome 'Karol'
            /* bancoDados.execSQL("DELETE FROM pessoas WHERE nome = 'Karol' "); */


            /** SELECT - FROM - WHERE e AND**/
            // Recuperar os dados
            // Utilizando o cursos podemos percorrer item a item
            /* Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null); */

            /** Selecionando nomes com o id utilizando o *(asterisco), quer dizer todos **/
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas",null);

            // Usando condições para utilizar o SELECT
            // Inserindo clausula WHERE

            // Busca no banco os registros cuja a idade é maior que 30 anos
            /* Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE idade > 30", null); */

            // Busca no banco os registros cuja a idade é maior que 20 anos
            /* Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE idade > 20", null);*/

            // Inserindo mais clausulas -> AND
            // Buscando um registro que a idade é maior que 20 e(AND) o nome Ronaldo
            /* Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE idade > 20 AND nome  = 'Ronaldo' ", null); */

            // Inserindo mais clausulas -> LIKE
            // Buscando um registro que o nome contem os itens pesquisados. Ex: inicia com 'ro' e pode conter qualquer valor(%) a direita
            /* Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE nome LIKE 'no%' ", null); */

            // Ex2: Busca os valores que contem 'na' e pode conter qualquer valor(%) a direita ou a esquerda do 'na'
            /* Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE nome LIKE '%na%' ", null); */




            // Recuperar os indices da tabela
            // Cria a variável para receber o indice
            // getColumnIndex -> recupera o indice da coluna em questão
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");
            int indiceColunaId = cursor.getColumnIndex("id");


            // retorna o cursor para o primeiro item
            cursor.moveToFirst();

            // testar: Enquanto o cursor for != (diferente) de null (vazio) temos dados para serem exibidos
            while (cursor != null){

                // Exibindo no log os dados buscando pelo indice
                Log.i("RESULTADO - Nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - Idade: ", cursor.getString(indiceColunaIdade));
                Log.i("RESULTADO - ID: ", cursor.getString(indiceColunaId));


                // se não colocar moveToNext o cursor nunca será nulo
                cursor.moveToNext();

            }
        }catch(Exception e){
            // Printa o erro
            e.printStackTrace();
        }
    }
}
