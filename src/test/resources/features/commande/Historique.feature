



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
    Quand "Antoine" "Griezmann" veut accéder à l'historique
    Alors il obtient une liste de taille 0
