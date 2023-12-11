// Fonction pour ajouter un actif
function addAsset(name, symbol, quantity, price) {
  // Vérifie que les paramètres sont valides
  if (!name || !symbol || !quantity || !price) {
    return;
  }

  // Ajoute l'actif au portefeuille
  wallets[name] = {
    symbol: symbol,
    quantity: quantity,
    price: price,
  };
}

// Fonction pour supprimer un actif
function deleteAsset(name) {
  // Vérifie que l'actif existe
  if (!wallets[name]) {
    return;
  }

  // Supprime l'actif du portefeuille
  delete wallets[name];
}

// Fonction pour lister les actifs
// function listAssets() {
//   // Ajoute une ligne vide pour la clarté
//   console.log();

//   // Itère sur les actifs du portefeuille
//   for (const [name, asset] of Object.entries(wallets)) {
//     // Affiche les informations sur l'actif en fonction du type
//     if (name === "IdentityCard") {
//       console.log(
//         `Carte d'identité ==> | Nom complet : ${asset.fullName} | Date de naissance : ${asset.dateOfBirth} | Nationalité : ${asset.nationality} | Numéro : ${asset.number} |`
//       );
//     } else if (name === "BankCardVisaHistory") {
//       console.log(
//         `Carte bancaire et Visa ==> | Date de transaction : ${asset.transactionDate} | Marchand : ${asset.merchant} | Montant : ${asset.amount} |`
//       );
//     } else {
//       console.log(
//         `${name} ==> | Symbole : ${asset.symbol} | Quantité : ${asset.quantity} | Prix : ${asset.price} |`
//       );
//     }
//   }

//   // Ajoute une ligne vide pour la clarté
//   console.log();
// }
// Fonction pour lister les actifs
// function listAssets() {
//   // Ajoute une ligne vide pour la clarté
//   console.log();

//   // Itère sur les actifs du portefeuille
//   for (const [name, asset] of Object.entries(wallets)) {
//     // Affiche le nom de l'actif
//     console.log(`${name} ==>`);

//     // Itère sur les propriétés de l'actif
//     for (const [key, value] of Object.entries(asset)) {
//       console.log(`  ${key}: ${value}`);
//     }

//     // Ajoute une ligne vide pour séparer les actifs
//     console.log();
//   }
// }
function listAssets() {
  // Ajoute une ligne vide pour la clarté
  console.log();

  // Itère sur les actifs du portefeuille
  for (const [name, asset] of Object.entries(wallets)) {
    // Affiche le nom de l'actif
    console.log(`${name} ==>`);

    // Vérifie si la valeur est un objet ou un tableau
    if (typeof asset === "object" && asset !== null) {
      // Si c'est un tableau, itère sur ses éléments
      if (Array.isArray(asset)) {
        for (const item of asset) {
          // Itère sur les propriétés de chaque élément
          for (const [key, value] of Object.entries(item)) {
            console.log(`  ${key}: ${value}`);
          }
          // Ajoute une ligne vide pour séparer les éléments du tableau
          console.log();
        }
      } else {
        // Si c'est un objet, itère sur ses propriétés
        for (const [key, value] of Object.entries(asset)) {
          console.log(`  ${key}: ${value}`);
        }
      }
    } else {
      // Si ce n'est ni un objet ni un tableau, affiche la valeur directement
      console.log(`  ${asset}`);
    }

    // Ajoute une ligne vide pour séparer les actifs
    console.log();
  }
}

// Fonction pour calculer la valeur totale
function calculateTotalValue() {
  // Initialise la valeur totale à 0
  let totalValue = 0;

  // Itère sur les actifs du portefeuille
  for (const asset of Object.values(wallets)) {
    // Ajoute la valeur de l'actif à la valeur totale
    totalValue += asset.quantity * asset.price;
  }

  // Retourne la valeur totale
  return totalValue;
}

// Fonction pour calculer le rendement
function calculateReturn() {
  // Initialise le rendement à 0
  let returnRate = 0;

  // Vérifie que le portefeuille n'est pas vide
  if (Object.keys(wallets).length === 0) {
    return returnRate;
  }

  // Récupère la valeur de départ du portefeuille
  const startingAsset = Object.values(wallets)[0];
  const startingValue = startingAsset.price * startingAsset.quantity;

  // Calcule le rendement
  returnRate = (calculateTotalValue() - startingValue) / startingValue;

  // Retourne le rendement
  return returnRate;
}

// *********************************************************************************

// Fonction pour ajouter une carte d'identité
function addIdentityCardInformation(cinInfo) {
  // Vérifie que les informations de la CIN sont valides
  if (!cinInfo) {
    return;
  }

  // Ajoute les informations de la CIN au portefeuille
  wallets["IdentityCard"] = cinInfo;
}

// Fonctions pour gérer l'argent dans le portefeuille
function rechargeWallet(amount) {
  // Vérifie que le montant est valide
  if (!amount || amount <= 0) {
    return;
  }

  // Effectue la recharge du portefeuille
  wallets["Balance"] = (wallets["Balance"] || 0) + amount;
}

function withdrawWallet(amount) {
  // Vérifie que le montant est valide
  if (!amount || amount <= 0) {
    return;
  }

  // Effectue le retrait du portefeuille
  wallets["Balance"] = (wallets["Balance"] || 0) - amount;
}

function checkBalance() {
  // Affiche le solde du portefeuille
  console.log("Solde actuel :", wallets["Balance"] || 0);
}

// Définir un tableau pour stocker les dépenses
const expenses = [];

// Définir un tableau pour stocker les transactions
const transactions = [];

// Fonction pour calculer les dépenses
function calculateTotalExpenses() {
  // Calculer le total des dépenses
  const totalExpenses = expenses.reduce(
    (total, expense) => total + expense.amount,
    0
  );

  // Afficher le total des dépenses
  console.log("Total des dépenses :", totalExpenses);
}

function trackTransactions(transaction) {
  // Suivi des transactions
  transactions.push(transaction);

  // Afficher un message de suivi
  console.log("Nouvelle transaction enregistrée :", transaction);
}

// Exemple d'utilisation des fonctions

// Ajouter une dépense
function addExpense(description, amount) {
  expenses.push({ description, amount });
}

// Fonction pour ajouter une carte bancaire et Visa
function addBankCardVisaTransactionHistory(receipt) {
  // Vérifie que le reçu est valide
  if (!receipt) {
    return;
  }

  // Ajoute le reçu à l'historique des transactions de la carte bancaire et Visa
  wallets["BankCardVisaHistory"] = wallets["BankCardVisaHistory"] || [];
  wallets["BankCardVisaHistory"].push(receipt);
}

// Fonction pour ajouter un permis de conduire
function addDrivingLicenseInformation(licenseInfo) {
  // Vérifie que les informations du permis de conduire sont valides
  if (!licenseInfo) {
    return;
  }

  // Ajoute les informations du permis de conduire au portefeuille
  wallets["DrivingLicense"] = licenseInfo;
}

// Fonction pour ajouter une photo d'identité
function addIdentityPhoto(photo) {
  // Vérifie que la photo est valide
  if (!photo) {
    return;
  }

  // Ajoute la photo d'identité au portefeuille
  wallets["IdentityPhoto"] = photo;
}

// Initialise le portefeuille
const wallets = {};

// Ajoute quelques actifs au portefeuille
console.log("*** Fonctionnalité 1 : Ajouter des actifs au portefeuille...****");
addAsset("Apple", "AAPL", 1, 100);
addAsset("Google", "GOOG", 1, 150);
addAsset("Tesla", "TSLA", 4, 1000);

// Liste les actifs du portefeuille
console.log("*** Fonctionnalité 2 : Lister les éléments du portefeuille :****");
listAssets();

// Exemple d'utilisation des fonctions liées à l'argent dans le portefeuille
console.log(
  "*** Fonctionnalité 3 : Gérer l'argent dans le portefeuille...****"
);
addExpense("Achat de nourriture", 50);
addExpense("Facture d'électricité", 30);

// Calculer et afficher le total des dépenses
calculateTotalExpenses();

// Ajouter une transaction
const transaction1 = { type: "recharge", amount: 100 };
const transaction2 = { type: "retrait", amount: 50 };

trackTransactions(transaction1);
trackTransactions(transaction2);

checkBalance();
// withdrawWallet(200);

// Exemple d'utilisation de la fonction deleteAsset
console.log("*** Fonctionnalité 4 : Supprimer l'actif Google...****");
deleteAsset("Google");

// Liste les actifs du portefeuille
console.log("Actifs restants :");
listAssets();

// Calcule la valeur totale du portefeuille
console.log(
  "*** Fonctionnalité 5 : Calculer la valeur total des éléements du portefeuille****"
);
const totalValue = calculateTotalValue();
console.log("Valeur totale :", totalValue);

// Calcule le rendement du portefeuille
console.log("*** Fonctionnalité 6 : Calculer le rendement****");
const returnRate = calculateReturn();
console.log("Rendement :", returnRate);

// Exemple d'utilisation de la fonction pour ajouter une carte d'identité
console.log("*** Fonctionnalité 7 : Ajouter une carte d'identité...****");
addIdentityCardInformation({
  fullName: "John Doe",
  dateOfBirth: "01/01/1990",
  nationality: "Some Country",
  number: "70123562140",
});

// Exemple d'utilisation de la fonction pour ajouter de l'argent dans le portefeuille
console.log(
  "*** Fonctionnalité 8 : Ajouter de l'argent dans le portefeuille...****"
);
rechargeWallet(1000);

// Exemple d'utilisation de la fonction pour ajouter une carte bancaire et Visa
console.log("*** Fonctionnalité 9 : Ajouter une carte bancaire et Visa...****");
addBankCardVisaTransactionHistory({
  transactionDate: "02/01/2023",
  merchant: "Some Merchant",
  amount: 75.5,
});
addBankCardVisaTransactionHistory({
  transactionDate: "05/01/2023",
  merchant: "Marchands",
  amount: 100.5,
});

// Exemple d'utilisation de la fonction pour ajouter un permis de conduire
console.log("*** Fonctionnalité 10 : Ajouter un permis de conduire...****");
const licenseInfo = {
  licenseNumber: "123456789",
  expirationDate: "01/01/2025",
  // ... other license details
};
addDrivingLicenseInformation(licenseInfo);

// Exemple d'utilisation de la fonction pour ajouter une photo d'identité
console.log("*** Fonctionnalité 11 : Ajouter une photo d'identité...****");
const identityPhoto = "base64-encoded-photo-data"; // Replace with actual base64-encoded photo data
addIdentityPhoto(identityPhoto);

// Affiche l'historique des transactions liées à la carte bancaire et Visa
console.log(
  "*** Historique des transactions de la carte bancaire et Visa :****"
);
console.log(
  wallets["BankCardVisaHistory"] || "Aucune transaction enregistrée."
);

// Affiche les informations du permis de conduire
console.log("*** Informations du permis de conduire :****");
console.log(wallets["DrivingLicense"] || "Aucune information enregistrée.");

// Affiche la photo d'identité
console.log("*** Photo d'identité :****");
console.log(wallets["IdentityPhoto"] || "Aucune photo enregistrée.");

// Liste les actifs du portefeuille après toutes les opérations
console.log("*** Liste finale des éléments du portefeuille :****");
listAssets();
