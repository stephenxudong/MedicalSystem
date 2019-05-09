package web.controller;
import org.apache.commons.logging.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/product")
public class Product {
    private static final Log logger = LogFactory.getLog(Product.class);
    @RequestMapping(value = "/product1")
    public String product(){
        return "product";
    }
}
