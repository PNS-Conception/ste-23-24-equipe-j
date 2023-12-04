#language:fr

Fonctionnalité: Création et ajout d'un menu ou plat dans un restaurant

  Contexte:
    Etant donné l'utilisateur "shenron" "leDragon" qui possède une commande Multiple
    Et que cette commande possède un menu "Tartiflette" du restaurant "Gastronomie"

  Scénario: Ajout Menu du même restaurant
    Quand il ajoute le plat "Salade" du restaurant "Gastronomie"
    Alors sa commande Multiple possède 1 commande simple
    Et que sa commande simple possède 2 menu

  Scénario: Ajout Menu avec différent restaurant
    Quand il ajoute le plat "Salade" du restaurant "Saladier"
    Alors sa commande Multiple possède 2 commande simple
    Et chaque commande simple possède 1 menu