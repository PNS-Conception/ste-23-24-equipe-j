#language:fr
Fonctionnalité: Reduction commande

  Contexte:
    Etant donné que l'utilisateur possède une commande

  Scénario:  Reduction Commande réussi
    Et que la commande possède 9 menus de 5€
    Et que le prix de la commande est à 45,00 €
    Et que la réduction est à 5%
    Quand j'ajoute 1 menu
    Alors le prix de la commande est à 47,50 €

  Scénario:  Reduction Commande non réussi
    Et que la commande possède 7 menus de 5€
    Et que le prix de la commande est à 35,00 €
    Et que la réduction est à 5%
    Quand j'ajoute 1 menu
    Alors le prix de la commande est à 40,00 €

