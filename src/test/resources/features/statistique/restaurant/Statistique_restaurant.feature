



#language:fr
Fonctionnalité: Accès aux statistiques des restaurants

  Contexte:
    Etant donné que L'utilisateur peut accéder aux restaurants suivant :
      | Chinois |
      | RoastBeef |
      | AfriqueDuSud |
      | SophiaRestaurantTech |
    Et que le restaurant "AfriqueDuSud" propose les menus suivant :
      | Tricheur | 5,00 |
      | Voleur | 3,00 |

  Scénario: Normal
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté
    Et que le restaurant "AfriqueDuSud" n'a jamais effectué de commande
    Quand "Antoine" "Dupont" accède à la page des statistiques du restaurant "AfriqueDuSud", il obtient une valeur 0.
    Alors "Antoine" "Dupont" effectue une commande dans le restaurant "AfriqueDuSud".
    Quand "Antoine" "Dupont" accède à la page des statistiques du restaurant "AfriqueDuSud", il obtient une valeur 1.
