package com.sainnt.servlet;

import com.sainnt.Product;
import com.sainnt.ProductRepository;
import com.sainnt.ProductRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Stream;

@WebServlet(name = "product", urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong( req.getPathInfo().substring(1));
        ProductRepository repository = new ProductRepositoryImpl();
        Stream.of(
                new Product("Product1",120),
                new Product("Product2",1921),
                new Product("Product3",1821),
                new Product("Product4",1721),
                new Product("Product5",6121),
                new Product("Product6",5121),
                new Product("Product7",4121),
                new Product("Product8",1421),
                new Product("Product9",1211),
                new Product("Product9",1223)
        ).forEach(repository::save);
        Optional<Product> productOptional = repository.load(id);
        PrintWriter writer = resp.getWriter();
        productOptional.ifPresentOrElse(product ->
                writer.printf("""
                        <a href="%s">Back<a/>
                        <h1>Product #%d<h1/>
                        <p>%s<p/>
                        <p>%d<p/>
                        """,
                        req.getContextPath()+"/product",
                        product.getId(),
                        product.getTitle(),
                        product.getCost()),
                ()-> {  writer.printf("""
                        <a href="%s">Back<a/>
                        <h1>Product #%d not found<h1/>
                        """,
                        req.getContextPath()+"/product",
                        id);
            resp.setStatus(403);
        });
    }
}
