insert into user (username, password, email, enabled, role) values ('user1', '$2y$12$fmAzYbcGyahi70/Wned8kOhwnpfDehK4AKcqU/yhsEvJL7FeIaBD6','user1@valami.com', true, 'ROLE_ADMIN');
insert into user (username, password, email, enabled, role) values ('user2', '$2y$12$St4SDwuOAZw0eco.ycjoZunMsYJDJl8LoDGMnnzITGD9.At6bQFNS', 'user2@valami.com',true, 'ROLE_USER');



insert into recipe(title,ingredients,instructions,category,status,rating,image) values ('Paprikás krumpli', '1 kg krumpli, 1 nagy fej vöröshagyma, pirospaprika, só, bors', 'A vöröshagymát kis olajon megpirítjuk. Hozzáadjuk a felvágott krumplit, majd felötnjük annyi vízzel, hogy ellepje. Ízlés szerint fűszerezzük.','MAINCOURSE','PUBLIC','LIKE', 'paprikaskrumpli');
insert into recipe(title,ingredients,instructions,category,status,rating,image) values ('Csicseriborsó pörkölt', '2 konzerv csicseriborsó, 1 nagy fej vöröshagyma, 2-3 gerezd fokhagyma, 1 db paradicsom, pirospaprika, só, bors', 'A hagymát és fokhagymát megpirítjuk, majd hozzáadjuk az apróra vágott paradicsomot. Pár percig főzzük, majd hozzáadjuk a csicseriborsót. Felöntjük vízzel, ízlés szerint fűszerezzük. Tetszőleges körettel tálaljuk.', 'MAINCOURSE','PUBLIC','LIKE', 'csicseri');
insert into recipe(title,ingredients,instructions,category,status,rating,image) values ('Zöldborsó fasírt', 'kb. 400 g zöldborsó, 1 fej hagyma, fokhagyma ízlés szerint, zabpehely (amennyit felvesz), 1 ek. darált lenmag, só, bors, pirospaprika', 'A borsót megfőzzük (konzerv esetén nem szükséges), hozzávalókat a zabpehely kivételével botmixerrel felaprítjuk. A lenmaghoz 3 ek. vizet adunk, és egy picit állni hagyjuk, majd miután besűrűsödött hozzáadjuk a masszához. Apránként hozzáadjuk a zabpelyhet, amíg jól formázható állagot nem kapunk. Fasírt formákat alakítunk és sütőben vagy serpenyőben kisütjük.', 'MAINCOURSE','PUBLIC','LIKE', 'zoldborso');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Kukorica krémleves', '1 kg kukorica, 1 nagy fej hagyma, fokhagyma ízlés szerint, só, bors, szerecsendió', 'A hagymát és fokhagymát megpirítjuk, majd hozzáadjuk a kukoricát. Felöntjük alaplével vagy vízzel (kb. 1,5 liter). Ha a kukorica puhára főtt, összeturmixoljuk és ízlés szerint fűszerezzük.', 'SOUP','PUBLIC','LIKE', '2','kukoricaleves');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Brokkoli krémleves', '500 g brokkoli, 1 nagy fej hagyma, fokhagyma ízlés szerint, só, bors', 'A hagymát és fokhagymát megpirítjuk, majd hozzáadjuk a brokkolit. Felöntjük alaplével vagy vízzel (kb. 1,5 liter). Ha a brokkoli puhára főtt, összeturmixoljuk és ízlés szerint fűszerezzük.', 'SOUP','PUBLIC','LIKE', '2', 'brokkolileves');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Zöldségleves', '3-4 db krumpli, 1 vöröshagyma, 3-4 db sárgarépa, 1 db karalábé, 1 db zeller, só, bors', 'A zöldségeket felaprítjuk és kb. 1,5 liter alaplében megfőzzük. Ízlés szerint fűszerezzük.', 'SOUP','PUBLIC','LIKE', '1','zoldsegleves');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Csokis keksz', '2 csésze liszt, 1 csipet só, 0.5 zacskó sütőpor, 1 csésze barnacukor, 0.5 csésze olaj, 0.25 csésze víz, 1 tábla étcsoki', 'A száraz és a nedves hozzávalókat külön-külön összekeverjük. A nedveshez hozzáadjuk az apró kockákra vágott étcsokoládét, majd az egészet összegyúrjuk. Diónyi golyókat formálunk majd kissé kilapítjuk őket. 180 fokra előmelegített sütőben 12 perc alatt kisütjük', 'DESSERT','PUBLIC','LIKE', '1','csokiskeksz');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Csokis muffin', '2,5 dl meleg víz, egy csipet só, 10 dkg cukor, 25 dkg liszt, 5 dkg olaj, 1/2 db citrom leve, 2 evőkanál kakaópor, 12 g sütőpor', 'Egy tálban kimérjük a száraz alapanyagokat a nádcukor kivételével, egy másik edényben a nádcukorral a nedves alapanyagokat. Kipapírozott muffinsütőbe adagoljuk, és kb. 15 perc alatt kisütjük. Csokidarabokkal díszíthetjük.', 'DESSERT','PUBLIC','LIKE', '2','muffin');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Chili sin carne ', '1 nagy fej vöröshagyma, 2 gerezd fokhagyma, 50 dkg bab, 10 dl paradicsomszósz, 1 db zöldpaprika, 40 dkg csemegekukorica, 1 db babérlevél, 1 ek cukrozatlan kakaópor, 1 kk fahéj, 1 db chili, só, bors', 'A hagymát felkockázzuk és kevés olajon megpirítjuk. Mehet rá a fokhagyma és a chili. Öntsük rá a babot, és öntsük fel annyi vízzel, amennyi épp ellepi. Tegyük bele a babérlevelett, borsot, és fedő alatt főzzük félpuhára. Ekkor mehet bele a paradicsom és a felkockázott paprika. Ha konzerv babbal készítjük, a hagymás pontnál csak öntsük rá a babot a hagymára a paradicsomszósszal. Fűszerezzük, kóstoljuk. Rotyogtassuk vagy fél órát - vagy ugye amíg a bab megpuhul -, majd az utolsó 10 percben csapjuk hozzá a kukoricát.', 'MAINCOURSE','PUBLIC','LIKE', '2','chili');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Zöldséges rizstészta', '500g rizstészta, 2 db kaliforniai paprika, 3 db répa, 2 db cukkini, 1 nagy fej lilahagyma, szójaszósz, 1 lime vagy citrom leve, mogyoró', 'A tésztát megfőzzük, zöldségeket felcsíkozzuk, a répát és cukkinit érdemes inkább hámozóval nagyon vékony kis szeletekre vágni, az egyszerűség kedvéért. A zöldségeket megpirítjuk, illetve egy idő után pároljuk, mert elég sok levet ereszt. Amikor már elég puha, mehet rá szója szósz ízlés szerint. Összekeverjük a tésztával, ami amúgy nem lesz könnyű művelet, de próbálkozzunk kitartóan. Végül mehet rá a citromlé és az előzőleg megpirított mogyoró.', 'MAINCOURSE','PUBLIC','LIKE', '2','piritottteszta');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Sárgarépa krémleves', '4 közepes sárgarépa, 1 kis fej vöröshagyma, kevés kókuszolaj, 3 gerezd fokhagyma, 0.5 tk gyömbér, 1 tk. kurkuma, só és bors ízlés szerint, 1 ek. apróra vágott petrezselyem, 2 l víz', 'A vöröshagymát felkockázzuk és üvegesre pirítjuk. Hozzáadjuk a fokhagymát, a kurkumát, a gyömbért belereszeljük, sózzuk, borsozzuk ízlés szerint. A répát felkockázzuk és megpároljuk, majd felöntjük a vízzel és puhára főzzük. Összeturmixoljuk, és ha nem elég sűrű, akkor a keményítővel vagy liszttel és kevés lével összekeverve besűrítjük. Száraz serpenyőben sótlan tökmagot pirítunk és találáskor a leves tetejére szórunk belőle.', 'SOUP','PUBLIC','LIKE', '1','repa');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Luca-macska', '50g élesztő, 150g margarin, 1 csipet sáfrány, 5dl mandulatej vagy zabtej, 1.5-2 dl cukor. 0.5 tk só, 900g liszt, a díszítéshez margarin és mazsola', 'Az élesztőt elmorzsoljuk egy tálba. A margarint megolvasztjuk, hozzáadjuk a tejet és langyosra melegítjük. A sáfrányt egy kis cukorral összetörjük egy mozsárban. Egy keveset hozzákeverünk a langyos keverékből, majd hozzáadjuk az egészhez. Az élesztőt elkeverjük egy kis tésztában, majd hozzáadjuk az egészhez. Mehet bele a cukor és só. Hozzádolgozzuk a lisztet és kb. 10 percig dagasztjuk. 30-45 percig hagyjuk kelni a tésztát. Kilisztezett pulton átgyúrjuk a tésztát. Tekercseket formázunk, sütőpapírral bélelt tepsibe tesszük és letakarva újabb 30-45 percig pihenni hagyjuk. Megkenjük olvadt margarinnal és megszórjuk mazsolával. 225 fokra előmelegített sütőben 8-10 percet sütjük.', 'DESSERT','PUBLIC','LIKE', '1','luca');
insert into recipe(title,ingredients,instructions,category,status,rating, author_id,image) values ('Áfonyás muffin', '250 ml szójatej, 1 teáskanál almabor ecet, 250 g liszt, 2.5 teáskanál sütőpor, 1/4 teáskanál szódabikarbóna, 1/2 teáskanál só, 65 g cukor, 60 ml olaj, 1 teáskanál vanília aroma, 1 citrom héja, 200 g áfonya', 'Először keverjük össze a tejet és az ecetet, hagyjuk állni amíg a többi elkészül. Keverjük el a száraz hozzávalókat a cukor kivételével, a cukrot pedig az olajjal és vaníliával. Hozzáadjuk a tejet, majd mehet egybe az egész. Belekeverjük a gyümölcsöt, muffin tepsibe mérjük, és 190 fokos sütőben 20-25 percig sütjük.', 'DESSERT','PUBLIC','LIKE', '2','afonyas');



insert into keyword(name) values('vegán');
insert into keyword(name) values('csoki');
insert into keyword(name) values('keksz');
insert into keyword(name) values('krumpli');
insert into keyword(name) values('krémleves');
insert into keyword(name) values('pörkölt');
insert into keyword(name) values('piskóta');

insert into recipe_keywords(recipes_id,keywords_id) values(1,1);
insert into recipe_keywords(recipes_id,keywords_id) values(1,3);
insert into recipe_keywords(recipes_id,keywords_id) values(2,1);
insert into recipe_keywords(recipes_id,keywords_id) values(2,6);
insert into recipe_keywords(recipes_id,keywords_id) values(3,1);
insert into recipe_keywords(recipes_id,keywords_id) values(4,1);
insert into recipe_keywords(recipes_id,keywords_id) values(4,5);
insert into recipe_keywords(recipes_id,keywords_id) values(5,1);
insert into recipe_keywords(recipes_id,keywords_id) values(5,5);
insert into recipe_keywords(recipes_id,keywords_id) values(6,1);
insert into recipe_keywords(recipes_id,keywords_id) values(6,5);
insert into recipe_keywords(recipes_id,keywords_id) values(7,1);
insert into recipe_keywords(recipes_id,keywords_id) values(7,3);
insert into recipe_keywords(recipes_id,keywords_id) values(7,2);
insert into recipe_keywords(recipes_id,keywords_id) values(8,1);
insert into recipe_keywords(recipes_id,keywords_id) values(8,2);
insert into recipe_keywords(recipes_id,keywords_id) values(9,1);
insert into recipe_keywords(recipes_id,keywords_id) values(9,7);





