package com.example.pizzarecipes.service;

import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.dao.IDao;
import com.example.pizzarecipes.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    private static ProduitService INSTANCE;
    private final List<Produit> data = new ArrayList<>();

    private ProduitService() {
        seed(); // préremplissage des pizzas
    }

    public static ProduitService getInstance() {
        if (INSTANCE == null) INSTANCE = new ProduitService();
        return INSTANCE;
    }

    private void seed() {
        ProduitService ps = this;

        ps.create(new Produit("BARBECUED CHICKEN PIZZA", 3, R.mipmap.pizza1, "35 min",
                "- 2 boneless skinless chicken breast halves (6 ounces each)\n- 1/4 teaspoon pepper\n- 1 cup barbecue sauce\n- 1 tube refrigerated pizza crust\n- 2 teaspoons olive oil\n- 2 cups shredded Gouda cheese\n- 1 small red onion, thinly sliced\n- 1/4 cup minced fresh cilantro",
                "Pizza au poulet barbecue simple et rapide.",
                "STEP 1:\n  Griller le poulet avec poivre et sauce.\nSTEP 2:\n  Étaler la pâte, ajouter sauce, fromage et poulet.\nSTEP 3:\n  Cuire au four et garnir de coriandre."));

        ps.create(new Produit("BRUSCHETTA PIZZA", 5, R.mipmap.pizza2, "35 min",
                "- 1/2 pound reduced-fat bulk pork sausage\n- 1 prebaked 12-inch pizza crust\n- 1 package sliced turkey pepperoni\n- 2 cups shredded mozzarella\n- 1-1/2 cups chopped plum tomatoes\n- 1/2 cup fresh basil leaves\n- 1 tbsp olive oil\n- 2 garlic cloves, minced\n- 1/2 tsp thyme\n- 1/2 tsp balsamic vinegar\n- Salt & pepper",
                "Pizza généreuse avec plein de saveurs italiennes.",
                "STEP 1:\n  Cuire la saucisse, déposer sur la pâte avec le pepperoni et fromage.\nSTEP 2:\n  Mélanger tomates, basilic, huile et épices. Ajouter sur pizza.\nSTEP 3:\n  Cuire au four 10-12 min."));

        ps.create(new Produit("SPINACH PIZZA", 2, R.mipmap.pizza3, "25 min",
                "- Pizza crust mix\n- 1/2 cup Alfredo sauce\n- 2 tomatoes\n- 4 cups chopped fresh spinach\n- 2 cups shredded Italian cheese blend",
                "Pizza crémeuse et légère aux légumes.",
                "STEP 1:\n  Préparer la pâte et étaler sur le moule.\nSTEP 2:\n  Étaler sauce Alfredo, ajouter tomates, épinards et fromage.\nSTEP 3:\n  Cuire au four 10-15 min."));

        ps.create(new Produit("DEEP-DISH SAUSAGE PIZZA", 8, R.mipmap.pizza4, "45 min",
                "- 1 package active dry yeast\n- 2/3 cup warm water\n- 1-3/4 to 2 cups flour\n- 1/4 cup vegetable oil\n- 1 tsp dried oregano, basil, marjoram\n- 1/2 tsp garlic salt & onion salt",
                "Pizza généreuse aux saucisses et fromage, goût traditionnel.",
                "STEP 1:\n  Préparer la pâte et laisser lever.\nSTEP 2:\n  Étaler dans le plat, ajouter garnitures, cuire au four.\nSTEP 3:\n  Ajouter pepperoni et mozzarella, cuire encore 10-15 min."));

        ps.create(new Produit("HOMEMADE PIZZA", 4, R.mipmap.pizza5, "50 min",
                "- 1 package active dry yeast\n- 1 tsp sugar\n- 1-1/4 cups warm water\n- 1/4 cup canola oil\n- 3-1/2 cups flour\n- 1/2 pound ground beef\n- 1 small onion, chopped\n- 1 can tomato sauce\n- 1 tsp dried basil\n- 1 medium green pepper\n- 2 cups mozzarella",
                "Pizza maison généreuse et savoureuse.",
                "STEP 1:\n  Préparer la pâte et laisser lever.\nSTEP 2:\n  Cuire le boeuf et l'oignon.\nSTEP 3:\n  Étaler pâte, ajouter sauce, viande, poivrons et fromage.\nSTEP 4:\n  Cuire au four 25-30 min."));

        ps.create(new Produit("PESTO CHICKEN PIZZA",3,R.mipmap.pizza6,"50 min",
                "- Poulet, poivrons, champignons, pesto, mozzarella",
                "Pizza au poulet avec sauce pesto délicieuse.",
                "STEP 1:\n  Cuire poulet et légumes.\nSTEP 2:\n  Étaler pesto sur pâte, ajouter garnitures.\nSTEP 3:\n  Cuire au four 18-20 min."));

        ps.create(new Produit("LOADED MEXICAN PIZZA",3,R.mipmap.pizza7,"30 min",
                "- Haricots noirs, poivrons, tomates, piment, coriandre, fromage",
                "Pizza mexicaine pleine de saveurs.",
                "STEP 1:\n  Préparer mélange de haricots et légumes.\nSTEP 2:\n  Étaler sur pâte, ajouter fromage.\nSTEP 3:\n  Cuire au four 12-15 min."));

        ps.create(new Produit("BACON CHEESEBURGER PIZZA",2,R.mipmap.pizza8,"20 min",
                "- Viande hachée, bacon, cornichons, mozzarella, cheddar, sauce pizza",
                "Pizza combinant cheeseburger et bacon.",
                "STEP 1:\n  Cuire viande et bacon.\nSTEP 2:\n  Étaler sur pâte avec sauce et fromage.\nSTEP 3:\n  Cuire 8-10 min au four."));

        ps.create(new Produit("PIZZA MARGHERITA",1,R.mipmap.pizza9,"30 min",
                "- Tomates, mozzarella, basilic, huile d’olive",
                "Pizza classique italienne aux couleurs du drapeau.",
                "STEP 1:\n  Préparer pâte et laisser lever.\nSTEP 2:\n  Ajouter sauce, fromage et basilic.\nSTEP 3:\n  Cuire 15-20 min au four."));

        ps.create(new Produit("VEGGIE PIZZA",2,R.mipmap.pizza1,"35 min",
                "- Légumes variés, sauce tomate, mozzarella",
                "Pizza saine et colorée remplie de légumes.",
                "STEP 1:\n  Étaler sauce et légumes.\nSTEP 2:\n  Ajouter fromage.\nSTEP 3:\n  Cuire 12-15 min."));

        ps.create(new Produit("DESSERT PIZZA",3,R.mipmap.pizza10,"25 min",
                "- Pâte sucrée, chocolat, fruits, guimauves",
                "Pizza dessert gourmande et sucrée.",
                "STEP 1:\n  Étaler pâte sucrée.\nSTEP 2:\n  Ajouter chocolat et fruits.\nSTEP 3:\n  Cuire 15-20 min au four."));
    }

    @Override
    public Produit create(Produit p) {
        data.add(p);
        return p;
    }

    @Override
    public Produit update(Produit p) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == p.getId()) {
                data.set(i, p);
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        return data.removeIf(x -> x.getId() == id);
    }

    @Override
    public Produit findById(long id) {
        for (Produit p : data) if (p.getId() == id) return p;
        return null;
    }

    @Override
    public List<Produit> findAll() {
        return Collections.unmodifiableList(data);
    }
}