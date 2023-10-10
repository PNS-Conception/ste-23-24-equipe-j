#language:fr
Fonctionnalité: Ajouter un plat dans la commande en cours de création

  Scénario: ajout réussi
    Etant donné qu'une commande en cours de création avec un montant qui s'élève à 5€
    Et que je veux ajouter "1" quantité de plat de "tagliatelles au saumon" à "10€"
    Et que le stock restant sur ce produit est de "3"
    Quand j'ajoute "1" quantité sur le plat
    Alors "1" quantité de ce plat sera dans ma commande
    Et le prix de la commande est à "15€"

  Scénario: Pas assez de quantité
    Etant donné qu'une commande en cours de création avec un montant qui s'élève à 5€
    Et que je veux ajouter "2" quantité de plat de "tagliatelles au saumon" à "10€"
    Et que le stock restant sur ce produit est de "1"
    Quand j'ajoute "2" quantités sur le plat
    Alors un message "Il ne reste plus qu'une quantité de ce produit, voulez vous en ajouter qu'une ?" s'affiche

