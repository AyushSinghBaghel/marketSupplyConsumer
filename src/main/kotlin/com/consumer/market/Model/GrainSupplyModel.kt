package com.consumer.market.Model

class GrainSupplyModel {
    private var itemId: String? = ""
    private var itemName: String? = ""
    private var quantity: String? = "null"
    private var price: String? = "null"

    fun getItemId(): String? {
        return itemId
    }

    fun setItemId(itemId: String?) {
        this.itemId = itemId
    }

    fun getItemName(): String? {
        return itemName
    }

    fun setItemName(itemName: String?) {
        this.itemName = itemName
    }

    fun getQuantity(): String? {
        return quantity
    }

    fun setQuantity(quantity: String?) {
        this.quantity = quantity
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }

    override fun toString(): String {
        return "GrainSupplyModel{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}'
    }

}