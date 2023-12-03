#language:fr
Fonctionnalité: Création d'une commande Buffet

  Contexte:
    Etant donné un "PROFESSEUR" se nommant "sheldon" "cooper"
    Et un "PROFESSEUR" se nommant "leonard" "Hofstadter"
    Et un "ETUDIANT" se nommant "hugo" "leau"

  Scénario: Création de la commande Buffet Reussi
    Quand "sheldon" "cooper" crée la commande en ajoutant "leonard" "Hofstadter" comme destinataire
    Alors la commande Buffet d'ID 0 est créé

  Scénario: Commande buffet sans destinataire non créer
    Quand "sheldon" "cooper" crée la commande en ajoutant "" "" comme destinataire
    Alors une erreur est retourner "l'usager destinataire n'existe pas"
    Et la commande Buffet d'ID 0 n'est pas créé

  Scénario: Commande buffet sans un staff destinataire
    Quand "hugo" "leau" crée la commande en ajoutant "leonard" "Hofstadter" comme destinataire
    Alors une erreur est retourner "le créateur de cette commande n'est pas un staff de l'université"
    Et la commande Buffet d'ID 0 n'est pas créé