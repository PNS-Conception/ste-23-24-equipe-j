#language:fr

Fonctionnalité: Recuperer les menus plats de son restaurant

  Contexte:
    Etant donné qu'il y a une commande Multiple avec un Plat "Pate" du Restaurant "Gastronomie"

  Scénario: Récupérer les menus d'un restaurant où la commande possède un menu de ce restaurant
    Quand la commande Multiple est paye
    Alors le plat "Pate" est en préparation pour le Restaurant "Gastronomie"

  Scénario: Récupérer les commandes de plusieurs restaurant dans la commande Multiple
    Etant donné qu'il y a une commande Multiple avec un Plat "Pate" du Restaurant "Gastronomie"
    Et que la commande multiple possède 2 autres Plats de "Salade" du Restaurant "Saladier"
    Quand la commande Multiple est paye
    Alors 1 Plat de "Pate" est en préparation pour le Restaurant "Gastronomie"
    Et que 2 Plat de "Salade" est en préparation pour le Restaurant "Saladier"

  Scénario: Récupérer aucun menu
    Quand la commande Multiple est paye
    Alors aucun menu pour le Restaurant "Saladier"