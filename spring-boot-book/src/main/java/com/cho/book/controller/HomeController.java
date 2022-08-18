package com.cho.book.controller;

import com.cho.book.model.BookVO;
import com.cho.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value={"","/"}, method = RequestMethod.GET)
    public String home(Model model) {
        List<BookVO> bookList = bookService.selectAll();
        model.addAttribute("BOOKS",bookList);
        return "home";
    }

    @RequestMapping(value={"","/"},  method = RequestMethod.POST)
    public String home(BookVO bookVO) {
        log.debug("받은 데이터" + bookVO);
        bookService.insert(bookVO);
        return "redirect:/";
    }
}
