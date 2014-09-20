package br.com.evacinas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.com.evacinas.dao.VacinaDao;
import br.com.evacinas.helpers.CustomOnItemSelectedListener;
import br.com.evacinas.models.Vacina;
import br.com.evacinas.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroVacinasActivity extends Activity {
	
	String nome, faixa_etaria;
	Button cadastrar;
	Button editar;
	ImageButton excluir;
	ListView listview;
	private Spinner spinner1, spinner2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		
		listview = (ListView) findViewById(R.id.listView1);
		exibirLista();
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		
		
		
		nome = spinner1.getSelectedItem().toString();
		faixa_etaria = spinner2.getSelectedItem().toString();
		
		cadastrar = (Button) findViewById(R.id.btnSubmit);
		cadastrar.setOnClickListener(new OnClickListener() {	
				
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(nome.trim().equals("") || nome == null){
				Toast.makeText(CadastroVacinasActivity.this, "Favor selecione uma vacina", 
						Toast.LENGTH_LONG).show();
			}else if (faixa_etaria.trim().equals("") || faixa_etaria == null){
				Toast.makeText(CadastroVacinasActivity.this, "Favor selecione a faixa etária que tomou a vacina", 
						Toast.LENGTH_LONG).show();
			}else{
				Vacina vacina = new Vacina();
				vacina.setNome(nome);
				vacina.setFaixa_etaria(faixa_etaria);
				
				VacinaDao dao = VacinaDao.getInstance(getApplicationContext());
				
				try {
					dao.salvar(vacina);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				Toast.makeText(CadastroVacinasActivity.this, "Vacina Cadastrada!", Toast.LENGTH_LONG).show();
				exibirLista();
			}
		  }
		});
		
		addListenerOnSpinnerItemSelection();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	 }
	
	public void exibirLista() {
		try {
			String[] from = new String[] { "codigo", "nome" };
			int[] to = new int[] { R.id.listCod, R.id.listNome, };

			VacinaDao vacinaDao = VacinaDao.getInstance(getApplicationContext());
			List<Vacina> listaVacinas = new ArrayList<Vacina>();
			listaVacinas = vacinaDao.recuperarTodos();
			Log.i("LISTA 2", listaVacinas.size() + "");

			List<HashMap<String, String>> mapItensLista = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < listaVacinas.size(); i++) {
				HashMap<String, String> mapItens = new HashMap<String, String>();
				
				mapItens.put("codigo","" + listaVacinas.get(i).getId());
				mapItens.put("nome", listaVacinas.get(i).getNome());

				mapItensLista.add(mapItens);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(this, mapItensLista,
				R.layout.item_listview, from, to);
			listview.setAdapter(adapter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
