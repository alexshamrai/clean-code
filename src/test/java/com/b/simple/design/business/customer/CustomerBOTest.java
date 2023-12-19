package com.b.simple.design.business.customer;

import static com.b.simple.design.model.customer.Currency.EURO;
import static com.b.simple.design.model.customer.Currency.INDIAN_RUPEE;
import static com.b.simple.design.model.customer.ProductType.BANK_GUARANTEE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;

public class CustomerBOTest {

	private static final int DEFAULT_PRODUCT_ID = 100;
	private static final String DEFAULT_PRODUCT_NAME = "Product 15";

	private CustomerBO customerBO = new CustomerBOImpl();

	@Test
	public void testCustomerProductSumWithTheSameCurrency() throws DifferentCurrenciesException {

		List<Product> products = List.of(createNewProduct(createAmount("5.0", EURO)),
										 createNewProduct(createAmount("6.0", EURO)));

		Amount actualAmount = customerBO.getCustomerProductsSum(products);
		Amount expectedAmount = createAmount("11.0", EURO);

		assertAmount(actualAmount, expectedAmount);
	}

	@Test
	public void testCustomerProductSumWithDifferentCurrency() {
		List<Product> products = List.of(createNewProduct(createAmount("5.0", INDIAN_RUPEE)),
				createNewProduct(createAmount("6.0", EURO)));

		Assertions.assertThrows(DifferentCurrenciesException.class, ()-> customerBO.getCustomerProductsSum(products));
	}

	@Test
	public void testCustomerProductSumEmptyProducts() throws DifferentCurrenciesException {
		Amount actualAmount = customerBO.getCustomerProductsSum(Collections.emptyList());
		Amount expectedAmount = createAmount("0", EURO);

		assertAmount(actualAmount, expectedAmount);
	}

	private static void assertAmount(Amount actualAmount, Amount expectedAmount) {
		assertEquals(expectedAmount.getCurrency(), actualAmount.getCurrency());
		assertEquals(expectedAmount.getValue(), actualAmount.getValue());
	}

	private ProductImpl createNewProduct(Amount amount) {
		return new ProductImpl(DEFAULT_PRODUCT_ID, DEFAULT_PRODUCT_NAME, BANK_GUARANTEE, amount);
	}

	private static AmountImpl createAmount(String value, Currency currency) {
		return new AmountImpl(new BigDecimal(value), currency);
	}

}