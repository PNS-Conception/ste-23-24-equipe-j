#language:fr

Fonctionnalité: Ajouter une commande multiple dans une commande

  Contexte:
    Etant donné qu'il y a une commande groupe d'ID 0

  Scénario: Ajout d'une commande multiple dans une commande groupe
    Quand il y a une commande multiple d ID 1 avec 1 plat de "Pate" et 2 plat de "Salade" du restaurant "Gastronomie" ajouté dans commande groupe d'ID 0
    Alors la commande groupe possède 1 commande contenant 1 plat de "Pate" et 2 plat de "Salade" du restaurant "Gastronomie"

  Scénario:
    Etant donné que il y a une commande multiple d ID 1 avec 1 plat de "Pate" du restaurant "Gastronomie" et 2 plat de "Salade" du restaurant "Saladier" ajouté dans commande groupe d'ID 0
    Quand la commande d'ID 1 est payé
    Alors le restaurateur du restaurant "Gastronomie" voit le plat "Pate"
    Et que le restaurateur du restaurant "Gastronomie" voit le plat "Salade"