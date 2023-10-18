#language:fr
Fonctionnalité: Ajouter un plat dans la commande en cours de création

  Scénario: ajout réussi
    Etant donné qu'une commande en cours de création avec un montant qui s'élève à 0€
    Et que je veux ajouter 1 quantité de plat de "tagliatelles au saumon" à 10€
    Quand j'ajoute 1 quantité sur le plat
    Alors 1 quantité de ce plat sera dans ma commande
    Et le prix de la commande est à 10€