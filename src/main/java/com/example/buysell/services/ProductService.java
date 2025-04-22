package com.example.buysell.services;

import com.example.buysell.models.Image;
import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.repositories.ProductRepository;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> listProducts(String title) {
        if (title != null) return
                productRepository.findByTitle(title);//метод для поиска товара по названию
        return productRepository.findAll();
    }

    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        product.setUser(getUserByPrincipal(principal));// 6l
        Image image1;//выделяем память
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);//первая фото для аватарки товара поэтому true ставим
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product.Title: {}; Author email:{}", product.getTitle(), product.getUser().getEmail());//добавили логирование
        Product productFromDB = productRepository.save(product);
        productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId());
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {//возможно надо будет поработать над этим методом
        if (principal == null) {return new User();}
        return userRepository.findByEmail(principal.getName()).get();
    }

    //этот метод нужен для преобразования файла в image
    private Image toImageEntity(MultipartFile file) {
        Image image = new Image();
        try {
            image.setName(file.getName()); // назначаем имя
            image.setOriginalFileName(file.getOriginalFilename()); // назначаем оригинальное имя
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            log.error("Ошибка при преобразовании MultipartFile в сущность Image", e);
            // Можно добавить обработку ошибки, например, выбросить RuntimeException
            throw new RuntimeException("Не удалось преобразовать MultipartFile в сущность Image", e);
        }
        return image;
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public Product getProductByID(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
