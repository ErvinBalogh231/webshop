package hu.balogh.webshop.controller;

import hu.balogh.webshop.entity.Item;
import hu.balogh.webshop.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class MainController {
    private final DatabaseService databaseService;

    @Autowired
    public MainController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("items", databaseService.fetchItems());

        return "index";
    }

    @GetMapping("/item/{id}")
    public String itemView(@PathVariable int id, Model model) {
        model.addAttribute("itemId", id);

        return "itemView";
    }

    @PostMapping("/insertItem")
    public String insertItem(@RequestParam String name, @RequestParam BigDecimal price, Model model) {
        model.addAttribute("name", name);

        databaseService.insertItem(new Item(name, price));

        return "itemInsertSuccesfull";
    }
}
