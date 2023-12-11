import java.util.HashMap;
import java.util.Map;

public class Wallet {

    // Portefeuille pour stocker les actifs
    private static Map<String, Asset> wallets = new HashMap<>();

    // Fonction pour ajouter un actif
    private static void addAsset(String name, String symbol, int quantity, double price) {
        // Vérifie que les paramètres sont valides
        if (name == null || symbol == null || quantity <= 0 || price <= 0) {
            return;
        }

        // Ajoute l'actif au portefeuille
        wallets.put(name, new Asset(symbol, quantity, price));
    }

    // Fonction pour supprimer un actif
    private static void deleteAsset(String name) {
        // Vérifie que l'actif existe
        if (!wallets.containsKey(name)) {
            return;
        }

        // Supprime l'actif du portefeuille
        wallets.remove(name);
    }

    // Fonction pour lister les actifs
    private static void listAssets() {
        // Itère sur les actifs du portefeuille
        for (Map.Entry<String, Asset> entry : wallets.entrySet()) {
            String name = entry.getKey();
            Asset asset = entry.getValue();

            // Affiche les informations sur l'actif
            System.out.println(name + " ==> | Symbole : " + asset.getSymbol() +
                    " | Quantité : " + asset.getQuantity() +
                    " | Prix : " + asset.getPrice() + " |");
        }
    }

    // Fonction pour calculer la valeur totale
    private static double calculateTotalValue() {
        // Initialise la valeur totale à 0
        double totalValue = 0;

        // Itère sur les actifs du portefeuille
        for (Asset asset : wallets.values()) {
            // Ajoute la valeur de l'actif à la valeur totale
            totalValue += asset.getQuantity() * asset.getPrice();
        }

        // Retourne la valeur totale
        return totalValue;
    }

    // Fonction pour calculer le rendement
    private static double calculateReturn() {
        // Initialise le rendement à 0
        double returnRate = 0;

        // Vérifie que le portefeuille n'est pas vide
        if (wallets.isEmpty()) {
            return returnRate;
        }

        // Récupère la valeur de départ du portefeuille
        Asset startingAsset = wallets.values().iterator().next();
        double startingValue = startingAsset.getPrice() * startingAsset.getQuantity();

        // Calcule le rendement
        returnRate = (calculateTotalValue() - startingValue) / startingValue;

        // Retourne le rendement
        return returnRate;
    }

    public static void main(String[] args) {
        // Ajoute quelques actifs au portefeuille
        System.out.println("*** Fonctionnalité 1 : Ajouter des actifs au portefeuille...****");
        addAsset("Apple", "AAPL", 1, 100);
        addAsset("Google", "GOOG", 1, 150);
        addAsset("Tesla", "TSLA", 4, 1000);

        // Liste les actifs du portefeuille
        System.out.println("*** Fonctionnalité 2 : Lister les éléments du protefeuille :****");
        listAssets();

        // Exemple d'utilisation de la fonction deleteAsset
        System.out.println("*** Fonctionnalité 3 : Supprimer l'actif Google...****");
        deleteAsset("Google");

        // Liste les actifs du portefeuille
        System.out.println("Actifs restants :");
        listAssets();

        // Calcule la valeur totale du portefeuille
        System.out.println("*** Fonctionnalité 4 : Calculer la valeur total des éléements du portefeuille****");
        double totalValue = calculateTotalValue();
        System.out.println("Valeur totale : " + totalValue);

        // Calcule le rendement du portefeuille
        System.out.println("*** Fonctionnalité 5 : Calculer le rendement****");
        double returnRate = calculateReturn();
        System.out.println("Rendement : " + returnRate);
    }

    // Classe interne pour représenter un actif
    private static class Asset {
        private String symbol;
        private int quantity;
        private double price;

        public Asset(String symbol, int quantity, double price) {
            this.symbol = symbol;
            this.quantity = quantity;
            this.price = price;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }
    }
}
