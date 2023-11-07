#language:fr
Fonctionnalité: Ajout d'une commande groupé simple

  Contexte:
    Etant donné qu'une commande groupé contient 3 commandes et s'élevant à 30€

  Scénario: Ajout d'une commande individuelle dans la commande groupé
    Quand "Hugo" "Hugo" donne l'identifiant 0 de la commande groupé
    Et que la commande groupé existe
    Et que "Hugo" "Hugo" ajoute sa commande à la commande groupé
    Alors la commande groupé contient 4 commandes et s'élève à 40€
    Et la commande groupé contient cette commande

  Scénario: Ajout d'une commande individuelle dans la commande groupé et que l'id est non valide
    Quand "Hugo" "Hugo" donne l'identifiant 1 de la commande groupé
    Et que la commande groupé n'existe pas
    Alors une erreur "Pas d'id pour cette commande groupé" est récupérer

  Scénario: Suppression d'une commande individuelle dans la commande groupé
    Quand "Hugo" "Hugo" donne l'identifiant 0 de la commande groupé
    Et que la commande groupé existe
    Et que "Hugo" "Hugo" ajoute sa commande à la commande groupé
    Et qu'il supprime sa commande de la commande groupé
    Alors la commande groupé contient 3 commandes et s'élève à 30€
    Et la commande groupé ne contient pas cette commande