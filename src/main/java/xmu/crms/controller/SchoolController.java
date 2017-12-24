package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xmu.crms.entity.School;
import xmu.crms.service.SchoolService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

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
