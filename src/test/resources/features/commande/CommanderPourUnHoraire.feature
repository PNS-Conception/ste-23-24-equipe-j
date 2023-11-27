#language:fr

Fonctionnalité: Commander pour un creneau

  Contexte:
    Etant donné un restaurant  "Restoche"
    Et qu'il propose les menus suivant :
      | Pate | 5,00 |
      | Riz  | 4,00 |
    Et qu'il a possède uniquement des créneaux  "quotidien":
      | 14:30 | 15:00 | 10 | lundi |
      | 15:10 | 15:40 | 10 | mardi |

    Et un utilisateur "Antoine" "Marie"


  Scénario: Demander la liste des créneaux
    Quand je demande la liste des creneaux pour le restaurant "Restoche"
    Alors j'obtient les creneaux "quotidien":
      | 14:30 | 15:00 | 10 | lundi |
      | 15:10 | 15:40 | 10 | mardi |

  Scénario: Demander la liste des créneaux pour une date
    Quand je demande la liste des creneaux pour le restaurant "Restoche" pour le "2024-03-25"
    Alors j'obtient les creneaux :
      | 14:30 |
      | 15:00 |
      | 10    |
      | lundi |

  Scénario: Commander un menu un jour quotidien
    Quand je commande à "Restoche" "lundi" prochain  entre "14:30" et "15:00" un menu:
      | Pate |
      | 5,00 |
    Alors  "Restoche" contient pour "lundi" prochain:
      | 14:30 |
      | 15:00 |
      | 1     |
      |30     |

  Scénario: Commander un menu pour une date précise
    Quand je commande à "Restoche" "2024-01-02" prochain  entre "15:10" et "15:30" un menu:
      | Pate |
      | 5,00 |
    Alors  "Restoche" contient pour "2024-01-02" prochain:
      | 15:10 |
      | 15:40 |
      | 1     |
      |30     |





