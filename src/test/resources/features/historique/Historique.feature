



#language:fr
Fonctionnalité: Accès à l'historique

  Scénario: Erreur
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté
    Et que "Antoine" "Dupont" n'a jamais effectué de commande
    Quand "Antoine" "Dupont" veut accéder à l'historique
    Alors il obtient une liste de taille 0


  Scénario: Normal
    Etant donné que l'utilisateur "Antoine" "Griezmann" est connecté
    Et que "Antoine" "Griezmann" n'a jamais effectué de commande
    Et que L'utilisateur peut accéder aux restaurants suivant :
      | Chinois |
      | RoastBeef |
      | AfriqueDuSud |
      | SophiaRestaurantTech |
    Et que le restaurant "AfriqueDuSud" propose les menus suivant :
      | Tricheur | 5,00 |
      | Voleur | 3,00 |
    Et que "Antoine" "Griezmann" effectue une commande dans le restaurant "AfriqueDuSud"
    Quand "Antoine" "Griezmann" veut accéder à l'historique
    Alors il obtient une liste de taille 1
