package com.sainnt;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepositoryImpl implements ProductRepository{
    private final Map<Long, Product> idOverProduct = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public Optional<Product> load(long id) {
        return Optional.ofNullable(idOverProduct.get(id));
    }

    @Override
    public List<Product> loadAll() {
        return idOverProduct.values().stream().toList();
    }

    @Override
    public void save(Product product) {
        product.setId(nextId.getAndIncrement());
        idOverProduct.put(product.getId(), product);
    }
}
