#language:fr
Fonctionnalité: Notification à un Utilisateur

  Contexte:
    Etant donné les utilisateurs "Antoine" "Dupont" et "Jean" "Dupont"

  Scénario:  Recevoir notification quand la commande est prête
    Etant donné que "Antoine" "Dupont" effectue une commande
    Quand la commande change de status "PRETE"
    Alors "Antoine" "Dupont" doit recevoir une notification

  Scénario:  Recevoir notification quand la commande est livrée
    Etant donné que "Antoine" "Dupont" effectue une commande
    Quand la commande change de status "LIVREE"
    Alors "Antoine" "Dupont" doit recevoir une notification

  Scénario:  Ne pas recevoir la notification quand la commande lui appartient pas
    Etant donné que "Jean" "Dupont" effectue une commande
    Quand la commande change de status "LIVREE"
    Alors "Jean" "Dupont" doit recevoir une notification
    Et que "Antoine" "Dupont" ne dois pas recevoir une notification