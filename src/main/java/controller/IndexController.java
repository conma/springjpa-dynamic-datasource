package controller;

import config.DynamicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.SomeService;

@Controller("/")
public class IndexController {

    @Autowired
    private SomeService someService;
    @Autowired
    private DynamicDataSource dynamicDataSource;

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(path = "")
    public String lineAuth(@RequestParam Integer dbId) {
        dynamicDataSource.connect(dbId);
        log.debug("Connected to db: dbId:{}", dbId);
        String page = someService.returnSomeText();
        return page;
    }

}
