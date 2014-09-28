package br.com.evacinas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class InfoVacinasActivity extends Activity {
	
	Button buttonNext;
	ImageSwitcher imageSwitcher;

	Animation slide_in_left, slide_out_right;

	int imageResources[] = { 
	   R.drawable.ic_tabela1,
	   R.drawable.ic_tabela2
	};

	 int curIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_vacinas);
		
		  buttonNext = (Button) findViewById(R.id.next);
		  imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);

		  slide_in_left = AnimationUtils.loadAnimation(this,
		    android.R.anim.slide_in_left);
		  slide_out_right = AnimationUtils.loadAnimation(this,
		    android.R.anim.slide_out_right);

		  imageSwitcher.setInAnimation(slide_in_left);
		  imageSwitcher.setOutAnimation(slide_out_right);

		  imageSwitcher.setFactory(new ViewFactory() {

		   @Override
		   public View makeView() {

		    ImageView imageView = new ImageView(InfoVacinasActivity.this);
		    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

		    LayoutParams params = new ImageSwitcher.LayoutParams(
		      LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		    imageView.setLayoutParams(params);
		    return imageView;

		   }
		  });

		  curIndex = 0;
		  imageSwitcher.setImageResource(imageResources[curIndex]);

		  buttonNext.setOnClickListener(new OnClickListener() {

		   @Override
		   public void onClick(View arg0) {
		    if (curIndex == imageResources.length - 1) {
		     curIndex = 0;
		     imageSwitcher.setImageResource(imageResources[curIndex]);
		    } else {
		     imageSwitcher.setImageResource(imageResources[++curIndex]);
		    }
		   }
	 });
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_vacinas, menu);
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
