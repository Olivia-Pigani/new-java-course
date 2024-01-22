use("bonus")
//   db.demo.find({_id: "566eec5f662b388eba464203"})




// 1. Quels sont les sportifs (identifiant, nom et prénom) qui ont un
// âge entre 20 et 30 ans 
// db.demo.aggregate([
//     {
//         $match: {
//             Age: { $gte: 20, $lte: 30 }
//         }
//     },
//     {
//         $project: { _id: 1, Prenom: 1, Nom: 1 }
//     }
// ])


// 2. Quels sont les gymnases de ville “Villetaneuse” ou de
// “Sarcelles” qui ont une surface de plus de 400 m2 
// db.demo.aggregate([
//     {
//         $match: {
//             $and: [
//                 {
//                     $or: [
//                         { Ville: "VILLETANEUSE" },
//                         { Ville: "SARCELLES" }
//                     ]
//                 },
//                 { Surface: { $gt: 400 } }
//             ]
//         }
//     }
// ])


// 3. Quels sont les sportifs (identifiant et nom) qui pratiquent du
// handball ?
//  db.demo.aggregate([{$match:{"Sports.Jouer" : "Hand ball"}},{$project:{_id:1,Nom:1}} ])



//4. Quels sportifs (identifiant et nom) ne pratiquent aucun sport ?
// db.demo.aggregate([{$match:{"Sports.Jouer" : null}},{$project:{_id:1,Nom:1}} ])


//5. Quels gymnases n’ont pas de séances le dimanche ?
// db.demo.aggregate([
//     {
//         $match: {
//             "Seances": { $exists: true, $not: { $elemMatch: { "Jour": "dimanche" } } }
//         }
//     },
//     {
//         $project: {  NomGymnase: 1 }
//     }
// ])


// 6. Quels gymnases ne proposent que des séances de basket
// ball ou de volley ball ?
// db.demo.aggregate([
//     {
//         $match: {
//             $or: [
//                 {"Seances.Libelle": "Basket ball"},
//                 {"Seances.Libelle": "Volley ball"}
//             ]
//         }
//     },
//     {
//         $project: { _id: 0, NomGymnase: 1 }
//     }
// ])

//7. Quels sont les entraîneurs qui sont aussi joueurs ?
// db.demo.aggregate([{$match:{


//     "IdSportif":{$exists:true},
//     "IdSportifConseiller":{$exists:true}
// }
// },
// {$project:{Nom:1,Prenom:1 }}




// ])

//8. Pour le sportif “Kervadec” quel est le nom de son conseiller ?
// db.demo.aggregate([
//    { $match:{Nom:"KERVADEC"}},

//     {$lookup:{
//         from:"demo",
//         localField:"IdSportifConseiller",
//         foreignField:"IdSportif",
//         as: "conseillerKervadec"
//     }},

//     {$unwind:"$conseillerKervadec" },

//    {$project: {
//     _id:1,
//     NomConseiller:"$conseillerKervadec.Nom"
//    }}
// ])


// 9. Quelle est la moyenne d’âge des sportives qui pratiquent du
// basket ball ?
// db.demo.aggregate([
//     {$match:{"Sports.Jouer":"Basket ball","Sexe":"F"}},
//     {$group:{_id : null, averageAge:{$avg:"$Age"}}}



// ])

// 10.Quels entraîneurs n’entraînent que du hand ball ou du basket
// ball ?
// db.demo.aggregate([

// {$match:{ $or:[{"Sports.Entrainer":"Basket ball"}, {"Sports.Entrainer":"Hand ball"}]}},
// {$project: {Nom:1, Prenom:1}}



// ])

// 11. Pour chaque sportif donner le nombre de sports qu’il arbitre?
// db.demo.aggregate([
//     {
//         $project: {
//             Nom: 1,
//             Prenom: 1,
//             totalSport: {
//                 $cond: {
//                     if: { $isArray: "$Sports.Arbitrer" },
//                     then: { $size: "$Sports.Arbitrer" },
//                     else: 0
//                 }
//             }
//         }
//     }
// ])
