--Ici est un exemple de base sql, pour la remlir avec des vrai valeurs, un méthode sur arduino a été créé (nom: searchalllight) qui renvoie un JSON
--On peut récupérer ce JSON et, avec les méthodes dans Light Controller, modifier notre table de manière adéquate.
--Nous n'avons pas implémenté totalement cette fonction car nous n'avons pas plusieurs lampes, et nous voulions avoir une base de donnée un peu plus riche
--meme si fictive

INSERT INTO BUILDING(ID,NAME) VALUES (1,'Bat1');
INSERT INTO BUILDING(ID,NAME) VALUES (2,'Bat2');

INSERT INTO ROOM(ID, NAME, FLOOR,BUILDING_ID) VALUES(1, 'Room1', 1,1);
INSERT INTO ROOM(ID, NAME, FLOOR,BUILDING_ID) VALUES(2, 'Room2', 1,2);

INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (10,'ON',1,0,0,1);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (11,'OFF',15,0,10,1);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (12,'OFF',25,0,20,2);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (13,'OFF',35,0,30,2);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (14,'OFF',56,0,40,2);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (15,'OFF',90,0,50,2);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (16,'OFF',96,0,60,2);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (17,'OFF',63,0,70,2);
INSERT INTO LIGHT(ID,STATUS,BRI,COLOUR,SAT, ROOM_ID) VALUES (18,'OFF',189,0,80,2);