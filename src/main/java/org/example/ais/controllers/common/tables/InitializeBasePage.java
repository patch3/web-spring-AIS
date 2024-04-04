package org.example.ais.controllers.common.tables;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public interface InitializeBasePage {
    default String initializeBasePage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMassage", error);
        }
        return getStaticPathToBasePage();
    }

    default String getErrorMessage(String error) { return "An unexpected error occurred"; }
    String getStaticPathToBasePage();
}
