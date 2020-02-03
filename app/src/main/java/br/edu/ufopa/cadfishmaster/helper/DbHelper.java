package br.edu.ufopa.cadfishmaster.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import androidx.annotation.Nullable;

import br.edu.ufopa.cadfishmaster.model.Usuario;

public class DbHelper extends SQLiteOpenHelper {

    public static  int VERSION = 1;
    public static String NOME_DB = "db_cadfish.db";
    public static String TABELA_USUARIOS = "usuarios";
    public static String TABELA_ESPECIES = "especies";
    public static String TABELA_PEIXES = "peixes";
    public static String TABELA_SUPER_USUARIOS  = "sp_usuarios";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null,VERSION);
    }

    public void inserirDataEspecie(String name, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + this.TABELA_ESPECIES + " VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name );
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public void inserirDataUsuario(Usuario usuario){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + this.TABELA_USUARIOS + " VALUES(null,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, usuario.getNome());
        statement.bindString(2, usuario.getEmail());
        statement.bindString(3, usuario.getSenha());
        statement.bindBlob(4, usuario.getIcon());

        statement.executeInsert();

    }
    public Cursor getData(String sql){
        SQLiteDatabase datadabe = getReadableDatabase();
        return datadabe.rawQuery(sql, null);
    }

    public Cursor getNameEspecies(){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT nome FROM " + TABELA_ESPECIES + "";
        return  database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUsuarioPadrao = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIOS
                + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " email TEXT NOT NULL, " +
                " senha TEXT NOT NULL, " +
                " icone BLOB "+
                ");";

        String sqlEspecieNova = "CREATE TABLE IF NOT EXISTS "+ TABELA_ESPECIES +
                " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " icone BLOB);";

        String sqlPeixe = "CREATE TABLE IF NOT EXISTS " + TABELA_PEIXES +
                " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " peso REAL NOT NULL, " +
                " tamanho REAL NOT NULL, " +
                " marca_tag TEXT NOT NULL, " +
                " localizacao TEXT NOT NULL)";

        String sqlSuperUsuarios = "CREATE TABLE IF NOT EXISTS " + TABELA_SUPER_USUARIOS +
                "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " email TEXT NOT NULL, " +
                " senha TEXT NOT NULL);";

        try {
            db.execSQL(sqlUsuarioPadrao);
            db.execSQL(sqlEspecieNova);
            db.execSQL(sqlPeixe);
            db.execSQL(sqlSuperUsuarios);
            Log.i("INFO DB", "Sucesso ao criar a tabelas");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabea" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlUser = "DROP TABLE IF EXISTS " + TABELA_USUARIOS + ";";
        String sqlPeixe = "DROP TABLE IF EXISTS " + TABELA_PEIXES + ";";
        String sqlEspecie = "DROP TABLE IF EXISTS " + TABELA_ESPECIES + ";";

        try{
            db.execSQL(sqlUser);
            db.execSQL(sqlPeixe);
            db.execSQL(sqlEspecie);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao Atualizar App");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar app");
        }
    }
}