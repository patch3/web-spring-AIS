package org.example.ais.controllers.staff.tables;

import org.example.ais.controllers.common.tables.InitializeBasePage;
import org.example.ais.repositorys.LoanRequestHistoryRepository;
import org.example.ais.services.LoanRequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/staff/request-history", produces = MediaType.TEXT_HTML_VALUE)
public final class RequestHistoryController implements InitializeBasePage {
    private final LoanRequestHistoryService requestHistoryService;

    public RequestHistoryController(LoanRequestHistoryService requestHistoryService) {
        this.requestHistoryService = requestHistoryService;
    }


    @Override
    @GetMapping
    public String initializeBasePage(String error, Model model) {
        model.addAttribute("namePage", "Request history");
        return InitializeBasePage.super.initializeBasePage(error, model);
    }




    @Override
    public String getStaticPathToBasePage() {
        return "/staff/tables/request-history";
    }
}