package com.consumer.market.Model

class ItemFinder {

        var price: String = ""
        var quantity: String = ""

        override fun toString(): String {
            return "ItemQualifier{" +
                    "price='" + price + '\'' +
                    ", quantity='" + quantity + '\'' +
                    '}'
        }

        constructor(price: String, quantity: String) {
            this.price = price
            this.quantity = quantity
        }

        constructor() {}

}