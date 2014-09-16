package br.com.evacinas.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.evacinas.helpers.DBHelper;
import br.com.evacinas.models.Vacina;

public class VacinaDao {
	
	public static final String NOME_TABELA = "vacinas";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_FAIXA_ETARIA = "faixa_etaria";
 
    public static final String SCRIPT_CRIACAO_TABELA_VACINA = "CREATE TABLE " + NOME_TABELA + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY ," + COLUNA_NOME + " TEXT,"+ COLUNA_FAIXA_ETARIA + " TEXT" + ");";
 
    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;
 
 
    private SQLiteDatabase dataBase = null;
 
 
    private static VacinaDao instance;
     
    public static VacinaDao getInstance(Context context) {
        if(instance == null)
            instance = new VacinaDao(context);
        return instance;
    }
 
    private VacinaDao(Context context) {
    	 DBHelper dBHelper = DBHelper.getInstance(context);
         dataBase = dBHelper.getWritableDatabase();
    }
 
    public void salvar(Vacina Vacina) {
        ContentValues values = gerarContentValeuesVacina(Vacina);
        dataBase.insert(NOME_TABELA, null, values);
    }
 
    public List<Vacina> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Vacina> Vacinas = construirVacinaPorCursor(cursor);
 
        return Vacinas;
    }
    
    public Vacina recuperarPelaPosicao(int posicao) {
    	  String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
          Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
          List<Vacina> Vacinas = construirVacinaPorCursor(cursor);
   
          return Vacinas.get(posicao);
    }
 
    public Vacina recuperarPeloId(int id) {
  	  String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE ID = " + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Vacina> Vacinas = construirVacinaPorCursor(cursor);
        if(!Vacinas.isEmpty()){        	
        	return Vacinas.get(0);
        }else{
        	return new Vacina();
        }
  }
    
    public void deletar(Vacina Vacina) {
 
        String[] valoresParaSubstituir = {
                String.valueOf(Vacina.getId())
        };
 
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
 
    public void editar(Vacina Vacina) {
        ContentValues valores = gerarContentValeuesVacina(Vacina);
 
        String[] valoresParaSubstituir = {
                String.valueOf(Vacina.getId())
        };
 
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
 
    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close(); 
    }
 
 
    private List<Vacina> construirVacinaPorCursor(Cursor cursor) {
        List<Vacina> Vacinas = new ArrayList<Vacina>();
        if(cursor == null)
            return Vacinas;
         
        try {
 
            if (cursor.moveToFirst()) {
                do {
 
                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);
                    int indexFaixaEtaria = cursor.getColumnIndex(COLUNA_FAIXA_ETARIA);
 
                    int id = cursor.getInt(indexID);
                    String nome = cursor.getString(indexNome);
                    String faixa_etaria = cursor.getString(indexFaixaEtaria);
 
                    Vacina Vacina = new Vacina();
                    Vacina.setId(id);
                    Vacina.setNome(nome);
                    Vacina.setFaixa_etaria(faixa_etaria);
                    Vacinas.add(Vacina);
 
                } while (cursor.moveToNext());
            }
             
        } finally {
            cursor.close();
        }
        return Vacinas;
    }
 
    private ContentValues gerarContentValeuesVacina(Vacina Vacina) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, Vacina.getNome());
        values.put(COLUNA_FAIXA_ETARIA, Vacina.getFaixa_etaria());
 
        return values;
    }
    
    public Vacina findByNome(String nome){
    	String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_NOME + " = " + "'" +nome+ "'";
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Vacina> Vacinas = construirVacinaPorCursor(cursor);
        if(!Vacinas.isEmpty()){        	
        	return Vacinas.get(0);
        }else{
        	return new Vacina();
        }
    }
}
