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
# This Api will take three parameter(Name,Quantity, Price)
*input should be excat same as given in the Supplier Api
*you will get item is not found ( NOT_FOUND) if provided wrong input excluding Case-Senstivity.

 # example: http://localhost:8080/buy-item-qty-price/Onion/20/$76.30


