- Le patron X doit être : Patron de Strategy
    - Utilisation pour les différents type de notification (SMS, Email...)
- Le patron Y doit être : Patron de Null Object
    - Notification neutre
- Le patron Z doit être : Patron de State
    - L'état de l'utilisateur (DoNotDisturb, Disconnected, Connected)

Grâce à la combinaison de ces différents architectures, on peut facilement ajouté des nouveaux états pour les utilisateurs et des nouveaux systèmes d'envoie de notification. 