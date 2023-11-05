#language:fr
Fonctionnalité: Ajout d'une commande groupé simple

  Contexte:
    Etant donné qu'une commande groupé contient 3 menus
    Quand l'utilisateur donne l'identifiant de la commande groupé

  Scénario: Ajout d'une commande individuelle dans la commande groupé
    Quand l'identifiant de la commande groupé est valide
    Et que l'utilisateur ajoute sa commande à la commande groupé
    Alors la commande groupé contient 4 commandes
    Et la commande groupé contient cette commande

  Scénario: Ajout d'une commande individuelle dans la commande groupé et que l'id est non valide
    Quand l'identifiant de la commande groupé est non valide
    Alors une erreur "pas d'id pour cette commande groupé" est récupérer

  Scénario: Suppression d'une commande individuelle dans la commande groupé
    Quand l'identifiant de la commande groupé est valide
    Et que l'utilisateur à ajouter sa commande à la commande groupé
    Et que l'utilisateur supprime sa commande de la commande groupé
    Alors la commande groupé contient 3 commandes
    Et la commande groupé ne contient pas cette commande