#language:fr

  Fonctionnalité: Noter le livreur

    Contexte:
      Etant donné que l'utilisateur "Antoine" "Dupont" est connecté (pass)
      Et que le livreur "Peato" "Mauvaka" a la liste de notes suivante :
        | 5 |
        | 4 |
        | 5 |
        | 3 |
        | 2 |
        | 4 |

      Scénario:
        Quand l'utilisateur attribue la note de 4 au livreur
        Alors la note a été ajouté à la liste de notes du livreur
        Et la note du livreur à été modifiée par l'ajout de cette nouvelle note : 4
