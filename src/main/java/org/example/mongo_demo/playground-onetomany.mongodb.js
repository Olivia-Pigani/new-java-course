
// 5 - Lister les auteurs qui ont un email spécifique "example.com"

// 6 - Mettre à jour l'email d'un auteur : Alice Dupont

// 7 - Supprimer tous les articles d'un auteur spécifique : Alice Dupont

// 8 - Compter le nombre d'articles d'un auteur spécifique : Bob Martin

// 9 - Rechercher des auteurs par un mot-clé dans leur nom : "Dup"

// 10 - Lister les articles avec un mot spécifique dans le titre : "Data"


use("onetomany")

// 1 - Trouver tous les articles par id : auteur1
// db.articles.aggregate([
    
//         {$match:{auteurId:"auteur1"}},

//         {$lookup:{
//             from:"articles",
//             localField:"_id",
//             foreignField:"auteurs",
//             as:"articlesAuteur"
//         }},

//     {
//         $project: {_id:1, titre:1,auteurId:1 }
//     }
// ])

// 2 - Trouver un auteur par 'Alice Dupont'
// db.auteurs.aggregate({$match:{nom:"Alice Dupont"}})



// 3 - Trouver un article par titre : 'La Science des Données'
// db.articles.aggregate({$match:{titre:'La Science des Données'}})


// 4 - Lister tous les articles d'un auteur spécifique "alice@example.com"
db.auteurs.findOne({{email:"alice@example.com"}})._id
// db.articles.aggregate([

//     {$match:{email:"alice@example.com"}},

// {$lookup:{
//     from:"articles",
//     localField:"_id",
//     foreignField:"auteurs",
//     as:"articlesAuteur"
// }},
// {
//     $project: {_id:0,titre:1}
// }



// ])