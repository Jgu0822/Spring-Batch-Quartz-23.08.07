package com.example.quartsdemo.batch.processor;

import com.example.quartsdemo.batch.repository.entity.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CoffeeItemProcessor implements ItemProcessor<Coffee, Coffee> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeItemProcessor.class);

	// 입력으로 받은 Coffee 객체의 필드들을 가공하여 새로운 Coffee 객체를 생성합니다.
	// 원본 데이터는 유지되고, 새로운 가공된 객체를 반환합니다.
	@Override
	public Coffee process(final Coffee coffee) throws Exception {
		Long id = coffee.getCoffee_id(); 									// 원본 객체의 coffee_id 필드를 읽어옵니다.
		String brand = coffee.getBrand().toUpperCase(); 					// 원본 객체의 brand 필드를 대문자로 변환합니다.
		String origin = coffee.getOrigin().toUpperCase(); 					// 원본 객체의 origin 필드를 대문자로 변환합니다.
		String chracteristics = coffee.getCharacteristics().toUpperCase();  // 원본 객체의 characteristics 필드를 대문자로 변환합니다.
		String processed = coffee.getProcessed();                           // 원본 객체의 processed 필드를 읽어옵니다.

		// 가공한 필드들로 새로운 Coffee 객체를 생성합니다.
		Coffee transformedCoffee = new Coffee(id, brand, origin, chracteristics, processed);

		// transformedCoffee 객체의 processed 필드를 "true"로 설정합니다.
		transformedCoffee.setProcessed("true");
		LOGGER.info("Converting ( {} ) into ( {} )", coffee, transformedCoffee);

		return transformedCoffee;
	}
}
