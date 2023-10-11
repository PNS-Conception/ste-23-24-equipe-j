#language:fr
  Fonctionnalité: Ajouter un restaurant
    Scénario:  Normal
      Etant donné que  l' Administrateur du campus est authentifié
      Et qu'il a renseigné  le nom, localisation, la catégorie
      Quand il valide
      Alors le restaurant est ajouté et apparaît sur son tableau de bord

    Scénario: Déjà stocké
      Etant donné que l' Administrateur du campus est authentifié
      Et qu'il a renseigné   le nom, localisation, la catégorie
      Quand il valide
      Alors le restaurant n'est pas ajouté  et le système indique que le restaurant existe déjà
    Scénario: Localisation oublié
      Etant donné que  l' Administrateur du campus est authentifié
      Et qu'il a renseigné   le nom, la catégorie
      Quand il valide
      Alors le restaurant n'est pas ajouté  et  le système indique qu'il lui manque une information obligatoire la localisation






