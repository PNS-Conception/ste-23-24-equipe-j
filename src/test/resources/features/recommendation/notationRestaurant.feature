#language:fr

Fonctionnalité: Noter le restaurant

  Contexte:
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté (pass)
    Et que le restaurant "RestaurantTech" a la liste de notes suivante :
      | 3 |
      | 4 |
      | 5 |
      | 3 |
      | 2 |
      | 4 |

  Scénario:
    Quand l'utilisateur attribue la note de 3 au restaurant
    Alors la note a été ajouté à la liste de notes du restaurant
    Et la note du restaurant à été modifiée par l'ajout de cette nouvelle note : 4