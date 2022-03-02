package com.sainnt.servlet;

import com.sainnt.Product;
import com.sainnt.ProductRepository;
import com.sainnt.ProductRepositoryImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

@WebServlet(name = "products",urlPatterns = "/product")
public class ProductsListServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductRepository rep = new ProductRepositoryImpl();
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
        ).forEach(rep::save);
        PrintWriter writer = resp.getWriter();
        writer.println("""
                <table>
                <tr>
                <th>Id<th/>
                <th>Title<th/>
                <th>Cost<th/>
                <tr/>
                <table/>
                """);
        rep.loadAll().forEach(
                product->
            writer.printf("""
                    <tr>
                    <td><a href="%s">%d<a/><td/>
                    <td>%s<td/>
                    <td>%d<td/>
                    """,
                    req.getContextPath()+"/product/"+product.getId(),
                    product.getId(),
                    product.getTitle(),
                    product.getCost()));
        writer.println("<table/>");
    }
}
