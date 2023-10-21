#language:fr
Fonctionnalité: Ajouter un restaurant

  Contexte:
    Etant donné que le restaurateur de position 1,1 a la liste des commandes en attente avec les menus :
      | Chinois |
      | Français |
    Et que le livreur "Antoine" "Dupont" est disponible au coordonnées 0 0
    Et que le livreur "Peato" "Mauvaka" est disponible au coordonnées 9 9
    Et que la commande avec le menu d'ID 0 à le status "PRETE"

  Scénario:  Normal
    Quand le restaurateur valide la commande avec le menu d'ID 0 avec le status "PRETE_POUR_LIVRAISON"
    Alors le livreur peut retirer la commande avec le menu d'ID 0, ie la commande est attribué au livreur le plus proche : "Antoine" "Dupont"

