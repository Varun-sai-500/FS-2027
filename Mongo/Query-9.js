/*
Create bulkSkuList: list SKUs where qty ≥ 2


Collection Name: orders

Sample Document:
----------------
{
    _id: ObjectId('6970698a212a45531c13cba1'),
    userId: 'u1',
    status: 'PAID',
    provider: 'stripe',
    amount: 650,
    items: [ { sku: 'A1', qty: 2 }, { sku: 'B2', qty: 1 } ],
    createdAt: ISODate('2026-01-20T09:00:00.000Z')
}

Sample Output:
--------------
[                                                                                                                                                     
  {                                                                                                                                                   
    userId: 'u1',                                                                                                                                     
    bulkSkuList: [                                                                                                                                    
      'A1'                                                                                                                                            
    ]                                                                                                                                                 
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u2',                                                                                                                                     
    bulkSkuList: [                                                                                                                                    
      'C3'                                                                                                                                            
    ]                                                                                                                                                 
  }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	->  db -> Refers to the database.
    	->  <collection> -> Your collection name
	
*/

const { db } = require("../FSD/orders-circuit-breaker-demo/models/Order");

printjson(
    db.orders.aggregate([
        {
            $project: {
                _id: 0,
                userId: 1,
                isBulk: {
                    $filter: { input: "$items", as: "i", cond: { $gte: ["$$i.qty", 2] } }
                }
            }
        },
        {
            $project: {
                userId: 1,
                bulkSkuList: {
                    $map: { input: "$isBulk", as: "i", in: "$$i.sku" }
                }
            }
        }
    ])
)
