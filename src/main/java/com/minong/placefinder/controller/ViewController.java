package com.minong.placefinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
  @RequestMapping(value = "/test")
  public String indexView() {
    System.out.println("hi");
    return "index";
  }
}