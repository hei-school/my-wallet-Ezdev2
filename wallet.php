<?php

// Fonction pour ajouter un actif
function addAsset($name, $symbol, $quantity, $price) {
    // Vérifie que les paramètres sont valides
    if (!$name || !$symbol || !$quantity || !$price) {
        return;
    }

    // Ajoute l'actif au portefeuille
    global $wallets;
    $wallets[$name] = array(
        "symbol" => $symbol,
        "quantity" => $quantity,
        "price" => $price
    );
}

// Fonction pour supprimer un actif
function deleteAsset($name) {
    // Vérifie que l'actif existe
    global $wallets;
    if (!isset($wallets[$name])) {
        return;
    }

    // Supprime l'actif du portefeuille
    unset($wallets[$name]);
}

function listAssets() {
    // Ajoute une ligne vide pour la clarté
    echo "\n";

    // Itère sur les actifs du portefeuille
    global $wallets;
    foreach ($wallets as $name => $asset) {
        // Affiche le nom de l'actif
        echo "$name ==>\n";

        // Vérifie si la valeur est un tableau associatif
        if (is_array($asset)) {
            // Itère sur ses propriétés
            foreach ($asset as $key => $value) {
                echo "  $key: $value\n";
            }
        } else {
            // Si ce n'est pas un tableau associatif, affiche la valeur directement
            echo "  $asset\n";
        }

        // Ajoute une ligne vide pour séparer les actifs
        echo "\n";
    }
}

// Fonction pour calculer la valeur totale
function calculateTotalValue() {
    // Initialise la valeur totale à 0
    $totalValue = 0;

    // Itère sur les actifs du portefeuille
    global $wallets;
    foreach ($wallets as $asset) {
        // Ajoute la valeur de l'actif à la valeur totale
        $totalValue += $asset["quantity"] * $asset["price"];
    }

    // Retourne la valeur totale
    return $totalValue;
}

// Fonction pour calculer le rendement
function calculateReturn() {
    // Initialise le rendement à 0
    $returnRate = 0;

    // Vérifie que le portefeuille n'est pas vide
    global $wallets;
    if (empty($wallets)) {
        return $returnRate;
    }

    // Récupère la valeur de départ du portefeuille
    $startingAsset = reset($wallets);
    $startingValue = $startingAsset["price"] * $startingAsset["quantity"];

    // Calcule le rendement
    $returnRate = (calculateTotalValue() - $startingValue) / $startingValue;

    // Retourne le rendement
    return $returnRate;
}

// *********************************************************************************

// Fonction pour ajouter une carte d'identité
function addIdentityCardInformation($cinInfo) {
    // Vérifie que les informations de la CIN sont valides
    if (!$cinInfo) {
        return;
    }

    // Ajoute les informations de la CIN au portefeuille
    global $wallets;
    $wallets["IdentityCard"] = $cinInfo;
}

// Fonctions pour gérer l'argent dans le portefeuille
function rechargeWallet($amount) {
    // Vérifie que le montant est valide
    if (!$amount || $amount <= 0) {
        return;
    }

    // Effectue la recharge du portefeuille
    global $wallets;
    $wallets["Balance"] = ($wallets["Balance"] ?? 0) + $amount;
}

function withdrawWallet($amount) {
    // Vérifie que le montant est valide
    if (!$amount || $amount <= 0) {
        return;
    }

    // Effectue le retrait du portefeuille
    global $wallets;
    $wallets["Balance"] = ($wallets["Balance"] ?? 0) - $amount;
}

function checkBalance() {
    // Affiche le solde du portefeuille
    global $wallets;
    echo "Solde actuel : " . ($wallets["Balance"] ?? 0) . "\n";
}

// Définir un tableau pour stocker les dépenses
$expenses = [];

// Définir un tableau pour stocker les transactions
$transactions = [];

// Fonction pour calculer les dépenses
function calculateTotalExpenses() {
    // Calculer le total des dépenses
    global $expenses;
    $totalExpenses = array_reduce($expenses, function ($total, $expense) {
        return $total + $expense["amount"];
    }, 0);

    // Afficher le total des dépenses
    echo "Total des dépenses : $totalExpenses\n";
}

function trackTransactions($transaction) {
    // Suivi des transactions
    global $transactions;
    $transactions[] = $transaction;

    // Afficher un message de suivi
    echo "Nouvelle transaction enregistrée : " . json_encode($transaction) . "\n";
}

// Exemple d'utilisation des fonctions

// Ajouter une dépense
function addExpense($description, $amount) {
    global $expenses;
    $expenses[] = array("description" => $description, "amount" => $amount);
}

// Fonction pour ajouter une carte bancaire et Visa
function addBankCardVisaTransactionHistory($receipt) {
    // Vérifie que le reçu est valide
    if (!$receipt) {
        return;
    }

    // Ajoute le reçu à l'historique des transactions de la carte bancaire et Visa
    global $wallets;
    $wallets["BankCardVisaHistory"] = $wallets["BankCardVisaHistory"] ?? [];
    $wallets["BankCardVisaHistory"][] = $receipt;
}

// Fonction pour ajouter un permis de conduire
function addDrivingLicenseInformation($licenseInfo) {
    // Vérifie que les informations du permis de conduire sont valides
    if (!$licenseInfo) {
        return;
    }

    // Ajoute les informations du permis de conduire au portefeuille
    global $wallets;
    $wallets["DrivingLicense"] = $licenseInfo;
}

// Fonction pour ajouter une photo d'identité
function addIdentityPhoto($photo) {
    // Vérifie que la photo est valide
    if (!$photo) {
        return;
    }

    // Ajoute la photo d'identité au portefeuille
    global $wallets;
    $wallets["IdentityPhoto"] = $photo;
}

// Initialise le portefeuille
$wallets = [];

// Ajoute quelques actifs au portefeuille
echo "*** Fonctionnalité 1 : Ajouter des actifs au portefeuille...****\n";
addAsset("Apple", "AAPL", 1, 100);
addAsset("Google", "GOOG", 1, 150);
addAsset("Tesla", "TSLA", 4, 1000);

// Liste les actifs du portefeuille
echo "*** Fonctionnalité 2 : Lister les éléments du portefeuille :****\n";
listAssets();

// Exemple d'utilisation des fonctions liées à l'argent dans le portefeuille
echo "*** Fonctionnalité 3 : Gérer l'argent dans le portefeuille...****\n";
addExpense("Achat de nourriture", 50);
addExpense("Facture d'électricité", 30);

// Calculer et afficher le total des dépenses
calculateTotalExpenses();

// Ajouter une transaction
$transaction1 = array("type" => "recharge", "amount" => 100);
$transaction2 = array("type" => "retrait", "amount" => 50);

trackTransactions($transaction1);
trackTransactions($transaction2);

checkBalance();

// Exemple d'utilisation de la fonction deleteAsset
echo "*** Fonctionnalité 4 : Supprimer l'actif Google...****\n";
deleteAsset("Google");

// Liste les actifs du portefeuille
echo "Actifs restants :\n";
listAssets();

// Calcule la valeur totale du portefeuille
echo "*** Fonctionnalité 5 : Calculer la valeur total des éléements du portefeuille****\n";
$totalValue = calculateTotalValue();
echo "Valeur totale : $totalValue\n";

// Calcule le rendement du portefeuille
echo "*** Fonctionnalité 6 : Calculer le rendement****\n";
$returnRate = calculateReturn();
echo "Rendement : $returnRate\n";

// Exemple d'utilisation de la fonction pour ajouter une carte d'identité
echo "*** Fonctionnalité 7 : Ajouter une carte d'identité...****\n";
addIdentityCardInformation(array(
    "fullName" => "John Doe",
    "dateOfBirth" => "01/01/1990",
    "nationality" => "Some Country",
    "number" => "70123562140"
));

// Exemple d'utilisation de la fonction pour ajouter de l'argent dans le portefeuille
echo "*** Fonctionnalité 8 : Ajouter de l'argent dans le portefeuille...****\n";
rechargeWallet(1000);

// Exemple d'utilisation de la fonction pour ajouter une carte bancaire et Visa
echo "*** Fonctionnalité 9 : Ajouter une carte bancaire et Visa...****\n";
addBankCardVisaTransactionHistory(array(
    "transactionDate" => "02/01/2023",
    "merchant" => "Some Merchant",
    "amount" => 75.5
));
addBankCardVisaTransactionHistory(array(
    "transactionDate" => "05/01/2023",
    "merchant" => "Marchands",
    "amount" => 100.5
));

// Exemple d'utilisation de la fonction pour ajouter un permis de conduire
echo "*** Fonctionnalité 10 : Ajouter un permis de conduire...****\n";
$licenseInfo = array(
    "licenseNumber" => "123456789",
    "expirationDate" => "01/01/2025"
    // ... other license details
);
addDrivingLicenseInformation($licenseInfo);

// Exemple d'utilisation de la fonction pour ajouter une photo d'identité
echo "*** Fonctionnalité 11 : Ajouter une photo d'identité...****\n";
$identityPhoto = "base64-encoded-photo-data"; // Remplacez par les données réelles de la photo encodées en base64
addIdentityPhoto($identityPhoto);

// Affiche l'historique des transactions liées à la carte bancaire et Visa
echo "*** Historique des transactions de la carte bancaire et Visa :****\n";
echo json_encode($wallets["BankCardVisaHistory"] ?? "Aucune transaction enregistrée.") . "\n";

// Affiche les informations du permis de conduire
echo "*** Informations du permis de conduire :****\n";
echo json_encode($wallets["DrivingLicense"] ?? "Aucune information enregistrée.") . "\n";

// Affiche la photo d'identité
echo "*** Photo d'identité :****\n";
echo json_encode($wallets["IdentityPhoto"] ?? "Aucune photo enregistrée.") . "\n";

// Liste les actifs du portefeuille après toutes les opérations
echo "*** Liste finale des éléments du portefeuille :****\n";
listAssets();

?>
