



#language:fr
Fonctionnalité: Accès aux statistiques des restaurants

  Contexte:
    Etant donné que L'utilisateur peut accéder aux restaurants suivant : (passStatistiqueRestaurant)
      | Chinois |
      | RoastBeef |
      | AfriqueDuSud |
      | SophiaRestaurantTech |
    Et que le restaurant "AfriqueDuSud" propose les menus suivant : (passStatistiqueRestaurant)
      | Tricheur | 5,00 |
      | Voleur | 3,00 |

  Scénario: Normal
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté (passStatistiqueRestaurant)
    Et que le restaurant "AfriqueDuSud" n'a jamais effectué de commande (passStatistiqueRestaurant)
    Quand "Antoine" "Dupont" accède à la page des statistiques du restaurant "AfriqueDuSud", il obtient une valeur 0. (passStatistiqueRestaurant)
    Alors "Antoine" "Dupont" effectue une commande dans le restaurant "AfriqueDuSud". (passStatistiqueRestaurant)
    Quand "Antoine" "Dupont" accède à la page des statistiques du restaurant "AfriqueDuSud", il obtient une valeur 1. (passStatistiqueRestaurant)
