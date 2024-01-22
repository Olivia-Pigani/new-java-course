// 1. Afficher la liste des restaurants mais limitez l’affichage à 10.
// 2. Afficher la liste des 10 premiers restaurants mais en triant cette liste par ordre
// alphabétique.
// 3. Afficher la liste des 10 premiers restaurants mais en triant cette liste par ordre
// alphabétique, mais uniquement ceux sur “Brooklyn” (champs : borough)..
// 4. Afficher la liste des 10 premiers restaurants mais on affiche que le nom du restaurant
// et son quartier.
// 5. Afficher la liste des 10 premiers restaurants mais on affiche tout sauf adresse et le
// grade.
// 6. Afficher la liste des 10 premiers restaurants avec un nouveau champ qui va afficher
// le nombre d’avis (grades) par restaurant.
// 7. Afficher la liste des 10 premiers restaurants avec un nouveau champ qui va afficher
// le nombre d’avis (grades) par restaurant et il faudra faire le tri par nombre d’avis.
// 8. On souhaite toujours afficher la liste des 10 premiers restaurants en affichant le nom
// du restaurant en majuscule et le quartier du restaurant.
// 9. On souhaite toujours afficher la liste des 10 premiers restaurants en affichant le nom
// du restaurant en majuscule et les 3 premières lettres du quartier.
// 10. On souhaite avoir le nombre total de restaurants toujours avec agrégation.
// 11. On souhaite avoir le nombre de restaurants par quartier (borough).

use("resto")

// db.restaurants.find()

// 1. Afficher la liste des restaurants mais limitez l’affichage à 10.
// db.restaurants.aggregate([{$limit: 10}])


// 2. Afficher la liste des 10 premiers restaurants mais en triant cette liste par ordre
// alphabétique.
// db.restaurants.find({ "name": { $regex: /^[A-Za-z]/ } }).limit(10).sort({ "name": 1 })

// db.restaurants.aggregate([
//   { 
//     $match: { 
//       "name": { $regex: /^[A-Za-z]/ } 
//     }
//   },
//   { 
//     $sort: { 
//       "name": 1 
//     }
//   },
//   { 
//     $limit: 10 
//   }
// ])




// 3. Afficher la liste des 10 premiers restaurants mais en triant cette liste par ordre
// alphabétique, mais uniquement ceux sur “Brooklyn” (champs : borough)..
// db.restaurants.aggregate([
//   { $match: { $and: [{ "name": { $regex: /^[A-Za-z]/ } }, { "borough": "Brooklyn" }] } },
//   { $limit: 10 },
//   { $sort: { "name": 1 } }
// ])


// 4. Afficher la liste des 10 premiers restaurants mais on affiche que le nom du restaurant
// et son quartier.
// db.restaurants.aggregate([{$project:{"_id":0,"name":1,"borough":1}},{$limit:10}])


// 5. Afficher la liste des 10 premiers restaurants mais on affiche tout sauf adresse et le
// grade.
// db.restaurants.aggregate([{$project: {adress : 0, grades : 0 }}, {$limit: 10} ])

// 6. Afficher la liste des 10 premiers restaurants avec un nouveau champ qui va afficher
// le nombre d’avis (grades) par restaurant.
// db.restaurants.aggregate([{$project : {grades : 1, name : 1}}, {$limit: 10}])

// 7. Afficher la liste des 10 premiers restaurants avec un nouveau champ qui va afficher
// le nombre d’avis (grades) par restaurant et il faudra faire le tri par nombre d’avis.
// db.restaurants.aggregate([
//   { $unwind: "$grades" }, 
//   { 
//     $group: { 
//       _id: "$name", 
//       nbReviews: { $sum: 1 } 
//     } 
//   },
//   { $sort: { nbReviews: -1 } }, 
//   { $limit: 10 }
// ])

// 8. On souhaite toujours afficher la liste des 10 premiers restaurants en affichant le nom
// du restaurant en majuscule et le quartier du restaurant.
// db.restaurants.aggregate([
//   {
//     $project: {
//       name: { $toUpper: "$name" }
//     }
//   },
//   {
//     $limit: 10
//   }
// ])

// 9. On souhaite toujours afficher la liste des 10 premiers restaurants en affichant le nom
// du restaurant en majuscule et les 3 premières lettres du quartier.
// db.restaurants.aggregate([
//   {
//     $project: {
//       name: { $toUpper: "$name" },
//       borough: { $substr: ["$borough", 0, 3] }
//     }
//   },
//   {
//     $limit: 10
//   }
// ])


// 10. On souhaite avoir le nombre total de restaurants toujours avec agrégation.
// db.restaurants.aggregate([
//   {
//     $group: {
//       _id: 1,
//       total: { $sum: 1 }
//     }
//   }
// ])


// 11. On souhaite avoir le nombre de restaurants par quartier (borough).
// db.restaurants.aggregate([{$group: {
//   _id: "$borough",
//   totalParQuartier: {
//     $sum: 1
//   }
// }}])