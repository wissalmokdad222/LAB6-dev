package com.example.pizzarecipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarecipes.R;
import com.example.pizzarecipes.adapter.PizzaAdapter;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.service.ProduitService;

import java.util.List;

public class ListPizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pizza);

        // ListeView
        ListView lv = findViewById(R.id.lvPizzas);

        // Vérifier si ListView existe
        if (lv == null) {
            throw new RuntimeException("ListView lvPizzas introuvable dans activity_list_pizza.xml !");
        }

        // Récupérer toutes les pizzas
        List<Produit> pizzas = ProduitService.getInstance().findAll();

        // Vérifier si la liste n'est pas vide
        if (pizzas == null || pizzas.isEmpty()) {
            // Afficher un message si aucune pizza
            // Par exemple : Toast ou TextView "Aucune pizza disponible"
            return;
        }

        // Adapter
        PizzaAdapter adapter = new PizzaAdapter(this, pizzas);
        lv.setAdapter(adapter);

        // Click sur un item
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Produit selectedPizza = pizzas.get(position); // Récupérer l'objet produit
            Intent intent = new Intent(this, PizzaDetailActivity.class);
            intent.putExtra("pizza_id", selectedPizza.getId()); // passer l'ID réel
            startActivity(intent);
        });
    }
}