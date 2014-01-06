package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Shop;

@Component
public class ShopFormatter implements Formatter<Shop> {

	@Override
	public String display(Shop shop) {
		return shop.toString();
	}

}
