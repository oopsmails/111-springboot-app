package com.oopsmails.springboot.mockbackend.test;

import com.oopsmails.springboot.mockbackend.xlsx.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ReadingExcelUnitTest {
    @Test
    void testJaveCommon_1() {
        List<Person> people = new ArrayList<>();
        assertTrue(people != null);

//        File file =
//
//        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//
//            // Skip header row
//            if (rowIterator.hasNext()) {
//                rowIterator.next();
//            }
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                Person person = new Person();
//
//                person.setName(row.getCell(0).getStringCellValue());
//                person.setAge((int) row.getCell(1).getNumericCellValue());
//                person.setEmail(row.getCell(2).getStringCellValue());
//
//                people.add(person);
//            }
//        } catch (IOException | IllegalStateException e) {
//            // Handle exceptions
//            e.printStackTrace();
//        }
    }

}
