# Fonction pour ajouter un actif
def add_asset(name, symbol, quantity, price):
    # Vérifie que les paramètres sont valides
    if not (name and symbol and quantity and price):
        return

    # Ajoute l'actif au portefeuille
    wallets[name] = {
        "symbol": symbol,
        "quantity": quantity,
        "price": price,
    }

# Fonction pour supprimer un actif
def delete_asset(name):
    # Vérifie que l'actif existe
    if name not in wallets:
        return

    # Supprime l'actif du portefeuille
    del wallets[name]

def list_assets():
    # Ajoute une ligne vide pour la clarté
    print()

    # Itère sur les actifs du portefeuille
    for name, asset in wallets.items():
        # Affiche le nom de l'actif
        print(f"{name} ==>")

        # Vérifie si la valeur est un objet ou un tableau
        if isinstance(asset, dict):
            # Si c'est un tableau, itère sur ses éléments
            if isinstance(asset, list):
                for item in asset:
                    # Itère sur les propriétés de chaque élément
                    for key, value in item.items():
                        print(f"  {key}: {value}")
                    # Ajoute une ligne vide pour séparer les éléments du tableau
                    print()
            else:
                # Si c'est un objet, itère sur ses propriétés
                for key, value in asset.items():
                    print(f"  {key}: {value}")
        else:
            # Si ce n'est ni un objet ni un tableau, affiche la valeur directement
            print(f"  {asset}")

        # Ajoute une ligne vide pour séparer les actifs
        print()

# Fonction pour calculer la valeur totale
def calculate_total_value():
    # Initialise la valeur totale à 0
    total_value = 0

    # Itère sur les actifs du portefeuille
    for asset in wallets.values():
        # Ajoute la valeur de l'actif à la valeur totale
        total_value += asset["quantity"] * asset["price"]

    # Retourne la valeur totale
    return total_value

# Fonction pour calculer le rendement
def calculate_return():
    # Initialise le rendement à 0
    return_rate = 0

    # Vérifie que le portefeuille n'est pas vide
    if not wallets:
        return return_rate

    # Récupère la valeur de départ du portefeuille
    starting_asset = list(wallets.values())[0]
    starting_value = starting_asset["price"] * starting_asset["quantity"]

    # Calcule le rendement
    return_rate = (calculate_total_value() - starting_value) / starting_value

    # Retourne le rendement
    return return_rate

# *********************************************************************************

# Fonction pour ajouter une carte d'identité
def add_identity_card_information(cin_info):
    # Vérifie que les informations de la CIN sont valides
    if not cin_info:
        return

    # Ajoute les informations de la CIN au portefeuille
    wallets["IdentityCard"] = cin_info

# Fonctions pour gérer l'argent dans le portefeuille
def recharge_wallet(amount):
    # Vérifie que le montant est valide
    if not (amount and amount > 0):
        return

    # Effectue la recharge du portefeuille
    wallets["Balance"] = (wallets.get("Balance", 0) + amount)

def withdraw_wallet(amount):
    # Vérifie que le montant est valide
    if not (amount and amount > 0):
        return

    # Effectue le retrait du portefeuille
    wallets["Balance"] = (wallets.get("Balance", 0) - amount)

def check_balance():
    # Affiche le solde du portefeuille
    print("Solde actuel :", wallets.get("Balance", 0))

# Définir un dictionnaire pour stocker les dépenses
expenses = []

# Définir une liste pour stocker les transactions
transactions = []

# Fonction pour calculer les dépenses
def calculate_total_expenses():
    # Calculer le total des dépenses
    total_expenses = sum(expense["amount"] for expense in expenses)

    # Afficher le total des dépenses
    print("Total des dépenses :", total_expenses)

def track_transactions(transaction):
    # Suivi des transactions
    transactions.append(transaction)

    # Afficher un message de suivi
    print("Nouvelle transaction enregistrée :", transaction)

# Exemple d'utilisation des fonctions

# Ajouter une dépense
def add_expense(description, amount):
    expenses.append({"description": description, "amount": amount})

# Fonction pour ajouter une carte bancaire et Visa
def add_bank_card_visa_transaction_history(receipt):
    # Vérifie que le reçu est valide
    if not receipt:
        return

    # Ajoute le reçu à l'historique des transactions de la carte bancaire et Visa
    wallets["BankCardVisaHistory"] = wallets.get("BankCardVisaHistory", [])
    wallets["BankCardVisaHistory"].append(receipt)

# Fonction pour ajouter un permis de conduire
def add_driving_license_information(license_info):
    # Vérifie que les informations du permis de conduire sont valides
    if not license_info:
        return

    # Ajoute les informations du permis de conduire au portefeuille
    wallets["DrivingLicense"] = license_info

# Fonction pour ajouter une photo d'identité
def add_identity_photo(photo):
    # Vérifie que la photo est valide
    if not photo:
        return

    # Ajoute la photo d'identité au portefeuille
    wallets["IdentityPhoto"] = photo

# Initialise le portefeuille
wallets = {}

# Ajoute quelques actifs au portefeuille
print("*** Fonctionnalité 1 : Ajouter des actifs au portefeuille...****")
add_asset("Apple", "AAPL", 1, 100)
add_asset("Google", "GOOG", 1, 150)
add_asset("Tesla", "TSLA", 4, 1000)

# Liste les actifs du portefeuille
print("*** Fonctionnalité 2 : Lister les éléments du portefeuille :****")
list_assets()

# Exemple d'utilisation des fonctions liées à l'argent dans le portefeuille
print("*** Fonctionnalité 3 : Gérer l'argent dans le portefeuille...****")
add_expense("Achat de nourriture", 50)
add_expense("Facture d'électricité", 30)

# Calculer et afficher le total des dépenses
calculate_total_expenses()

# Ajouter une transaction
transaction1 = {"type": "recharge", "amount": 100}
transaction2 = {"type": "retrait", "amount": 50}

track_transactions(transaction1)
track_transactions(transaction2)

check_balance()

# Exemple d'utilisation de la fonction delete_asset
print("*** Fonctionnalité 4 : Supprimer l'actif Google...****")
delete_asset("Google")

# Liste les actifs du portefeuille
print("Actifs restants :")
list_assets()

# Calcule la valeur totale du portefeuille
print(
    "*** Fonctionnalité 5 : Calculer la valeur total des éléements du portefeuille****"
)
total_value = calculate_total_value()
print("Valeur totale :", total_value)

# Calcule le rendement du portefeuille
print("*** Fonctionnalité 6 : Calculer le rendement****")
return_rate = calculate_return()
print("Rendement :", return_rate)

# Exemple d'utilisation de la fonction pour ajouter une carte d'identité
print("*** Fonctionnalité 7 : Ajouter une carte d'identité...****")
add_identity_card_information({
    "fullName": "John Doe",
    "dateOfBirth": "01/01/1990",
    "nationality": "Some Country",
    "number": "70123562140",
})

# Exemple d'utilisation de la fonction pour ajouter de l'argent dans le portefeuille
print(
    "*** Fonctionnalité 8 : Ajouter de l'argent dans le portefeuille...****"
)
recharge_wallet(1000)

# Exemple d'utilisation de la fonction pour ajouter une carte bancaire et Visa
print("*** Fonctionnalité 9 : Ajouter une carte bancaire et Visa...****")
add_bank_card_visa_transaction_history({
    "transactionDate": "02/01/2023",
    "merchant": "Some Merchant",
    "amount": 75.5,
})
add_bank_card_visa_transaction_history({
    "transactionDate": "05/01/2023",
    "merchant": "Marchands",
    "amount": 100.5,
})

# Exemple d'utilisation de la fonction pour ajouter un permis de conduire
print("*** Fonctionnalité 10 : Ajouter un permis de conduire...****")
license_info = {
    "licenseNumber": "123456789",
    "expirationDate": "01/01/2025",
    # ... other license details
}
add_driving_license_information(license_info)

# Exemple d'utilisation de la fonction pour ajouter une photo d'identité
print("*** Fonctionnalité 11 : Ajouter une photo d'identité...****")
identity_photo = "base64-encoded-photo-data"  # Replace with actual base64-encoded photo data
add_identity_photo(identity_photo)

# Affiche l'historique des transactions liées à la carte bancaire et Visa
print(
    "*** Historique des transactions de la carte bancaire et Visa :****"
)
print(
    wallets.get("BankCardVisaHistory", "Aucune transaction enregistrée.")
)

# Affiche les informations du permis de conduire
print("*** Informations du permis de conduire :****")
print(wallets.get("DrivingLicense", "Aucune information enregistrée."))

# Affiche la photo d'identité
print("*** Photo d'identité :****")
print(wallets.get("IdentityPhoto", "Aucune photo enregistrée."))

# Liste les actifs du portefeuille après toutes les opérations
print("*** Liste finale des éléments du portefeuille :****")
list_assets()