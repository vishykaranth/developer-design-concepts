package com.manning.spock.chapter4.specification

import spock.lang.*

import com.manning.spock.chapter4.Basket
import com.manning.spock.chapter4.Product


@Title("Unit test for basket weight")
class BasketWeightSpec extends spock.lang.Specification{

	def "A basket with two products weights as their sum"() {
		given: "an empty basket"
		Basket basket = new Basket()

		and: "several products"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)

		when: "user wants to buy the TV and the camera and the hifi"
		basket.addProduct tv
		basket.addProduct camera

		then: "basket weight is equal to all product weight"
		basket.currentWeight == (tv.weight + camera.weight)
	}

	def "A basket with two products weights as their sum (better)"() {
		given: "an empty basket"
		@Subject
		Basket basket = new Basket()

		and: "several products"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)

		when: "user wants to buy the TV and the camera and the hifi"
		basket.addProduct tv
		basket.addProduct camera

		then: "basket weight is equal to all product weight"
		basket.currentWeight == (tv.weight + camera.weight)
	}
}

