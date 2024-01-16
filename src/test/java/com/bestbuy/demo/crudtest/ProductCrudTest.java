package com.bestbuy.demo.crudtest;

import com.bestbuy.demo.model.ProductPojo;
import com.bestbuy.demo.testbase.TestBase1;
import com.bestbuy.demo.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


class ProductsCRUDTest extends TestBase1 {

        static String name = TestUtils.getRandomValue() + "Duracell - AAA Batteries (4-Pack)";

        static String type = TestUtils.getRandomValue() + "HardGood1";

        static double price = 5.49;

        static Long upc = Long.valueOf("0413334240191");

        static int shipping = 0;

        static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";

        static String manufacturer = "Duracell";

        static String model = "MN2400B4Z";

        static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";

        static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

        static int id;

        @Test
        public void createProduct() {
            ProductPojo productPojo = new ProductPojo();
            productPojo.setName(name);
            productPojo.setType(type);
            productPojo.setPrice(price);
            productPojo.setUpc(upc);
            productPojo.setShipping(shipping);
            productPojo.setDescription(description);
            productPojo.setManufacturer(manufacturer);
            productPojo.setModel(model);
            productPojo.setUrl(url);
            productPojo.setImage(image);

            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .body(productPojo)
                    .post("/products");

            response.prettyPrint();
            response.then().statusCode(201);
        }

        //Get
        public void getProduct() {
            Response response = given()
                    .pathParam("productId", id)
                    .get("/{productId}");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        //Put
        public void updateProduct() {
            String updatedProductJson = "{\n" +
                    "    \"name\": \"Updated Duracell - AAA Batteries (4-Pack)\"\n" +
                    "}";

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(updatedProductJson)
                    .pathParam("productId", id)
                    .put("/{productId}");

            response.then().statusCode(200);
        }

        //Delete

        public void deleteProduct() {
            Response response = given()
                    .pathParam("productId", id)
                    .delete("/{productId}");

            response.then().statusCode(204);
        }

    }

