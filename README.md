# projet


#Journal de bord
09/02 : Documentation ok, ServiceRefused(ok), createUser et Login(ok)
13/02 : Logout dans User ok ( il faut creer le servlet logout)
18/02 : Creation de la classe Database et du fichier context.xml permettant d'instancier le datatSource, Changement de la fonction UserExist 
20/02 : DBstatic ok , test avecpyPhp ok implementation de certaines fonction db
23/02 : createUser fonctionne sur tomcat. Login fonctinne presque petite erreur a cause du parseInt a regler.
24/02 : Ajout des services, servlets et BD de addFriend et removeFriend. A tester.
26/02 :	Réecriture de la méthode checkPassword, très brouillon, à tester. 
		Implémentation de listFriends (BD, Service, Servlet + web.xml), à tester aussi.
		

#Reste a faire:
1) Regler login
2) implementer les fonctions messages
3) tester en local puis serveur
4) listFriend(pas demandé pour cette semaine)
5) Ajout de dernière minute : amélioration de deleteMessage (cf TD1)
6) GetProfil et SetProfil de User (partie client)

#note :
Integer.toString(int i) transforme un int en String
