package baltic.amadeus.pizzacooker.component;

import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductComponent {
    private ModelMapper modelMapper = new ModelMapper();

    public ProductDetails convertToDto(Product product) {
        ProductDetails productDetails = modelMapper.map(product, ProductDetails.class);
        return productDetails;
    }

    public Product convertToEntity(ProductDetails productDetails) {
        Product product = modelMapper.map(productDetails, Product.class);
        return product;
    }

    public Set<ProductDetails> convertToDto(Set<Product> products) {
        return products.stream()
                .map(product -> convertToDto(product))
                .collect(Collectors.toSet());
    }

    public Set<Product> convertToEntity(Set<ProductDetails> productsDetails) {
        return productsDetails.stream()
                .map(productDetails -> convertToEntity(productDetails))
                .collect(Collectors.toSet());
    }
}
