# marketSupplyConsumer
Consume Rest API with Spring Boot Using Kotlin

#  REST API  endpoint "buy-item "
This end-point will Counsume a;ll the API provided by Supplier and return the object passed at the end-point
*This Api will take only single parameter
*you will get item is not found ( NOT_FOUND) if provided wrong input excluding Case-Senstivity.
 # example: http://localhost:8080/buy-item/banana

#  REST API  endpoint "buy-item-qty-price "
This end-point will Counsume all the API provided by Supplier
*It will give you output based on input that you have provided to end point 
*This Api will take three parameter(Name,Quantity, Price)
*if searched Product is found in the Map That is storing the Data of Previous call and match the requirement you will get the data from map from last call with hitting Suppliers Api.
*you will get item is not found ( NOT_FOUND) if provided wrong input excluding Case-Senstivity.


 # example: hhttp://localhost:8080/buy-item-qty-price/Apple/30/$62.02
 # output : FruitSupplyModel{id='', name='Apple', quantity='30', price='$62.02'}
 
 2nd input lesser quantity.
 # INPUT:- http://localhost:8080/buy-item-qty-price/Apple/3/$50.02
# output :   Found cached item Apple 3 $50.02 (its means in that price and Quantity  you can have that Product)

