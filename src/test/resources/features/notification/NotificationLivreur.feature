#language:fr
Fonctionnalité: Notification à un Livreur

  Contexte:
    Etant donné les livreurs "Timon" "RoiLion" et "Pumba" "RoiLion"

  Scénario:  Recevoir notification quand le livreur est disponible
    Etant donné que "Timon" "RoiLion" est disponible
    Quand la commande change de status à "PRETE"
    Alors le livreur "Timon" "RoiLion" doit recevoir une notification
    Et que la commande est de status "EN_LIVRAISON"

  Scénario:  Ne pas recevoir la notification quand le livreur n'est pas disponible
    Etant donné que "Pumba" "RoiLion" est disponible
    Et que "Timon" "RoiLion" n'est pas disponible
    Quand la commande change de status à "PRETE"
    Alors le livreur "Timon" "RoiLion" doit recevoir une notification
    Et que la commande est de status "EN_LIVRAISON"