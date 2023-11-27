#language:fr
Fonctionnalité: Ajouter un horaire

  Contexte:
    Etant donné un restaurant "Restoche"
    Et que j'ai une liste d'horaires:
      | debut | fin   | capacite_par_slot |
      | 12:00 | 13:30 | 10                |

  Scénario: Ajout correct quotidien
    Quand j'ajoute l'horaire "quotidien":
      | 14:30 |
      | 15:00 |
      | 10    |
      |lundi  |
    Alors  la liste d'horaire contient:
      | 14:30 |
      | 15:00 |
      | 10    |
      |lundi  |
    Et la taille de la liste a augmenté de 1
    Et la capacité du restaurant a augmenté de 30
  Scénario: Ajout correct exceptionnel
    Quand j'ajoute l'horaire "exceptionnel":
      | 14:30 |
      | 15:00 |
      | 10    |
      |2024-02-18 |
    Alors  la liste d'horaire contient:
      | 14:30 |
      | 15:00 |
      | 10    |
      |2024-02-18  |
    Et la taille de la liste a augmenté de 1
    Et la capacité du restaurant a augmenté de 30
  Scénario: Ajout horaire incluant un autre
    Quand  j'ajoute l'horaire "quotidien":
      |12:30|
      |12:45|
      |10   |
      |lundi|
    Alors l'horaire n'est pas ajouté
  Scénario: Capacite  non spécifié
    Quand  j'ajoute l'horaire "quotidien":
      |15:00|
      |15:20|
      | null|
      |mardi|
    Alors la capacite n'a pas augmenté
