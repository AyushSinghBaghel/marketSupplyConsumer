package com.consumer.market.Controller

import com.consumer.market.Model.FruitSupplyModel
import com.consumer.market.Model.GrainSupplyModel
import com.consumer.market.Model.ItemFinder
import com.consumer.market.Model.VegetableSupplyModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.lang.Double
import java.util.*
import kotlin.collections.HashMap


@RestController
class ApiConsumerController {

    var fruitUrl: String = "https://run.mocky.io/v3/c51441de-5c1a-4dc2-a44e-aab4f619926b"
    var grainURL: String = "https://run.mocky.io/v3/e6c77e5c-aec9-403f-821b-e14114220148"
    var vegetableURL: String = "https://run.mocky.io/v3/4ec58fbc-e9e5-4ace-9ff0-4e893ef9663c"
    var index = 0
    var temp: MutableMap<String, ItemFinder> = java.util.HashMap<String, ItemFinder>()



    @Autowired
    var restTemplate: RestTemplate? = null

    /**
     * Consuming a service by getForEntity method, this method is exposed as a Get operation if user
     * a Request parameter object(JSON) it will be automatically mapped to @PathVariable .
     * hitting End Point /buy-items with single paramater
     */
    @RequestMapping(value = ["/buy-items/{id}"])
    fun getProductList(@PathVariable("id") id: String?): String? {
        val headers = HttpHeaders()
        headers.add("Content-Type", "application/json")

        // creating rest Template to get the object from url and storing it inside Array model
        val fruitResponse = RestTemplate().getForEntity(fruitUrl, Array<FruitSupplyModel>::class.java)
        val fruits = fruitResponse.body
        if (fruits != null) {
            for (i in fruits.indices) {
                if (id == fruits.get(i).getName()) {
                    index = i
                }
            }
        }
        // Stream the each element and check whether it is giving correct output from response
        val fruitIsPresent = Arrays.stream(fruits).anyMatch { o: FruitSupplyModel? -> id.equals(o?.getName()?.trim { it <= ' ' }, ignoreCase = true) }
        return if (fruitIsPresent) {
            fruits?.get(index).toString()
        } else {

            //we will search in the Vegetables
            val vegetableResponse = RestTemplate().getForEntity(vegetableURL, Array<VegetableSupplyModel>::class.java)

            val vegetable = vegetableResponse.body
            if (vegetable != null) {
                if (vegetable != null) {
                    for (i in vegetable.indices) {

                        if (vegetable != null) {
                            if (id == vegetable.get(i).getProductName()) {
                                index = i
                            }
                        }
                    }
                }
            }
            val vegetableIsPresent = Arrays.stream(vegetable).anyMatch { v: VegetableSupplyModel? -> id.equals(v?.getProductName()?.trim { it <= ' ' }, ignoreCase = true) }
            return if (vegetableIsPresent) {
                vegetable?.get(index).toString()
            } else {
                // we will search in the Grain Store
                val grainResponse = RestTemplate().getForEntity(grainURL, Array<GrainSupplyModel>::class.java)
                val grain = grainResponse.body
                if (grain != null) {
                    if (grain != null) {
                        for (i in grain.indices) {
                            print("This is the Message ${id == grain.get(0).getItemName()} ")
                            if (grain != null) {
                                if (id == grain.get(i).getItemName()) {
                                    index = i
                                }
                            }
                        }
                    }
                }
                val grainIsPresent = Arrays.stream(grain).anyMatch { v: GrainSupplyModel? -> id.equals(v?.getItemName()?.trim { it <= ' ' }, ignoreCase = true) }
                return if (grainIsPresent) {
                    grain?.get(index).toString()
                } else {
                    "NO_ITEM_FOUND"
                }
            }
        }
    }

    /**
     * Consuming a service by getForEntity method, this method is exposed as a Get operation if user
     * a Request parameter object(JSON) it will be automatically mapped to @PathVariable .
     *
     * hitting End Point /buy-item-qty-price with multiple paramter
     * This method will take Exact three parameter of Name, Price And Quantity with the
     * Exact information provided in counsumed API
     */
    @RequestMapping(value = ["/buy-item-qty-price/{id}/{quantity}/{price}"])
    fun searchProductList(@PathVariable("id") id: String, @PathVariable("quantity") quantity: String, @PathVariable("price") price: String): String {
        val headers = HttpHeaders()
        headers.add("Content-Type", "application/json")

        var ITEM = id
        var QUANTITY = quantity
        var PRICE = price
        var foundCachedItem = false

        for ((key, value) in temp) {
            if (key.equals(id, ignoreCase = true)) {
                if (converter(value.price)!!.toDouble() >= converter(PRICE!!)!!.toDouble() && value.quantity.toInt() >= QUANTITY!!.toInt()) {
                    foundCachedItem = true
                }
            }
        }
        if (foundCachedItem) {
            return "Found cached item $id $quantity $price";
        }

        val fruitResponse = RestTemplate().getForEntity(fruitUrl, Array<FruitSupplyModel>::class.java)
        val fruits = fruitResponse.body
        if (fruits != null) {
            for (i in fruits.indices) {
                if (fruits != null) {
                    if (id.equals(fruits.get(i).getName(), ignoreCase = true) && quantity.equals(fruits.get(i).getQuantity(), ignoreCase = true)
                            && Double.parseDouble(price?.let { converter(it) }) <= Double.parseDouble(fruits.get(i).getPrice()?.let { converter(it) })) {
                        index = i
                    }
                }
            }
        }

        val itemIsPresent = Arrays.stream(fruits).anyMatch { o: FruitSupplyModel? -> id.equals(o?.getName()?.trim { it <= ' ' }, ignoreCase = true) }
        val quantityIsPresent = Arrays.stream(fruits).anyMatch { q: FruitSupplyModel? -> quantity.equals(q?.getQuantity()?.trim { it <= ' ' }, ignoreCase = true) }
        val priceIsPresent = Arrays.stream(fruits).anyMatch { p -> PRICE?.let { converter(it) }!!.toDouble() <= converter(p.getPrice()!!)!!.toDouble() }

        if (itemIsPresent && quantityIsPresent && priceIsPresent) {
            val f = FruitSupplyModel()
            if (fruits != null) {
                f.setName(id)
                f.setQuantity(quantity)
                f.setPrice(price)
                temp[id] = ItemFinder(price, quantity)
            }
            return f.toString()
        } else {
            //we will search in the Vegetables
            val vegetableResponse = RestTemplate().getForEntity(vegetableURL, Array<VegetableSupplyModel>::class.java)
            val vegetable = vegetableResponse.body
            if (vegetable != null) {
                for (j in vegetable.indices) {
                    if (id.equals(vegetable.get(j).getProductName(), ignoreCase = true) && quantity.equals(vegetable.get(j).getQuantity(), ignoreCase = true)
                            && Double.parseDouble(price?.let { converter(it) }) <= Double.parseDouble(vegetable.get(j).getPrice()?.let { converter(it) })) {
                        index = j
                    }
                }
            }
            val productPresent = Arrays.stream(vegetable).anyMatch { op: VegetableSupplyModel? -> id.equals(op?.getProductName()?.trim { it <= ' ' }, ignoreCase = true) }
            val vegQuantityIsPresent = Arrays.stream(vegetable).anyMatch { vq: VegetableSupplyModel? -> quantity.equals(vq?.getQuantity()?.trim { it <= ' ' }, ignoreCase = true) }
            val vegPriceIsPresent = Arrays.stream(vegetable).anyMatch { vp -> PRICE?.let { converter(it) }!!.toDouble() <= converter(vp.getPrice()!!)!!.toDouble() }

            if (productPresent && vegQuantityIsPresent && vegPriceIsPresent) {

                val v = VegetableSupplyModel()
                if (vegetable != null) {
                    v.setProductName(id)
                    v.setQuantity(quantity)
                    v.setPrice(price)
                    temp[id] = ItemFinder(price, quantity)
                }
                return v.toString()
            } else {
                // we will search in the Grain Store
                val garinResponse = RestTemplate().getForEntity(grainURL, Array<GrainSupplyModel>::class.java)
                val grains = garinResponse.body
                if (grains != null) {
                    for (k in grains.indices) {
                            if (id.equals(grains.get(k).getItemName(), ignoreCase = true) && quantity.equals(grains.get(k).getQuantity(), ignoreCase = true)
                                    && Double.parseDouble(price?.let { converter(it) }) <= Double.parseDouble(grains.get(k).getPrice()?.let { converter(it) })) {
                                index = k
                        }
                    }
                }
                val grainItemIsPresent = Arrays.stream(grains).anyMatch { gi: GrainSupplyModel? -> id.equals(gi?.getItemName()?.trim { it <= ' ' }, ignoreCase = true) }
                val grainQuantityIsPresent = Arrays.stream(grains).anyMatch { gq: GrainSupplyModel? -> quantity.equals(gq?.getQuantity()?.trim { it <= ' ' }, ignoreCase = true) }
                val grainPriceIsPresent = Arrays.stream(grains).anyMatch { gp -> PRICE?.let { converter(it) }!!.toDouble() <= converter(gp.getPrice()!!)!!.toDouble() }

                if (grainItemIsPresent && grainQuantityIsPresent && grainPriceIsPresent) {
                    val g = GrainSupplyModel()
                    if (grains != null) {
                        g.setItemName(id)
                        g.setQuantity(quantity)
                        g.setPrice(price)
                        temp[id] = ItemFinder(price, quantity)
                    }
                    return g.toString()
                } else {

                    return "NO_ITEM_FOUND"
                }
            }
        }
    }
    fun converter(price: String): String? {
        return price.substring(1)

    }
}

