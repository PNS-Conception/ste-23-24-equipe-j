#language:fr
Fonctionnalité: Changer status d'une commande groupe

  Scénario: Changer status commande à en préparation
    Etant donné que la commande groupé possède 1 commande
    Quand l'utilisateur paye la commande
    Alors la commande est "EN_PREPARATION"
    Et que la commande groupe est "EN_PREPARATION"

  Scénario: Changer status commande à Prête mais pas toutes les commandes
    Etant donné que la commande groupé possède 2 commande
    Quand le restaurateur passe une commande "PRETE"
    Alors la commande est "PRETE"
    Et la commande groupe est "EN_ATTENTE"

  Scénario: Changer status commande à Prête et toutes les commandes aussi
    Etant donné que la commande groupé possède 2 commande
    Et qu'une commande est "PRETE"
    Quand le restaurateur passe une commande "PRETE"
    Alors la commande est "PRETE"
    Et la commande groupe est "PRETE"

  Scénario: Changer status commande Groupe
    Etant donné que la commande groupé possède 1 commande
    Quand l'utilisateur passe la commande groupé "PRETE"
    Alors une "Impossible de changer l'état d'une commande de groupe" doit être retournée