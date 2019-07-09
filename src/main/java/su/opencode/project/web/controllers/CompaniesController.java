package su.opencode.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import su.opencode.project.web.project.persistence.model.Company;
import su.opencode.project.web.project.persistence.services.CompaniesDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompaniesController {
    @Autowired
    CompaniesDataService companiesDataService;

    @GetMapping
    public ModelAndView getCompaniesPage() {
        List<Company> companies = new ArrayList<>();
        companies.addAll((Collection<? extends Company>) companiesDataService.findAll());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companies", companies);
        modelAndView.setViewName("companies");
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        companiesDataService.deleteById(id);

        return "redirect:/companies";
    }

    @PostMapping("/save")
    public String save(Company company) {
        // Если есть id обновит запись, иначе добавит как новую
        companiesDataService.save(company);

        return "redirect:/companies";
    }
}
