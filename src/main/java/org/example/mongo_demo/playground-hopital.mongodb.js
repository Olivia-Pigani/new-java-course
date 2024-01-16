use("hopital");
// db.patient.insertMany([{
//     "firstname":"Mohamed",
//     "lastName":"Deloin",
//     "age":30,
//     "history":[
//         {"desease":"rhume","treatment":"sirop"},
//         {deasease:"grippe", treatment:"anti-biotique"}
//     ]
// },
// {
//     "firstname":"Gilbert",
//     "lastName":"Test",
//     "age":45,
//     "history":[
//         {"desease":"qdqd","treatment":"claque"},
//         {deasease:"rhume", treatment:"pez"}
//     ]
// },
// {
//     "firstname":"Stan",
//     "lastName":"Stan",
//     "age":16,
//     "history":[
//         {"desease":"gastro","treatment":"sirop"},
//         {deasease:"gastro", treatment:"anti-biotique"}
//     ]
// }
// ]);


// Mettre à jour les données d’un patient avec nouvel un âge, un
// nouveau nom et un nouvel historique medical.
// db.patient.updateOne(
//     { "firstname": "Stan", "lastName": "Stan" },
//     {
//         $set: {
//             "age": 15,
//             "firstname": "newStan",
//             "lastName": "newStan",
//             "history": [
//         {"desease":"newgastro","treatment":"newsirop"},
//         {deasease:"newrhume", treatment:"newanti-biotique"}
//     ]
//         }
//     }
// );


// db.patient.find({ "age": { $gt: 29 } });


//  db.patient.deleteMany({ "history.deasease": "rhume" });



// db.patient.find();

