#language: fr
Fonctionnalité: Application des réductions

  Contexte:
    Etant donné que les utilisateurs connectés sont : (passDiscount)
      | String | String |
      | AntoineRugby | Dupont |
      | AntoineFoot | Griezmann |
      | OTP1 | Fiora |
      | OTP2 | Heimerdinger |
      | OTP3 | Yuumi |
      | OTP4 | Chomage |
      | X | OuRien |
    Et que l'utilisateur "AntoineRugby" "Dupont" est un "etudiant" (passDiscount)
    Et que l'utilisateur "AntoineFoot" "Griezmann" est un "professeur" (passDiscount)
    Et que l'utilisateur "OTP1" "Fiora" est un "personnel" (passDiscount)
    Et que l'utilisateur "OTP2" "Heimerdinger" est un "exterieur" (passDiscount)
    Et que l'utilisateur "OTP3" "Yuumi" est un "etudiant" (passDiscount)
    Et que l'utilisateur "OTP4" "Chomage" est un "normal" (passDiscount)
    Et que l'utilisateur "X" "OuRien" est un "normal" (passDiscount)

    Et que les utilisateur peut accéder aux restaurants suivant : (passDiscount)
      | Chinois |
      | RoastBeef |
      | AfriqueDuSud |
      | SophiaRestaurantTech |
    Et que le restaurant "AfriqueDuSud" propose les réductions suivant : (passDiscount)
      | Etudiant   | 20 |
      | Professeur | 5  |
      | Personnel  | 3  |
      | Exterieur  | 1  |
    Et que le restaurant "SophiaRestaurantTech" propose les réductions suivant : (passDiscount)
      | Etudiant   | 75 |
      | Professeur | 5  |
      | Personnel  | 3  |
      | Exterieur  | 1  |
    Et que le restaurant "AfriqueDuSud" propose une réduction de 40 (%) valable 10 jours pour les bon clients ayant effectué plus de 10 commandes. (passDiscount)
    Et que le restaurant "SophiaRestaurantTech" propose une réduction de 40 (%) valable 1 jours pour les bon clients ayant effectué plus de 10 commandes. (passDiscount)
    Et que le restaurant "AfriqueDuSud" propose les menus suivant : (passDiscount)
      | Tricheur | 5,00 |
      | Voleur | 3,00 |
    Et que le restaurant "SophiaRestaurantTech" propose les menus suivant : (passDiscount)
      | Londres | 15,00 |
      | Liverpool | 13,00 |
    Et que l'utilisateur "AntoineRugby" "Dupont" a effectué 9 commandes au restaurant "AfriqueDuSud" (passDiscount)
    Et que l'utilisateur "AntoineRugby" "Dupont" a effectué 9 commandes au restaurant "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "OTP4" "Chomage" a effectué 9 commandes au restaurant "AfriqueDuSud" (passDiscount)
    Et que l'utilisateur "OTP4" "Chomage" a effectué 9 commandes au restaurant "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "X" "OuRien" a effectué 9 commandes au restaurant "SophiaRestaurantTech" (passDiscount)


  Scénario: Réduction status Etudiant
    Quand l'utilisateur "OTP3" "Yuumi" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "OTP3" "Yuumi" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Alors le prix total de la commande doit être de 7,50 (passDiscount)

  Scénario: Réduction status Professeur
    Quand l'utilisateur "AntoineFoot" "Griezmann" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "AntoineFoot" "Griezmann" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Alors le prix total de la commande doit être de 28,50 (passDiscount)

  Scénario: Réduction status Personnel
    Quand l'utilisateur "OTP1" "Fiora" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "OTP1" "Fiora" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Alors le prix total de la commande doit être de 29,10 (passDiscount)

  Scénario: Réduction status Exterieur
    Quand l'utilisateur "OTP2" "Heimerdinger" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "OTP2" "Heimerdinger" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Alors le prix total de la commande doit être de 29,70 (passDiscount)


  Scénario: Réduction bon client
    Quand l'utilisateur "OTP4" "Chomage" choisit le restaurant de nom "AfriqueDuSud" (passDiscount)
    Et que l'utilisateur "OTP4" "Chomage" a effectué 1 commandes au restaurant "AfriqueDuSud" le "2019-12-12" à "12:00" (passDiscount)
    Et que l'utilisateur "OTP4" "Chomage" choisit les menus : (passDiscount)
      | Tricheur |
      | Tricheur |
    Et que l'utilisateur "OTP4" "Chomage" valide la commande pour le "2019-12-12" à "13:00" (passDiscount)
    Alors le prix total de la commande doit être de 6,00 (passDiscount)

  Scénario: Fin réduction bon client
    Quand l'utilisateur "OTP4" "Chomage" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "OTP4" "Chomage" a effectué 1 commandes au restaurant "SophiaRestaurantTech" le "2019-12-12" à "12:00" (passDiscount)
    Et que l'utilisateur "OTP4" "Chomage" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Et que l'utilisateur "OTP4" "Chomage" valide la commande pour le "2500-12-15" à "13:00" (passDiscount)
    Alors le prix total de la commande doit être de 30,00 (passDiscount)

  Scénario: Reduction bon client et etudiant
    Quand l'utilisateur "AntoineRugby" "Dupont" choisit le restaurant de nom "AfriqueDuSud" (passDiscount)
    Et que l'utilisateur "AntoineRugby" "Dupont" a effectué 1 commandes au restaurant "AfriqueDuSud" le "2019-12-12" à "12:00" (passDiscount)
    Et que l'utilisateur "AntoineRugby" "Dupont" choisit les menus : (passDiscount)
      | Tricheur |
      | Tricheur |
    Et que l'utilisateur "AntoineRugby" "Dupont" valide la commande pour le "2019-12-12" à "13:00" (passDiscount)
    Alors le prix total de la commande doit être de 4,00 (passDiscount)

  Scénario: Reduction supérieur à 100%
    Quand l'utilisateur "AntoineRugby" "Dupont" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "AntoineRugby" "Dupont" a effectué 1 commandes au restaurant "SophiaRestaurantTech" le "2019-12-12" à "12:00" (passDiscount)
    Et que l'utilisateur "AntoineRugby" "Dupont" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Et que l'utilisateur "AntoineRugby" "Dupont" valide la commande pour le "2019-12-12" à "13:00" (passDiscount)
    Alors le prix total de la commande doit être de 0,00 (passDiscount)

  Scénario: Prolongation de la réduction
    Quand l'utilisateur "X" "OuRien" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "X" "OuRien" a effectué 1 commandes au restaurant "SophiaRestaurantTech" le "2019-12-12" à "12:00" (passDiscount)
    Et que l'utilisateur "X" "OuRien" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Et que l'utilisateur "X" "OuRien" valide la commande pour le "2019-12-12" à "13:00" (passDiscount)
    Et que le prix de la commande est de 18,00 (passDiscount)
    Et que l'utilisateur "X" "OuRien" paye la commande (passDiscount)
    Et que l'utilisateur "X" "OuRien" a effectué 9 commandes au restaurant "SophiaRestaurantTech" le "2019-12-13" à "11:00" (passDiscount)
    Quand l'utilisateur "X" "OuRien" choisit le restaurant de nom "SophiaRestaurantTech" (passDiscount)
    Et que l'utilisateur "X" "OuRien" choisit les menus : (passDiscount)
      | Londres |
      | Londres |
    Et que l'utilisateur "X" "OuRien" valide la commande pour le "2019-12-13" à "15:00" (passDiscount)
    Alors le prix total de la commande doit être de 18,00 (passDiscount)