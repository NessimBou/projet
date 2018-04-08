# projet


#Journal de bord
09/02 : Documentation ok, ServiceRefused(ok), createUser et Login(ok)
13/02 : Logout dans User ok ( il faut creer le servlet logout)
18/02 : Creation de la classe Database et du fichier context.xml permettant d'instancier le datatSource, Changement de la fonction UserExist 
20/02 : DBstatic ok , test avecpyPhp ok implementation de certaines fonction db
23/02 : createUser fonctionne sur tomcat. Login fonctinne presque petite erreur a cause du parseInt a regler.
24/02 : Ajout des services, servlets et BD de addFriend et removeFriend. A tester.
26/02 :	Reecriture de la methode checkPassword, tres brouillon, a tester. 
		Implementation de listFriends (BD, Service, Servlet + web.xml), a tester aussi.
28/02 : changer les implementations de logout addFriend removeFriend listFriend et voir note 2) j'ai commencer a revoir checkPaswword mais renvoie tout le temps false
06/03 : CheckPassword ok, expireSession deconnecte l'user si il est connecté + de 30 min,L'heure de connexion affiche est la bonne
21/03 :ListFriend fonctionne, ajout de certaine fonctionne : getUserId,DeconnectionUrgente,SupprimerUser, fonctionne aussi sur serveur.	
8/04: on peut s'inscrire, se connecter, reste à relier au serveur 

#Reste a faire:


- Refaire le html avec moins de div
- implementer ajax 
- finir js
- tester en local puis serveur
- Ajout de derni�re minute : am�lioration de deleteMessage (cf TD1)


#note :
1)Integer.toString(int i) transforme un int en String


