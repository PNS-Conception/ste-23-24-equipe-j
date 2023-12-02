#language:fr
Fonctionnalité: Ajout de menus afterwork dans la commande afterworks

  Contexte:
    Etant donné qu'un utilisateur se nomme "Hector" "Le H"
    Et qu'il possède une commande afterwork avec 35 personnes

  Scénario: Menu ajouté avec succès
    Quand il ajoute le menu afterworks "crêpe" à 12€
    Alors le menu est ajouté à la commande

  Scénario: Menu non ajouté avec succès
    Quand il ajoute le menu classique "pate" à 10€
    Alors une erreur est renvoyé "Impossible d'ajouter un menu qui n'est pas afterworks"
    Et le menu n'est pas ajouté

  Scénario: Plat non ajouté avec succès
    Quand il ajoute le plat "pate" à 5€
    Alors une erreur est renvoyé "Impossible d'ajouté un plat"
    Et le menu n'est pas ajouté