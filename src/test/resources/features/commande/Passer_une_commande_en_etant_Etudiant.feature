#language:fr
Fonctionnalité: Passer une commande en etant Etudiant

  Contexte:
    Etant donné l'utilisateur "Antoine" "Dupont" est connecté et est "ETUDIANT" (pass)
    Et que "Antoine" "Dupont" crée une commande (pass)
    Et que L'utilisateur peut accéder aux restaurants suivant : (pass)
      | Chinois |
      | RoastBeef |
      | RestaurantTech |
      | SophiaRestaurantTech |
    Et que le restaurant "RestaurantTech" propose les menus suivant : (pass)
      | Chine | 5,00 |
      | Japon | 3,00 |
    Et que l'utilisateur choisit le restaurant "RestaurantTech" (pass)
    Et que le restaurant "RestaurantTech" applique un tarif spécial pour les "ETUDIANT" :
      | Chine | 3,00 |
      | Japon | 2,00 |

    Scénario:
      Quand l'utilisateur choisit le menu "Chine" à 3 € (pass)
      Alors le prix de la commande est de 3 €
