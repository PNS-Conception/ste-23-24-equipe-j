#language:fr
Fonctionnalité: Ajout d'un menu buffet dans une commande buffet

  Contexte:
    Etant donné que le "PROFESSEUR" "sheldon" "cooper" possède une commande Buffet avec "Hugo" "lo" comme destinataire

  Scénario: Menu ajouté avec succès
    Quand il ajoute le menu Buffet 'crêpe'
    Alors le menu est ajouté à la commande

  Scénario: Commande buffet sans destinataire non créer
    Quand  il ajoute le menu classique 'pate'
    Alors une erreur est renvoyé "mauvais menu ajouté"
    Et le menu n'est pas ajouté à la commande

  Scénario: Commande buffet sans un staff destinataire
    Quand il ajoute le plat 'pate'
    Alors une erreur est renvoyé "Impossible d'ajouter un plat"
    Et le menu n'est pas ajouté à la commande