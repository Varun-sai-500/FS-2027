/*


Collection Name: orders

Sample Document:
----------------
{
  "_id": {
    "$oid": "6970698a212a45531c13cba1"
  },
  "userId": "u1",
  "status": "PAID",
  "provider": "stripe",
  "amount": 650,
  "items": [
    {
      "sku": "A1",
      "qty": 2
    },
    {
      "sku": "B2",
      "qty": 1
    }
  ],
  "createdAt": {
    "$date": "2026-01-20T09:00:00.000Z"
  }
}


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
        ->  db -> Refers to the database.
        ->  <collection> -> Your collection name
	
*/

const { db } = require("../FSD/orders-circuit-breaker-demo/models/Order");

printjson(

)


// Write a condition
db.orders.find({amount: {$gt: 500, $lt: 1000}}, {_id: 0});

// Or operator
db.orders.find({$or: [{status: "PAID"}, {status: "REFUNDED"}]});

// In operator


db.orders.find({provider: {$in: ["stripe", "razorpay"]}});

// Arrays 
db.orders.find({"items.sku": "A1"});

db.orders.find({items: {$elemMatch: {sku:"A1", qty: {$gte: 2}}}});

db.orders.find({$expr: {$gt: [{$size: "$items"}, 5]}});

// Map, filter and reduce 
// Create a field bulkIteams that keeps only items where qty >= 2 -> use $filter

db.orders.aggregate([
  {
    $project: {
      orderId: 1,
      bulkItems: {
        $filter: { input: "$items", as: "i", cond: {$gte: ["$$i.qty", 2]} }
      }, 
      _id:0
    }
  }
])

// Create skuList: an array containing only sku for each item -> use $map
db.orders.aggregate([
  {
    $project: {
      skuList: {
        $map: { input: "$items", as: "i", in: "$$i.sku" }
      }
    }
  }
])

// Compute orderTotal = sum(qty * price) across the items array -> use $reduce
db.orders.aggregate([
  {
    $project: {
      orderTotal: {
        $reduce: { input: "$items", initialValue: 0, in: { $add: [ "$$value", { $multiply: [ "$$this.qty", "$$this.price" ] } ] } }
      }
    }
  }
])

// Create highSKusL keep inly items where prices >= 200 then output each sku -> use $filter and $map
db.orders.aggregate([
  {$project: {
    highSkus: {
      $filter: {input: "$items", as: "i", cond: { $gte: ["$$i.price", 200] }}
    },
    _id: 0
  }}, 
  {
    $project: {
      sku: {
        $map: { input: "$highSkus", as: "i", in: "$$i.sku" }
      }
    }
  }
]) 