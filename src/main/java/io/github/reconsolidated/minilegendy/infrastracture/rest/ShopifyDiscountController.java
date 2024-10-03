package io.github.reconsolidated.minilegendy.infrastracture.rest;

import io.github.reconsolidated.minilegendy.application.ShopifyDiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class ShopifyDiscountController {

    private final ShopifyDiscountService shopifyDiscountService;

    public ShopifyDiscountController(ShopifyDiscountService shopifyDiscountService) {
        this.shopifyDiscountService = shopifyDiscountService;
    }

    @GetMapping("/create")
    public String createOneTimeDiscount() {
        return shopifyDiscountService.createOneTimeDiscountCode();
    }
}
