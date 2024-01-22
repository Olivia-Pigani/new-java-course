//aggregation:
use("resto");
db.test.aggregate({$match: {rating:5}},
{$count: "comptage"});
