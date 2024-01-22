use("info");

// db.produits.insertMany([{
//     "nom":"Macbook Pro",
//     "fabriquant":"Apple",
//     "prix":11435.99,
//     "options":["Intel Core i5", "Retina Display"," Long life battery"]

// },
// {
//     "nom":"Macbook Air",
//     "fabriquant":"Apple",
//     "prix":125794.73,
//     "ultrabook":true,
//     "options":["Intel Core i7", "SSD"," Long life battery"]

// },
// {
//     "nom":"ThinkPad X230",
//     "fabriquant":"Lenovo",
//     "prix":114358.74,
//     "ultrabook":true,
//     "options":["Intel Core i5", "SSD"," Long life battery"]

// },


// ]);






db.produits.find();





// db.produits.findOne({nom:"ThinkPad X230" });






// db.produits.find({prix :{$gt:13723.00}});



db.products.find({"ultrabook":true}).limit(1);