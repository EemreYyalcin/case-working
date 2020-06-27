# Trendyol-Case-Working

The project is developed a e-commerce shopping cart has products, categories, campaigns, coupons etc...


## Definations

 * Define Categories
   ```java
     Category dress = new Category(1L, "Dress");
 
     Category man = new Category(2L, "Man"); 
    ```

* Define Categories Parent-Child 

     ```java
     man.setParentCategory(dress);
     ```
     
* Define Products for Categories

     ```java
    Product tShirtProduct = new Product(1L, "tShirtProduct", new BigDecimal("100.0"), dress);
    
    Product pantProduct = new Product(2L, "pantProduct", new BigDecimal("200.0"), man);
     ```

* Define Campaigns for Categories

     ```java
     Campaign dressCampaign = new Campaign(1L, dress, new BigDecimal("10.0"), 5, DiscountType.RATE);
     
     Campaign manCampaign = new Campaign(2L, man, new BigDecimal("300.0"), 10, DiscountType.AMOUNT);
     ```
     
 * Define Coupon for Shopping Cart
 
     ```java
     Coupon coupon = new Coupon(1L, new BigDecimal("3000.0"), new BigDecimal("20.0"), DiscountType.RATE);
     ```
 
 * Define Shopping Cart and Add Items with Quantity
 
     ```java
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(tShirtProduct, 10);
    shoppingCart.addItem(pantProduct, 20);
     ```
  
 
## Running and Results
  
 * Apply Campaign and Coupon
 
     ```java
     shoppingCart.applyDiscounts(dressCampaign, manCampaign);
  
     shoppingCart.applyCoupon(coupon);
    ```
 * See Code Result 
 
     ```java
    shoppingCart.print();
    ```
    
    
## Deployment
    
  * Maven
  
    This project has been developed by using Maven and Java8. jUnit4 has been used for testing.You can build by executing command as in below:
     
     ```sh
       $ mvn clean package        
    ```
    
    
## License

This project is licensed under the MIT License
