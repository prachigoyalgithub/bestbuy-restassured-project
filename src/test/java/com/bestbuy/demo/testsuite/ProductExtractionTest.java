
    package com.bestbuy.demo.testsuite;

    import com.bestbuy.demo.testbase.TestBase;
    import io.restassured.RestAssured;
    import io.restassured.path.json.JsonPath;
    import org.testng.annotations.BeforeClass;
    import org.testng.annotations.Test;

    import java.util.List;

    public class ProductExtractionTest extends TestBase {

        @Test
        public void extractProductDetails() {
            String response = RestAssured.get("http://localhost:3030/products").asString();
            JsonPath jsonPath = new JsonPath(response);

            // 21. Extract the limit
            int limit = jsonPath.getInt("limit");
            System.out.println("Limit: " + limit);

            // 22. Extract the total
            int total = jsonPath.getInt("total");
            System.out.println("Total: " + total);

            // 23. Extract the name of the 5th product
            String fifthProductName = jsonPath.getString("data[4].name");
            System.out.println("Name of 5th Product: " + fifthProductName);

            // 24. Extract the names of all the products
            List<String> allProductNames = jsonPath.getList("data.name");
            System.out.println("Names of all Products: " + allProductNames);

            // 25. Extract the productId of all the products
            List<String> allProductIds = jsonPath.getList("data.id");
            System.out.println("ProductIds of all Products: " + allProductIds);

            // 26. Print the size of the data list
            int dataSize = jsonPath.getList("data").size();
            System.out.println("Size of data list: " + dataSize);

            // 27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
            String productNameToFind = "Energizer - MAX Batteries AA (4-Pack)";
            List<Object> productValues = jsonPath.getList("data.find { it.name == '" + productNameToFind + "' }");
            System.out.println("Values for product '" + productNameToFind + "': " + productValues);

            // 28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
            String modelNameToFind = "Energizer - N Cell E90 Batteries (2-Pack)";
            String model = jsonPath.getString("data.find { it.name == '" + modelNameToFind + "' }.model");
            System.out.println("Model for product '" + modelNameToFind + "': " + model);

            // 29. Get all the categories of 8th products
            List<String> eighthProductCategories = jsonPath.getList("data[7].categories.name");
            System.out.println("Categories of 8th Product: " + eighthProductCategories);

            // 30. Get categories of the store where product id = 150115
            String productIdForCategories = "150115";
            List<String> categoriesForProductId = jsonPath.getList("data.find { it.id == '" + productIdForCategories + "' }.categories.name");
            System.out.println("Categories for product with ID '" + productIdForCategories + "': " + categoriesForProductId);

            // 31. Get all the descriptions of all the products
            List<String> allProductDescriptions = jsonPath.getList("data.description");
            System.out.println("Descriptions of all Products: " + allProductDescriptions);

            // 32. Get id of all the all categories of all the products
            List<String> allCategoriesIds = jsonPath.getList("data.categories.id.flatten()");
            System.out.println("Ids of all Categories of all Products: " + allCategoriesIds);

            // 33. Find the product names Where type = HardGood
            List<String> hardGoodProductNames = jsonPath.getList("data.findAll { it.type == 'HardGood' }.name");
            System.out.println("Product names with type 'HardGood': " + hardGoodProductNames);

            // 34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
            String duracellProductName = "Duracell - AA 1.5V CopperTop Batteries (4-Pack)";
            int duracellTotalCategories = jsonPath.getInt("data.find { it.name == '" + duracellProductName + "' }.categories.size()");
            System.out.println("Total number of categories for product '" + duracellProductName + "': " + duracellTotalCategories);

            // 35. Find the createdAt for all products whose price < 5.49
            float priceThreshold = 5.49f;
            List<String> createdAtForLowPriceProducts = jsonPath.getList("data.findAll { it.price < " + priceThreshold + " }.createdAt");
            System.out.println("CreatedAt for products with price < 5.49: " + createdAtForLowPriceProducts);

            // 36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
            String energizerProductName = "Energizer - MAX Batteries AA (4-Pack)";
            List<String> categoriesForEnergizerProduct = jsonPath.getList("data.find { it.name == '" + energizerProductName + "' }.categories.name");
            System.out.println("Categories for product '" + energizerProductName + "': " + categoriesForEnergizerProduct);

            // 37. Find the manufacturer of all the products
            List<String> allManufacturers = jsonPath.getList("data.manufacturer");
            System.out.println("Manufacturers of all Products: " + allManufacturers);

            // 38. Find the image of products whose manufacturer is = Energizer
            String manufacturerToFilter = "Energizer";
            List<String> imagesForEnergizerProducts = jsonPath.getList("data.findAll { it.manufacturer == '" + manufacturerToFilter + "' }.image");
            System.out.println("Images for products with manufacturer '" + manufacturerToFilter + "': " + imagesForEnergizerProducts);

            // 39. Find the createdAt for all categories products whose price > 5.99
            float highPriceThreshold = 5.99f;
            List<String> createdAtForHighPriceProducts = jsonPath.getList("data.findAll { it.price > " + highPriceThreshold + " }.categories.createdAt");
            System.out.println("CreatedAt for products with price > 5.99: " + createdAtForHighPriceProducts);

            // 40. Find the uri of all the products
            List<String> allUris = jsonPath.getList("data.uri");
            System.out.println("URIs of all Products: " + allUris);
        }

        @BeforeClass
        @Override
        public void inIt() {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 3030;
            ProductAssertionTest.response = RestAssured.given()
                    .when()
                    .get("/products")
                    .then().statusCode(200);
        }
    }


