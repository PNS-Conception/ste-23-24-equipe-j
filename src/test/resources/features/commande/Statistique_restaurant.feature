



#language:fr
Fonctionnalité: Accès aux statistiques des restaurants

  Scénario: Normal
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté
    Et que le restaurant "ADS" n'a jamais effectué de commande
    Quand "Antoine" "Dupont" accède à la page des statistiques du restaurant "ADS", il obtient une valeur 0.
    Alors il effectue une commande dans ce restaurant "ADS".
    Quand "Antoine" "Dupont" accède à la page des statistiques du restaurant "ADS", il obtient une valeur 1.
