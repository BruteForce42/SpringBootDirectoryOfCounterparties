package com.zatsepin.controller;

import com.zatsepin.entity.Counterparty;
import com.zatsepin.service.CounterpartyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/counterparties")
public class CounterpartyController {

    private final Logger logger = LoggerFactory.getLogger(CounterpartyController.class);

    @Autowired
    private CounterpartyServiceImpl counterpartyService;

    @GetMapping
    public String list(Model uiModel) {
        logger.info("Listing counterparties");
        List<Counterparty> counterparties = counterpartyService.findAll();
        uiModel.addAttribute("counterparties", counterparties);
        logger.info("Number of counterparties: " + counterparties.size());
        return "counterparties";
    }

    @GetMapping(value = "/{id}")
    public String showById(@PathVariable("id") Long id, Model uiModel) {
        logger.info("Counterparty requested with id: " + id);
        Counterparty counterparty = counterpartyService.findById(id).get();
        uiModel.addAttribute("counterparty", counterparty);
        return "show";
    }

    @GetMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable Long id, Model uiModel) {
        uiModel.addAttribute("counterparty", counterpartyService.findById(id));
        return "update";
    }

    @GetMapping(value = "/new")
    public String createForm(Model uiModel) {
        Counterparty counterparty = new Counterparty();
        uiModel.addAttribute("counterparty", counterparty);
        return "update";
    }

    @PostMapping
    public String create(@Valid Counterparty counterparty) {
        logger.info("Creating counterparty");
        counterpartyService.save(counterparty);
        return "redirect:/counterparties/" + counterparty.getId();
    }

    @PostMapping(value = "/{id}")
    public String update(@Valid Counterparty counterparty) {
        logger.info("Updating counterparty");
        counterpartyService.save(counterparty);
        return "redirect:/counterparties/" + counterparty.getId();
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Counterparty requested to be deleted with id: " + id);
        counterpartyService.delete(id);
        return "redirect:/counterparties";
    }

}
