#language:fr
Fonctionnalité: Rejoindre une commande groupe

  Contexte:
    Etant donné qu'il existe une commande groupe d ID 0

  Scénario: Rejoindre une commande groupe et ajouter une commande
    Etant donné que le nombre de commande est 0
    Quand  l utilisateur ajoute 1 commande à cette commande groupe
    Alors la commande Groupe possède la commande
    Et que le nombre de commande est 1

  Scénario: Supprimer une commande dans une commande groupe
    Etant donné que l utilisateur ajoute 1 commande à cette commande groupe
    Et que le nombre de commande est 1
    Quand l utilisateur supprime cette commande
    Alors le nombre de commande est 0

  Scénario: Supprimer une commande dans une commande groupe qui n'existe pas
    Etant donné que l utilisateur ajoute 1 commande à cette commande groupe
    Et que le nombre de commande est 1
    Quand l utilisateur supprime cette commande et la commande groupe n a pas cette commande
    Alors le nombre de commande est 1