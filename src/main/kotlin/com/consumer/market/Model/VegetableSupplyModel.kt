package com.consumer.market.Model

class VegetableSupplyModel {
    private var productId: String? = ""
    private var productName: String? = ""
    private var quantity: String? = ""
    private var price: String? = ""

    fun getProductId(): String? {
        return productId
    }

    fun setProductId(productId: String?) {
        this.productId = productId
    }

    fun getProductName(): String? {
        return productName
    }

    fun setProductName(productName: String?) {
        this.productName = productName
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
        return "VegetableSupplyModel{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}'
    }
}