package org.example.testData;

import org.testng.annotations.DataProvider;

public class BillingData {

    @DataProvider(name = "billingAddressData")
    public static Object[][] getBillingAddressData() {
        return new Object[][] {
                {"123 Main St", "New York", "NY", "United States", "10001"}
//               , {"456 Elm St", "Los Angeles", "CA", "United States", "90001"},
//                {"789 Pine St", "Chicago", "IL", "United States", "60601"}
        };
    }
}
