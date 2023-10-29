package com.kia.solid_example


data class CurrencyRates(val euroToDollar: Double, val euroToRuble: Double)

enum class CurrencyType{
    EURO,
    DOLLAR,
    RUBLE
}

class PriceHandler(private val priceApi: PriceApi,
                   private val currencyRateApi: CurrencyRateApi,
                   private val currencyCalculator: CurrencyCalculator) {

    fun getPrice(type: CurrencyType): Double{
        val priceEuro = priceApi.getPriceEuro()
        val currencyRate = currencyRateApi.getCurrencyRate()

        return currencyCalculator.calculate(type,priceEuro,currencyRate)
    }

}

interface PriceApi{
    fun getPriceEuro(): Double
}

interface CurrencyRateApi{
    fun getCurrencyRate(): CurrencyRate
}

class CurrencyCalculator{

    fun calculate(type: CurrencyType, priceEuro: Double, currencyRate: CurrencyRates): Double = when(type){
        CurrencyType.EURO -> priceEuro
        CurrencyType.DOLLAR -> priceEuro * currencyRate.euroToDollar
        CurrencyType.RUBLE -> priceEuro * currencyRate.euroToRuble
    }
}
