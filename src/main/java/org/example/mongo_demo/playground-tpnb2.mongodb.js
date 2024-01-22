use("tp2");

// db.users.insertOne({"lastName":"Norris",
// "firstName":"Chuck",
// "age":77,
// "hobbies":[
//     "karate","kung-fu","ruling the world"
// ]




// db.users.find();
// db.users.find({"firstName": "Chuck", "lastName": "Norris"});


// chuck sans son id
// db.users.find({"firstName": "Chuck", "lastName": "Norris"}, {"_id": 0});


// entre 20 et 25 ans
// db.users.find({"age": {$gte: 20, $lte: 25}});


//hommes etre 30 et 40
// db.users.find({"gender": "male", "age": {$gte: 30, $lte: 40}});

//ville
// db.users.find({"address.state": "Louisiana"});

// 20 premiers users : 
// db.users.find().limit(20).sort({"age":-1});

// femmes de 30 ans
// db.users.find({"gender":"female",$and:[{age:30}]}).count();

// supprimer les telephones 
// db.users.updateMany({}, {$unset: {"phone": ""}});
// db.users.find();


// Chuck Norris est venu nous dire que le temps ne marquait pas Chuck Norris,
// mais que Chuck Norris marquait le temps : changer l'age de Chuck Norris à
// infinity
// db.users.updateOne({"lastName": "Norris"}, {$set: {"age": Infinity}});

//Ajoutons un hobby à tous nos utilisateurs de plus de 50 ans : jardinage
// db.users.updateMany(
//     {"age": {$gt: 50}},
//     {$push: {"hobbies": "jardinage"}}
// );db.users.find();

// 