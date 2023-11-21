



#language:fr
Fonctionnalité: Accès aux statistiques des utilisateurs

  Scénario: Normal
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté
    Et que "Antoine" "Dupont" n'a jamais effectué de commande
    Quand "Antoine" "Dupont" accède à sa page de statistique, il obtient une valeur 0.
    Alors il effectue une commande dans ce restaurant "ADS".
    Quand "Antoine" "Dupont" accède à sa page de statistique, il obtient une valeur 1.
