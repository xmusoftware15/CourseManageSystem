package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xmu.crms.entity.School;
import xmu.crms.service.SchoolService;

import javax.jws.WebParam;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/teacher/createSchool")
    public ModelAndView createSchool(ModelAndView modelAndView) {
        modelAndView.setViewName("/teacher/createSchool");
        return modelAndView;
    }

    @PostMapping("/school")
    public School school(@RequestBody School school) {
        BigInteger id = schoolService.insertSchool(school);
        if( id == BigInteger.valueOf(0)){
            System.out.print("id读取失败");

        }
        else if(id == BigInteger.valueOf(-1)){
            System.out.print("学校已被创建");
        }
        else {
            System.out.print(id);
        }
        return school;
    }

    @GetMapping("/school")
    public List<School> school(String city) {
        System.out.print(city);
        List<School> school = new ArrayList<>();
        school = schoolService.listSchoolByCity(city);
        return school;
    }
}
