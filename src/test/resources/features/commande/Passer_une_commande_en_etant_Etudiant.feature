#language:fr
Fonctionnalité: Passer une commande en etant Etudiant

  Contexte:
    Etant donné l'utilisateur "Antoine" "Dupont" est connecté et est "ETUDIANT"
    Et que l'utilisateur "Antoine" "Dupont" crée une commande
    Et que L'utilisateur peut accéder aux restaurants suivant :
      | Chinois |
      | RoastBeef |
      | RestaurantTech |
      | SophiaRestaurantTech |
    Et que le restaurant "RestaurantTech" propose les menus suivant :
      | Chine | 5,00 |
      | Japon | 3,00 |
    Et que l'utilisateur choisit le restaurant "RestaurantTech"
    Et que le restaurant applique un tarif spécial pour les "ETUDIANT" :
      | Chine | 3,00 |
      | Japon | 2,00 |

    Scénario:
      Quand l'utilisateur choisit le menu "Chine" à 3 €
      Alors le prix de la commande augmente de 3 €
