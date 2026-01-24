/*
Compute totalUnits: total quantity of all items in each order


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
    totalUnits: 3                                                                                                                                     
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u2',                                                                                                                                     
    totalUnits: 5                                                                                                                                     
  }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	->  db -> Refers to the database.
    	->  <collection> -> Your collection name
	
*/

printjson(
    db.orders.aggregate([
        {
            $project: {
                _id: 0,
                userId: 1,
                totalUnits: {
                    $reduce: { input: "$items", initialValue: 0, in:  { $add: ["$$value", "$$this.qty"] } }
                }
            }
        }
    ])
)
