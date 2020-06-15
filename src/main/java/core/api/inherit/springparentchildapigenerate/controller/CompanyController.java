package core.api.inherit.springparentchildapigenerate.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/company")
public class CompanyController {

    final static Logger logger = Logger.getLogger(CompanyController.class);

    @RequestMapping(value="/content", method = RequestMethod.GET)
    public ModelAndView doView() {
        logger.info("Call doView");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fico/company :: content");
        return modelAndView;
    }
} 