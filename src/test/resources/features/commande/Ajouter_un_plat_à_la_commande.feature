#language:fr
Fonctionnalité: Ajouter un plat dans la commande en cours de création

  Scénario: ajout réussi
    Etant donné qu'une commande en cours de création avec un montant qui s'élève à 0€
    Quand l'utilisateur séléctionne le plat "tagliatelles au saumon"
    Alors l'utilisateur récupère la liste des aliments du plat
    Et l'utilisateur récupère la liste des alergenes du plat
    Quand l'utilisateur ajoute 1 quantité de plat de "tagliatelles au saumon" à 10€
    Alors 1 quantité de ce plat sera dans ma commande
    Et le prix de la commande est à 10€