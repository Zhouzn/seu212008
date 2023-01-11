package com.seu8.nowcoder.community.controller;

import com.seu8.nowcoder.community.service.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return  alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //get request data
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration=request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            String value=request.getHeader(name);
            System.out.println(name+": " +value);
        }
        System.out.println(request.getParameter("code"));

        //get response data
        response.setContentType("text/html;charset=utf-8");
        try(
                PrintWriter writer=response.getWriter();
                ){
            writer.write("<h1>newcoder</h1>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // GET
    // /students?current=1&limit=20
    @RequestMapping(path="/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name="current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123
    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    //POST
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //dynamic HTML data
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "Ohguro Maki");
        mav.addObject("age", 54);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "TokyoUniversity");
        model.addAttribute("age", "-1");
        return "/demo/view";
    }

    // response JSON(Unsynchoronize)
    // java object->JSON string -> JS object

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getEmp(){
        HashMap<String, Object> emp= new HashMap<>();
        emp.put("name","Ohguro Maki");
        emp.put("age","54");
        emp.put("id",53252);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<HashMap<String, Object>> getEmps(){
        ArrayList<HashMap<String, Object>> list= new ArrayList<>();
        HashMap<String, Object> emp= new HashMap<>();
        emp.put("name","Ohguro Maki");
        emp.put("age","54");
        emp.put("id",53252);
        list.add(emp);
        emp= new HashMap<>();
        emp.put("name","Ohguro");
        emp.put("age","5");
        emp.put("id",53);
        list.add(emp);
        return list;
    }


}

