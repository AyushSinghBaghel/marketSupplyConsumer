package com.consumer.market.Model

class FruitSupplyModel {
    private var id: String? = ""
    private var name: String? = ""
    private var quantity: String? = ""
    private var price: String? = ""
    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
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
        return "FruitSupplyModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}'
    }
}