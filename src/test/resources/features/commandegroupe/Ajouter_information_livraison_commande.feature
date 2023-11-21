#language:fr
Fonctionnalité: Changer information livraison commande

  Scénario: Changer status commande à en préparation
    Etant donné que la commande ne possède pas d'informations de livraison
    Quand l'utilisateur veut ajouter la date "2023-11-10" et le lieu "Paris"
    Alors la commande groupe contiendra la date "2023-11-10" et le lieu "Paris"

  Scénario: Changer status commande à Prête mais pas toutes les commandes
    Etant donné que  la commande possède la date "2023-11-11" et le lieu "Nice"
    Quand  l'utilisateur veut ajouter la date "2023-11-10" et le lieu "Paris"
    Alors la commande groupe contiendra la date "2023-11-11" et le lieu "Nice"