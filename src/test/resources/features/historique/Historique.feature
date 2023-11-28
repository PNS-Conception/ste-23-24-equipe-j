



#language:fr
Fonctionnalité: Accès à l'historique

  Scénario: Erreur
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté (passHistorique)
    Et que "Antoine" "Dupont" n'a jamais effectué de commande (passHistorique)
    Quand "Antoine" "Dupont" veut accéder à l'historique (passHistorique)
    Alors il obtient une liste de taille 0 (passHistorique)


  Scénario: Normal
    Etant donné que l'utilisateur "Antoine" "Griezmann" est connecté (passHistorique)
    Et que "Antoine" "Griezmann" n'a jamais effectué de commande (passHistorique)
    Et que L'utilisateur peut accéder aux restaurants suivant: (passHistorique)
      | Chinois |
      | RoastBeef |
      | AfriqueDuSud |
      | SophiaRestaurantTech |
    Et que les menus proposés par le restaurant "AfriqueDuSud" sont les suivant : (passHistorique)
      | Tricheur | 5,00 |
      | Voleur | 3,00 |
    Et que "Antoine" "Griezmann" effectue une commande dans le restaurant "AfriqueDuSud" (passHistorique)
    Quand "Antoine" "Griezmann" veut accéder à l'historique (passHistorique)
    Alors il obtient une liste de taille 1 (passHistorique)
