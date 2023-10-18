#language:fr
Fonctionnalité: Ajouter un restaurant


  Scénario:  Normal
    Etant donné que  l' Administrateur du campus est authentifié
    Et  les champs nom,position:
      | NomDuChamp | Valeur            |
      | nom        | Ru nice toulon    |
      | position   | 26 route de Turin |
    Quand il valide
    Alors "Ru nice toulon" de  position "26 route de Turin" est ajouté

  Scénario: Déjà stocké
    Etant donné que l' Administrateur du campus est authentifié
    Et  les champs nom,position:
      | NomDuChamp | Valeur            |
      | nom        | Ru nice toulon    |
      | position   | 26 route de Turin |
    Et que "Ru nice toulon" de position "26 route de Turin" existe déjà dans le système
    Quand il valide
    Alors "Ru nice toulon" de position "26 route de Turin" n'est pas ajouté une seconde fois

  Scénario: Position oublié
    Etant donné que  l' Administrateur du campus est authentifié
    Et  le champ nom:
      | NomDuChamp | Valeur         |
      | nom        | Ru nice toulon |
    Quand il valide
    Alors "Ru nice toulon" n'est pas ajouté






