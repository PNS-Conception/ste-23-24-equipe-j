#language:fr
Fonctionnalité: Notification à un Livreur

  Contexte:
    Etant donné l'utilisateur est "Antoine" "griezmann"

  Scénario:  Recevoir notification quand le livreur est disponible
    Quand il paye sa commande et le paiement est accepte
    Alors le status de la commande est "EN_PREPARATION"

  Scénario:  Ne pas recevoir la notification quand le livreur n'est pas disponible
    Quand il paye sa commande et le paiement n'est pas accepte
    Alors le status de la commande est "EN_ATTENTE"