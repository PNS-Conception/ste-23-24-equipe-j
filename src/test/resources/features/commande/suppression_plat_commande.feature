#language:fr
Fonctionnalité: Suppression Plat à la Commande

  Scénario: Suppression d'un plat
    Etant donné que l'utilisateur a une commande
    Et avec 1 plat "Hamburger" et que ce plat coûte 7€
    Quand l'utilisateur supprime le plat "Hamburger"
    Alors la commande contient 0 plat "Hamburger"
    Et que la commande coûte 0€

  Scénario: Suppression d'un plat avec plusieurs quantités
    Etant donné que l'utilisateur a une commande
    Et avec 3 plat "Pate" et que ce plat coûte 7€
    Quand l'utilisateur supprime le plat "Pate"
    Alors la commande contient 2 plat "Pate"
    Et que la commande coûte 14€

  Scénario: Suppression d'un plat qui n'existe pas
    Etant donné que l'utilisateur a une commande
    Quand l'utilisateur supprime le plat "Hamburger"
    Alors la commande coûte 0€