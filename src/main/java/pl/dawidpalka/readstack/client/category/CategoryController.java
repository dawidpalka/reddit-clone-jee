package pl.dawidpalka.readstack.client.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.dawidpalka.readstack.domain.api.CategoryFullInfo;
import pl.dawidpalka.readstack.domain.api.CategoryService;
import pl.dawidpalka.readstack.domain.api.DiscoveryBasicInfo;
import pl.dawidpalka.readstack.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final DiscoveryService discoveryService = new DiscoveryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        CategoryFullInfo category = categoryService.findById(categoryId)
                .orElseThrow();
        req.setAttribute("category", category);
        List<DiscoveryBasicInfo> discoveries = discoveryService.findByCategory(categoryId);
        req.setAttribute("discoveries", discoveries);
        req.getRequestDispatcher("/category.jsp").forward(req, resp);
    }
}
