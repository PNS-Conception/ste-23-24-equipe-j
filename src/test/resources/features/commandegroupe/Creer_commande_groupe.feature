#language:fr
Fonctionnalité: Créer une commande groupe

  Contexte:
    Etant donné un utilisateur "Bob" "Patrick"

  Scénario: Création commande groupe
    Quand l utilisateur crée une commande groupé
    Alors le commande d'ID 0 est créé
    Et le créateur de cette commande est "Bob" "Patrick"
    Et que l utilisateur possède une commande groupé d ID 0

    Scénario: Suppression commande groupe
      Etant donné que l utilisateur possède une commande groupé d ID 0
      Alors quand l utilisateur supprime la commande
      Et la commande d ID 0 n existe plus