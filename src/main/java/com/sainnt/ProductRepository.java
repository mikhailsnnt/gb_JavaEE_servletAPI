package com.sainnt;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> load(long id);
    List<Product> loadAll();
    void save(Product product);
}
