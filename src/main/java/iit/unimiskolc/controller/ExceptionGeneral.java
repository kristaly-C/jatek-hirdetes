package iit.unimiskolc.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionGeneral {


    public String exception(Exception ex, Model model){
        model.addAttribute("exception",ex);
        return "whateverHandlesTheException";
    }
}
