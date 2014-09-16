package br.com.evacinas.helpers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import br.com.evacinas.dao.VacinaDao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper{
	public static final String NOME_BANCO = "evacinas";
	public static final int VERSAO = 1;

	public static final SimpleDateFormat formatoData = new SimpleDateFormat(
			"dd/MM/yyyy");
	public static final SimpleDateFormat formatoDataHora = new SimpleDateFormat(
			"yyyy/MM/dd");

	private static DBHelper instance;

	private DBHelper(Context context) {
		super(context, NOME_BANCO, null, VERSAO);
	}

	public static DBHelper getInstance(Context context) {
		if (instance == null)
			instance = new DBHelper(context);

		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(VacinaDao.SCRIPT_CRIACAO_TABELA_VACINA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(VacinaDao.SCRIPT_DELECAO_TABELA);
		onCreate(db);
	}

	public Date retornarDatedoBanco(String data) {
		if (data != null) {
			String d[] = data.split("_");
			Date retorno;
			if (d.length == 3) {
				try {
					retorno = new Date(Integer.parseInt(d[0]) - 1900,
							Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2]));
				} catch (Exception dfe) {
					return null;
				}
				return retorno;
			}

		}
		return null;
	}

	public String formatarData(String data) {
		String[] dataTemp = data.split("/");
		data = dataTemp[2] + "-" + dataTemp[1] + "-" + dataTemp[0];
		Log.i("DATA FORMATO", data);
		return data;
	}
}
