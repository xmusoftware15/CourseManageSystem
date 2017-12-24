package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.dao.SchoolDAO;
import xmu.crms.entity.School;
import xmu.crms.service.SchoolService;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author badcode
 * @date 2017/12/01
 */
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<School> getSchools(String city) {
        return schoolService.listSchoolByCity(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public School addSchool(@RequestBody School school) {
        BigInteger id = schoolService.insertSchool(school);
        School newSchool = new School();
        newSchool.setId(id);
        return newSchool;
    }
}
