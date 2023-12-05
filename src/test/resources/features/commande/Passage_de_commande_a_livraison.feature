#language:fr
Fonctionnalité: Passage de commande en entier

  Contexte:
    Etant donné qu'un Utilisateur se nommant "Antoine" "Dupont"

  Scénario: Passer une commande
    Etant donné que l'utilisateur possède une commande d'ID 0
    Et que l'utilisateur a ajouter un plat "Pate" à 5€ au restaurant "Chez Antoine"
    Et qu'il ajoute la date de livraison "2023-12-06" à "12:00" et le lieu de livraison "Paris"
    Quand l'utilisateur paye la commande d'ID 0
    Alors la commande est passé au status "EN_PREPARATION"
    Et le restaurateur peut récupérer la commande d'ID 0
    Quand le restaurateur termine la commande
    Alors la commande est passé au status "EN_LIVRAISON"
    Et que le livreur peut récupérer la commande d'ID 0
    Quand le livreur livre la commande
    Alors la commande est passé au status "LIVREE"
