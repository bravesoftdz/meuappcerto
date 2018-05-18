package br.com.regiane.meuapp.meuappcerto.estabelecimento;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.regiane.meuapp.meuappcerto.R;

public class EstabelecimentoListActivity extends ListActivity {

    EstabelecimentoListAdapter adapter;
    List<Estabelecimento> estabelecimentos;
    EstabelecimentoDAO dao;

    final int MENU_NOVO = 1;
    final int MENU_CANCELAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento_list);

        dao = new EstabelecimentoDAO(this);
        estabelecimentos = dao.listar();


        adapter = new EstabelecimentoListAdapter(
                this, R.layout.activity_estabelecimento_list_item, estabelecimentos);

        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // para recuperar os elemtos da lista
        Estabelecimento est = estabelecimentos.get(position);

        Intent it = new Intent(this, EstabelecimentoActivity.class);
        it.putExtra(Estabelecimento.ID, est.getId());
        startActivityForResult(it, 1);
    }

    public void novo() {
        Intent it = new Intent(this, EstabelecimentoActivity.class);
        // startActivity(it);
        startActivityForResult(it, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Toast.makeText(this, "Voce licou em Novo!!", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 2) {
            Toast.makeText(this, "Voce Clicou em Cancelar!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelar() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MENU_NOVO, 0, "Novo");
        menu.add(0, MENU_CANCELAR, 0, "Cancelar");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_NOVO:
                novo();
                break;

            case MENU_CANCELAR:
                cancelar();
                break;
        }
        return true;
    }

    public void atualizarLista() {

        estabelecimentos.clear();
        estabelecimentos.addAll(dao.listar());
        adapter.notifyDataSetChanged();

    }


}
