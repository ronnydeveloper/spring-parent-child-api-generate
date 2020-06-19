package org.api.spring.generate.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/itemcategory")
public class ItemCategoryController {

    final static Logger logger = Logger.getLogger(ItemCategoryController.class);

    @RequestMapping(value="/content", method = RequestMethod.GET)
    public ModelAndView doView() {
        logger.info("Call doView");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fico/itemcategory :: content");
        return modelAndView;
    }
} 