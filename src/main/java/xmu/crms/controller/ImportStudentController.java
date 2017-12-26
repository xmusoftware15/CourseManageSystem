package xmu.crms.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import xmu.crms.service.ClassService;
import xmu.crms.service.LoginService;
import xmu.crms.service.UserService;
import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * 导入学生列表
 *
 * @author Lulongfei
 *
 */
@Controller
public class ImportStudentController {

    @Autowired
    ClassService classService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    /**
     * 导入处理
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping("/importStudents")
    public @ResponseBody ResponseEntity importHandler(@RequestParam("file") MultipartFile multipartFile,
                                              @RequestParam("classId") BigInteger classId) {

        String filename = multipartFile.getOriginalFilename();

        Workbook workbook = null;
        try {
            String xls = ".xls";
            String xls2 = ".XLS";
            String xlsx = ".xlsx";
            String xlsx2 = ".XLSX";
            if (filename.endsWith(xls) || filename.endsWith(xls2)) {
                workbook = new HSSFWorkbook(multipartFile.getInputStream());
            } else if (filename.endsWith(xlsx) || filename.endsWith(xlsx2)) {
                workbook = new XSSFWorkbook(multipartFile.getInputStream());
            } else {
                throw new Exception("illegal file");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }

        Sheet sheet = workbook.getSheetAt(0);
        int count = sheet.getPhysicalNumberOfRows();
        int i = 0;
        try {
            for (i = 1; i < count; i++) {
                Row row = sheet.getRow(i);

                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String studentNumber = row.getCell(1).getStringCellValue();

                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String studentName = row.getCell(2).getStringCellValue();

                User stu = userService.getUserByUserNumber(studentNumber);
                if (stu == null) {
                    userService.createStudentAccountByNumber(stu);
                    stu = userService.getUserByUserNumber(studentNumber);
                }

                classService.insertCourseSelectionById(stu.getId(), classId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
