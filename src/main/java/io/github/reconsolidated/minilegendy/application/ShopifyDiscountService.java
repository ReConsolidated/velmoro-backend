package io.github.reconsolidated.minilegendy.application;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ShopifyDiscountService {

    private final RestTemplate restTemplate;
    private final String accessToken = "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";
    private final String shopName = "quickstart-d96ed6ad";

    public ShopifyDiscountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createOneTimeDiscountCode() {
        try {
            String priceRuleUrl = "https://" + shopName + ".myshopify.com/admin/api/2023-04/price_rules.json";
            String discountCodeUrl = "https://" + shopName + ".myshopify.com/admin/api/2023-04/price_rules/{price_rule_id}/discount_codes.json";

            Map<String, Object> priceRuleRequest = new HashMap<>();
            Map<String, Object> priceRule = new HashMap<>();
            priceRule.put("title", "Promocja 10% Off - Jednorazowy");
            priceRule.put("target_type", "line_item");
            priceRule.put("target_selection", "all");
            priceRule.put("allocation_method", "across");
            priceRule.put("value_type", "percentage");
            priceRule.put("value", -10.0);
            priceRule.put("customer_selection", "all");
            priceRule.put("usage_limit", 1);
            priceRule.put("starts_at", "2024-01-01T00:00:00Z");

            priceRuleRequest.put("price_rule", priceRule);

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Shopify-Access-Token", accessToken);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(priceRuleRequest, headers);
            ResponseEntity<Map> priceRuleResponse = restTemplate.exchange(priceRuleUrl, HttpMethod.POST, request, Map.class);

            Long priceRuleId = (Long) ((Map<String, Object>) priceRuleResponse.getBody().get("price_rule")).get("id");

            Map<String, Object> discountCodeRequest = new HashMap<>();
            Map<String, Object> discountCode = new HashMap<>();
            discountCode.put("code", generateRandomCode(6));
            discountCodeRequest.put("discount_code", discountCode);

            HttpEntity<Map<String, Object>> discountRequest = new HttpEntity<>(discountCodeRequest, headers);
            ResponseEntity<Map> discountCodeResponse = restTemplate.exchange(discountCodeUrl.replace(
                    "{price_rule_id}", priceRuleId.toString()), HttpMethod.POST, discountRequest, Map.class
            );

            return (String) ((Map<String, Object>) discountCodeResponse.getBody().get("discount_code")).get("code");
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas tworzenia kodu zniżkowego: " + e.getMessage(), e);
        }
    }

    public static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }

        return code.toString();
    }
}
