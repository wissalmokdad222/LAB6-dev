# PizzaRecipes – Application Android

## Objectif général
Cette application Android en Java permet de :  

- Afficher une liste de pizzas avec leur nom, prix, durée de préparation et image.  
- Accéder à un **détail complet de chaque recette**.  

### Objectifs pédagogiques :  
- Organisation d’un projet Android en packages logiques (`classes`, `dao`, `service`, `adapter`, `ui`).  
- Utilisation d’une `ListView` avec un **Adapter personnalisé**.  
- Passage de données entre activités.  
- Mise en place d’un **Splash Screen animé** avec texte dynamique.  
- Modélisation des pizzas avec un **objet orienté produit**.

---

## Structure du projet


com.example.pizzarecipes
│
├─ classes → entités métier (Produit)

├─ dao → interfaces CRUD (IDao)

├─ service → gestion et stockage des données (ProduitService)

├─ adapter → affichage personnalisé (PizzaAdapter)

└─ ui → activités (SplashActivity, ListPizzaActivity, PizzaDetailActivity)

- Images des pizzas (`pizza1.jpg` à `pizza10.jpg`) dans `res/mipmap`
  ![](https://github.com/user-attachments/assets/df6ed4bd-d191-49af-af7d-96a13beb9611)
  ![](https://github.com/user-attachments/assets/d56ef90a-511a-4e05-a6bf-d55458e63f92)
  ![](https://github.com/user-attachments/assets/57629ffb-d181-4a9a-9738-9a13341386d4)

## Modèle de données `Produit`

Chaque pizza est représentée par un objet `Produit` :

```java
package com.example.pizzarecipes.classes;


public class Produit {
    private static long AUTO_ID = 1;

    private long id;
    private String nom;
    private double prix;
    private int imageRes;
    private String duree;
    private String ingredients;
    private String description;
    private String etapes;

    // Constructeur sans paramètres
    public Produit() {
        this.id = AUTO_ID++;
    }

    public Produit(String nom, double prix, int imageRes, String duree,
                   String ingredients, String description, String etapes) {
        this.id = AUTO_ID++;
        this.nom = nom;
        this.prix = prix;
        this.imageRes = imageRes;
        this.duree = duree;
        this.ingredients = ingredients;
        this.description = description;
        this.etapes = etapes;
    }

    public long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public int getImageRes() { return imageRes; }
    public void setImageRes(int imageRes) { this.imageRes = imageRes; }
    public String getDuree() { return duree; }
    public void setDuree(String duree) { this.duree = duree; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getEtapes() { return etapes; }
    public void setEtapes(String etapes) { this.etapes = etapes; }

    @Override
    public String toString() {
        return nom + " - " + prix + " €";
    }}
 ```
## DAO et Service

### Interface DAO :
 ```java
public interface IDao<T> {
    T create(T t);
    T update(T t);
    boolean delete(long id);
    T findById(long id);
    List<T> findAll();
}

Service singleton ProduitService pour gérer les pizzas en mémoire :

public class ProduitService implements IDao<Produit> {
    private static ProduitService INSTANCE;
    private final List<Produit> data = new ArrayList<>();

    private ProduitService() { seed(); }

    public static ProduitService getInstance() {
        if (INSTANCE == null) INSTANCE = new ProduitService();
        return INSTANCE;
    }

    private void seed() {
        data.add(new Produit("BARBECUED CHICKEN PIZZA", 3.0,
                R.mipmap.pizza1, "35 min",
                "- 2 chicken breast halves\n- 1 cup barbecue sauce...",
                "Pizza au poulet barbecue simple et rapide.",
                "STEP 1: Griller le poulet.\nSTEP 2: Garnir et cuire."));
        // Ajouter les autres pizzas
    }
}
 ```
Splash Screen animé

### Layout activity_splash.xml :
 ```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:background="#F5E6D3">

    <LinearLayout
        android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:orientation="vertical"
        android:gravity="center"
        android:layout_gravity="center">

        <ImageView
            android:id="@+id/splashLogo"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:src="@drawable/pizza1" />

        <TextView
            android:id="@+id/tvTitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="22sp"
            android:textStyle="italic"
            android:textColor="@android:color/black"
            android:gravity="center" />

        <TextView
            android:id="@+id/tvSubtitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="16sp"
            android:textColor="#6D4C41"
            android:gravity="center" />
    </LinearLayout>
</FrameLayout>
 ```
### SplashActivity.java :
 ```java
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.splashLogo);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        logo.startAnimation(anim);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvSubtitle = findViewById(R.id.tvSubtitle);
        tvTitle.setText("Pizza Recipes");
        tvSubtitle.setText("Fresh • Hot • Delicious ");

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, ListPizzaActivity.class));
            finish();
        }, SPLASH_DURATION);
    }
}
 ```
Liste des pizzas

### activity_list_pizza.xml : ListView pour toutes les pizzas.
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFF8F0"
    android:padding="8dp">

<ListView
android:id="@+id/lvPizzas"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:dividerHeight="8dp"
android:clipToPadding="false"
android:paddingBottom="8dp"/>
    </LinearLayout>

row_pizza.xml : layout pour chaque pizza (image, nom, durée, prix).
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:padding="8dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <!-- Image de la pizza -->
    <ImageView
        android:id="@+id/imgPizza"
        android:layout_width="96dp"
        android:layout_height="96dp"
        android:scaleType="centerCrop"
        android:layout_alignParentStart="true"
        android:layout_centerVertical="true"/>

    <!-- Nom de la pizza -->
    <TextView
        android:id="@+id/tvNom"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toEndOf="@id/imgPizza"
        android:layout_marginStart="12dp"
        android:layout_alignTop="@id/imgPizza"
        android:textStyle="bold"
        android:textSize="16sp"
        android:textColor="#5D4037"/>

    <!-- Description ou métadonnées -->
    <TextView
        android:id="@+id/tvMeta"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/tvNom"
        android:layout_alignStart="@id/tvNom"
        android:layout_marginTop="4dp"
        android:textSize="14sp"
        android:textColor="#8D6E63"/>

</RelativeLayout>
```
### PizzaAdapter.java : Adapter personnalisé.
```java
package com.example.pizzarecipes.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.example.pizzarecipes.R;
import com.example.pizzarecipes.classes.Produit;
import java.util.List;

public class PizzaAdapter extends BaseAdapter {
    private final Context ctx;
    private final List<Produit> pizzas;

    public PizzaAdapter(Context ctx, List<Produit> pizzas) {
        this.ctx = ctx;
        this.pizzas = pizzas;
    }

    @Override public int getCount() { return pizzas.size(); }
    @Override public Object getItem(int i) { return pizzas.get(i); }
    @Override public long getItemId(int i) { return pizzas.get(i).getId(); }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(ctx).inflate(R.layout.row_pizza, parent, false);

        ImageView img = convertView.findViewById(R.id.imgPizza);
        TextView tvNom = convertView.findViewById(R.id.tvNom);
        TextView tvMeta = convertView.findViewById(R.id.tvMeta);

        Produit p = pizzas.get(pos);
        img.setImageResource(p.getImageRes());
        tvNom.setText(p.getNom());
        tvMeta.setText(p.getDuree() + " • " + p.getPrix() + " €");

        return convertView;
    }
}
```

### ListPizzaActivity.java : activité pour afficher la liste et gérer le clic.
```java
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
```
## Détail d’une pizza

### activity_pizza_detail.xml : ScrollView avec image, nom, durée, prix, ingrédients, description et étapes.
```xml
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFF8F0"> <!-- fond beige clair -->

    <LinearLayout
        android:orientation="vertical"
        android:padding="16dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <!-- Image de la pizza -->
        <ImageView
            android:id="@+id/img"
            android:layout_width="match_parent"
            android:layout_height="220dp"
            android:scaleType="centerCrop"
            android:layout_marginBottom="16dp"
            android:background="@drawable/round_corners_bg"/> <!-- drawable conservé -->

        <!-- Nom -->
        <TextView
            android:id="@+id/title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textStyle="bold"
            android:textSize="22sp"
            android:textColor="#5D4037"
            android:layout_marginBottom="4dp"/>

        <!-- Meta (durée et prix) -->
        <TextView
            android:id="@+id/meta"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textSize="14sp"
            android:textColor="#8D6E63"
            android:layout_marginBottom="12dp"/>

        <!-- Section ingrédients -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Ingrédients :"
            android:textStyle="bold"
            android:textColor="#5D4037"
            android:textSize="16sp"
            android:layout_marginBottom="4dp"/>
        <TextView
            android:id="@+id/ingredients"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textColor="#4E342E"
            android:layout_marginBottom="12dp"/>

        <!-- Section description -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Description :"
            android:textStyle="bold"
            android:textColor="#5D4037"
            android:textSize="16sp"
            android:layout_marginBottom="4dp"/>
        <TextView
            android:id="@+id/desc"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textColor="#4E342E"
            android:layout_marginBottom="12dp"/>

        <!-- Section étapes -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Étapes :"
            android:textStyle="bold"
            android:textColor="#5D4037"
            android:textSize="16sp"
            android:layout_marginBottom="4dp"/>
        <TextView
            android:id="@+id/steps"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textColor="#4E342E"
            android:layout_marginBottom="16dp"/>

    </LinearLayout>
</ScrollView>
```
### PizzaDetailActivity.java : charge les données depuis ProduitService et affiche le détail.
```java
package com.example.pizzarecipes.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarecipes.R;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.service.ProduitService;

public class PizzaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        // Récupération de l'ID de la pizza passé via l'intent
        long id = getIntent().getLongExtra("pizza_id", -1);
        Produit p = ProduitService.getInstance().findById(id);

        // Références des vues
        ImageView img = findViewById(R.id.img);
        TextView title = findViewById(R.id.title);
        TextView meta = findViewById(R.id.meta);
        TextView ingredients = findViewById(R.id.ingredients);
        TextView desc = findViewById(R.id.desc);
        TextView steps = findViewById(R.id.steps);

        // Remplissage des données si la pizza existe
        if (p != null) {
            img.setImageResource(p.getImageRes());
            title.setText(p.getNom());
            meta.setText(p.getDuree() + " • " + p.getPrix() + " €");
            ingredients.setText(p.getIngredients());
            desc.setText(p.getDescription());
            steps.setText(p.getEtapes());
        } else {
            title.setText("Pizza introuvable !");
            meta.setText("");
            ingredients.setText("");
            desc.setText("");
            steps.setText("");
        }
    }
}
```
## Résultat attendu

Splash Screen avec logo et texte dynamique.

Liste des pizzas avec nom, image, durée, prix.

Détail complet d’une pizza sur clic.

Code organisé en DAO / Service / Adapter / UI.

## Captures d’écran
### Splash Screen
![](https://github.com/user-attachments/assets/39968d8e-8837-49b9-a660-51079f0808c3)
### Liste des pizzas
![](https://github.com/user-attachments/assets/749b49cb-52da-437e-b0c8-59927038a65c)
### Détail pizza
![](https://github.com/user-attachments/assets/1bc60ef9-838c-4d71-9375-b19cd11d7a47)
	
## Technologies utilisées

Java & Android Studio

ListView et Adapter personnalisé

Animation et Splash Screen

Architecture simple DAO/Service/UI
