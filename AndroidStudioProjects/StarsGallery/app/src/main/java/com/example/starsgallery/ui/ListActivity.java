package com.example.starsgallery.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starsgallery.R;
import com.example.starsgallery.adapter.StarAdapter;
import com.example.starsgallery.service.StarService;

/**
 * Activité principale :
 * - Affiche la liste des stars
 * - Gère la recherche (SearchView)
 * - Gère le partage (Intent)
 */
public class ListActivity extends AppCompatActivity {

    private StarAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // 🔹 Initialisation RecyclerView
        recyclerView = findViewById(R.id.recycle_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true); // optimisation

        // 🔹 Adapter + données
        adapter = new StarAdapter(this, StarService.getInstance().findAll());
        recyclerView.setAdapter(adapter);
    }

    //  MENU (SearchView)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Récupérer SearchView
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Rechercher une star...");

        // Listener de recherche
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        return true;
    }

    //  MENU (Share)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.share) {

            String text = "Découvrez l'application Stars ⭐";
            String mimeType = "text/plain";

            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Partager l’application Stars")
                    .setText(text)
                    .startChooser();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}