package br.com.evacinas;

import br.com.evacinas.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton cadastro = (ImageButton) findViewById(R.id.button1);
		cadastro.setOnClickListener(new View.OnClickListener() {			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cadastroIntent = new Intent(MainActivity.this, 
							CadastroVacinasActivity.class);
					startActivity(cadastroIntent);
			}				
		});
		
		ImageButton info = (ImageButton) findViewById(R.id.Button01);
		info.setOnClickListener(new View.OnClickListener() {			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent infoIntent = new Intent(MainActivity.this, 
							InfoVacinasActivity.class);
					startActivity(infoIntent);
			}				
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
