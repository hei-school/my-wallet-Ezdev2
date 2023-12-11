import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {

    // Définir un tableau pour stocker les dépenses
    private static final List<Expense> expenses = new ArrayList<>();

    // Définir un tableau pour stocker les transactions
    private static final List<String> transactions = new ArrayList<>();

    // Définir une classe pour les dépenses
    private static class Expense {
        double amount;

        Expense(String description, double amount) {
            this.amount = amount;
        }
    }

    // Définir une classe pour les transactions
    private static class Transaction {
        String type;
        double amount;

        Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }
    }

    // Définir un portefeuille
    private static final Map<String, Object> wallets = new HashMap<>();

    // Fonction pour ajouter un actif
    private static void addAsset(String name, String symbol, int quantity, double price) {
        // Vérifie que les paramètres sont valides
        if (name == null || symbol == null || quantity <= 0 || price <= 0) {
            return;
        }

        // Ajoute l'actif au portefeuille
        wallets.put(name, Map.of("symbol", symbol, "quantity", quantity, "price", price));
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

    private static void listAssets() {
        // Ajoute une ligne vide pour la clarté
        System.out.println();

        // Itère sur les actifs du portefeuille
        for (Map.Entry<String, Object> entry : wallets.entrySet()) {
            String name = entry.getKey();
            Object asset = entry.getValue();

            // Affiche le nom de l'actif
            System.out.println(name + " ==>");

            // Vérifie si la valeur est une carte d'identité
            if (name.equals("IdentityCard")) {
                Map<String, String> cinInfo = (Map<String, String>) asset;
                for (Map.Entry<String, String> info : cinInfo.entrySet()) {
                    System.out.println("  " + info.getKey() + ": " + info.getValue());
                }
            } else {
                // Vérifie si la valeur est un objet ou un tableau
                if (asset instanceof Map) {
                    Map<String, Object> assetMap = (Map<String, Object>) asset;

                    // Itère sur les propriétés de l'actif
                    for (Map.Entry<String, Object> prop : assetMap.entrySet()) {
                        System.out.println("  " + prop.getKey() + ": " + prop.getValue());
                    }
                } else {
                    // Si ce n'est ni un objet ni un tableau, affiche la valeur directement
                    System.out.println("  " + asset);
                }
            }

            // Ajoute une ligne vide pour séparer les actifs
            System.out.println();
        }
    }

    // Fonction pour calculer la valeur totale
    private static double calculateTotalValue() {
        // Initialise la valeur totale à 0
        double totalValue = 0;

        // Itère sur les actifs du portefeuille
        for (Object asset : wallets.values()) {
            Map<String, Object> assetMap = (Map<String, Object>) asset;

            // Ajoute la valeur de l'actif à la valeur totale
            totalValue += (int) assetMap.get("quantity") * (double) assetMap.get("price");
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
        Map<String, Object> startingAsset = (Map<String, Object>) wallets.values().iterator().next();
        double startingValue = (double) startingAsset.get("price") * (int) startingAsset.get("quantity");

        // Calcule le rendement
        returnRate = (calculateTotalValue() - startingValue) / startingValue;

        // Retourne le rendement
        return returnRate;
    }

    // *********************************************************************************

    // Fonction pour ajouter une carte d'identité
    private static void addIdentityCardInformation(Map<String, String> cinInfo) {
        // Vérifie que les informations de la CIN sont valides
        if (cinInfo == null) {
            return;
        }

        // Ajoute les informations de la CIN au portefeuille
        wallets.put("IdentityCard", cinInfo);
    }

    // Fonctions pour gérer l'argent dans le portefeuille
    private static void rechargeWallet(double amount) {
        // Vérifie que le montant est valide
        if (amount <= 0) {
            return;
        }

        // Effectue la recharge du portefeuille
        wallets.put("Balance", (double) wallets.getOrDefault("Balance", 0.0) + amount);
    }

    private static void withdrawWallet(double amount) {
        // Vérifie que le montant est valide
        if (amount <= 0) {
            return;
        }

        // Effectue le retrait du portefeuille
        wallets.put("Balance", (double) wallets.getOrDefault("Balance", 0.0) - amount);
    }

    private static void checkBalance() {
        // Affiche le solde du portefeuille
        System.out.println("Solde actuel : " + wallets.getOrDefault("Balance", 0.0));
    }

    // Fonction pour calculer les dépenses
    private static void calculateTotalExpenses() {
        // Calculer le total des dépenses
        double totalExpenses = expenses.stream().mapToDouble(expense -> expense.amount).sum();

        // Afficher le total des dépenses
        System.out.println("Total des dépenses : " + totalExpenses);
    }

    private static void trackTransactions(Transaction transaction) {
        // Suivi des transactions
        transactions.add("Nouvelle transaction enregistrée : " + transaction.type + " - Montant : " + transaction.amount);

        // Afficher un message de suivi
        System.out.println("Nouvelle transaction enregistrée : " + transaction.type + " - Montant : " + transaction.amount);
    }

    // Exemple d'utilisation des fonctions

    // Ajouter une dépense
    private static void addExpense(String description, double amount) {
        expenses.add(new Expense(description, amount));
    }

    // Fonction pour ajouter une carte bancaire et Visa
    private static void addBankCardVisaTransactionHistory(Map<String, Object> receipt) {
        // Vérifie que le reçu est valide
        if (receipt == null) {
            return;
        }

        // Ajoute le reçu à l'historique des transactions de la carte bancaire et Visa
        List<Map<String, Object>> history = (List<Map<String, Object>>) wallets.getOrDefault("BankCardVisaHistory", new ArrayList<>());
        history.add(receipt);
        wallets.put("BankCardVisaHistory", history);
    }

    // Fonction pour ajouter un permis de conduire
    private static void addDrivingLicenseInformation(Map<String, Object> licenseInfo) {
        // Vérifie que les informations du permis de conduire sont valides
        if (licenseInfo == null) {
            return;
        }

        // Ajoute les informations du permis de conduire au portefeuille
        wallets.put("DrivingLicense", licenseInfo);
    }

    // Fonction pour ajouter une photo d'identité
    private static void addIdentityPhoto(String photo) {
        // Vérifie que la photo est valide
        if (photo == null) {
            return;
        }

        // Ajoute la photo d'identité au portefeuille
        wallets.put("IdentityPhoto", photo);
    }

    public static void main(String[] args) {
        // Initialise le portefeuille
        wallets.clear();

        // Ajoute quelques actifs au portefeuille
        System.out.println("*** Fonctionnalité 1 : Ajouter des actifs au portefeuille...****");
        addAsset("Apple", "AAPL", 1, 100);
        addAsset("Google", "GOOG", 1, 150);
        addAsset("Tesla", "TSLA", 4, 1000);

        // Liste les actifs du portefeuille
        System.out.println("*** Fonctionnalité 2 : Lister les éléments du portefeuille :****");
        listAssets();

        // Exemple d'utilisation des fonctions liées à l'argent dans le portefeuille
        System.out.println("*** Fonctionnalité 3 : Gérer l'argent dans le portefeuille...****");
        addExpense("Achat de nourriture", 50);
        addExpense("Facture d'électricité", 30);

        // Calculer et afficher le total des dépenses
        calculateTotalExpenses();

        // Ajouter une transaction
        Transaction transaction1 = new Transaction("recharge", 100);
        Transaction transaction2 = new Transaction("retrait", 50);

        trackTransactions(transaction1);
        trackTransactions(transaction2);

        checkBalance();
        // withdrawWallet(200);

        // Exemple d'utilisation de la fonction deleteAsset
        System.out.println("*** Fonctionnalité 4 : Supprimer l'actif Google...****");
        deleteAsset("Google");

        // Liste les actifs du portefeuille
        System.out.println("Actifs restants :");
        listAssets();

        // Calcule la valeur totale du portefeuille
        System.out.println(
                "*** Fonctionnalité 5 : Calculer la valeur total des éléements du portefeuille****");
        double totalValue = calculateTotalValue();
        System.out.println("Valeur totale : " + totalValue);

        // Calcule le rendement du portefeuille
        System.out.println("*** Fonctionnalité 6 : Calculer le rendement****");
        double returnRate = calculateReturn();
        System.out.println("Rendement : " + returnRate);

        // Exemple d'utilisation de la fonction pour ajouter une carte d'identité
        System.out.println("*** Fonctionnalité 7 : Ajouter une carte d'identité...****");
        addIdentityCardInformation(Map.of(
                "fullName", "John Doe",
                "dateOfBirth", "01/01/1990",
                "nationality", "Some Country",
                "number", "70123562140"
        ));

        // Exemple d'utilisation de la fonction pour ajouter de l'argent dans le portefeuille
        System.out.println(
                "*** Fonctionnalité 8 : Ajouter de l'argent dans le portefeuille...****");
        rechargeWallet(1000);

        // Exemple d'utilisation de la fonction pour ajouter une carte bancaire et Visa
        System.out.println("*** Fonctionnalité 9 : Ajouter une carte bancaire et Visa...****");
        addBankCardVisaTransactionHistory(Map.of(
                "transactionDate", "02/01/2023",
                "merchant", "Some Merchant",
                "amount", 75.5
        ));
        addBankCardVisaTransactionHistory(Map.of(
                "transactionDate", "05/01/2023",
                "merchant", "Marchands",
                "amount", 100.5
        ));

        // Exemple d'utilisation de la fonction pour ajouter un permis de conduire
        System.out.println("*** Fonctionnalité 10 : Ajouter un permis de conduire...****");
        Map<String, Object> licenseInfo = Map.of(
                "licenseNumber", "123456789",
                "expirationDate", "01/01/2025"
                // ... other license details
        );
        addDrivingLicenseInformation(licenseInfo);

        // Exemple d'utilisation de la fonction pour ajouter une photo d'identité
        System.out.println("*** Fonctionnalité 11 : Ajouter une photo d'identité...****");
        String identityPhoto = "base64-encoded-photo-data"; // Replace with actual base64-encoded photo data
        addIdentityPhoto(identityPhoto);

        // Affiche l'historique des transactions liées à la carte bancaire et Visa
        System.out.println(
                "*** Historique des transactions de la carte bancaire et Visa :****");
        System.out.println(
                wallets.getOrDefault("BankCardVisaHistory", "Aucune transaction enregistrée."));

        // Affiche les informations du permis de conduire
        System.out.println("*** Informations du permis de conduire :****");
        System.out.println(wallets.getOrDefault("DrivingLicense", "Aucune information enregistrée."));

        // Affiche la photo d'identité
        System.out.println("*** Photo d'identité :****");
        System.out.println(wallets.getOrDefault("IdentityPhoto", "Aucune photo enregistrée."));

        // Liste les actifs du portefeuille après toutes les opérations
        System.out.println("*** Liste finale des éléments du portefeuille :****");
        listAssets();
    }
}
