package com.famalde.rssreader;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

/**
 * Lector de Rss clase principal.
 * 
 * @author Fernando
 * 
 */
public class RssReaderActivity extends ListActivity {

	private List<String> titulares;
	private List<String> enlaces;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_reader);
		setTitle("Edlam´s Rss Reader");
		initComponent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rss_reader, menu);
		return true;
	}

	/**
	 * Inicializa los elementos del componente.
	 */
	private void initComponent() {
		titulares = new ArrayList<String>();
		enlaces = new ArrayList<String>();

		ProgressBar progressBar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		progressBar.setIndeterminate(true);
		
		getListView().setEmptyView(progressBar);

	}

}
