#language:fr

Fonctionnalité: Noter l'utilisateur

  Contexte:
    Etant donné que le livreur "Peato" "Mauvaka" a livré l'utilisateur "Antoine" "Dupont"
    Et que l'utilisateur a la liste de notes suivante pour le retard:
      | 3 |
      | 4 |
      | 5 |

    Et que l'utilisateur a la liste de notes suivante pour l'amabilité:
      | 3 |
      | 2 |
      | 4 |

  Scénario:
    Quand le livreur attribue les notes de 3 et 5 à l'utilisateur
    Alors les notes ont été ajoutées aux listes de notes de l'utilisateur
    Et les notes de l'utilisateur ont été modifiées par l'ajout de ces nouvelles notes : 3 et 5