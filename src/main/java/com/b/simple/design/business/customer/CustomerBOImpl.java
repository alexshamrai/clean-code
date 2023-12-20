package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

import static com.b.simple.design.model.customer.Currency.EURO;
import static java.math.BigDecimal.ZERO;

public class CustomerBOImpl implements CustomerBO {

	@Override
	public Amount getCustomerProductsSum(List<Product> products) throws DifferentCurrenciesException {
		if (products.isEmpty())
			return new AmountImpl(ZERO, EURO);

		if(!isCurrencyIsTheSameForAllProducts(products)) {
			throw new DifferentCurrenciesException();
		}

		return calculateProductsSum(products);
	}

	private AmountImpl calculateProductsSum(List<Product> products) {
		BigDecimal sum = ZERO;
		Currency currency = products.get(0).getAmount().getCurrency();
		for (Product product : products) {
			sum = sum.add(product.getAmount().getValue());
		}

		return new AmountImpl(sum, currency);
	}

	private boolean isCurrencyIsTheSameForAllProducts(List<Product> products) {
		Currency firstProductCurrency = products.get(0).getAmount().getCurrency();
		return products.stream()
				.map(product -> product.getAmount().getCurrency())
				.allMatch(currency -> currency.equals(firstProductCurrency));
	}
}