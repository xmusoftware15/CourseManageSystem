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
import java.util.Objects;

/**
 * @author 1-11、2-4
 */
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
        if(Objects.equals(id, BigInteger.valueOf(0))){
            System.out.print("id读取失败");

        }
        else if(Objects.equals(id, BigInteger.valueOf(-1))){
            System.out.print("学校已被创建");
        }
        else {
            System.out.print(id);
        }
        return school;
    }

    @GetMapping("/school")
    public List<School> school(@RequestParam("city") String city) {

        System.out.println("city=================>"+city);
        List<School> school = schoolService.listSchoolByCity(city);
        System.out.println(school.toString());
        return school;
    }
}
