package com.oopsmails.springboot.mockbackend.xlsx;

import com.oopsmails.springboot.mockbackend.SpringBootBackendMockApplication;
import com.oopsmails.springboot.mockbackend.util.ResourceLoaderFile;
import com.oopsmails.springboot.mockbackend.xlsx.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        SpringBootBackendMockApplication.class,
        XlsxTest.XlsxTestConfig.class
})
public class XlsxTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;


    @Test
    public void testReadTxtFile() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:sampleTxt.txt");
        File file = resource.getFile();
        String s = new String(Files.readAllBytes(file.toPath()));
        System.out.println(s);
        assertTrue(resource != null);
    }

    @Test
    public void testReadXlsxFile() throws Exception {
        List<Person> items = new ArrayList<>();
        InputStream inputStream = null;

        try {
            Resource resource = resourceLoader.getResource("classpath:sampleXlsx.xlsx");
            File file = resource.getFile();
            inputStream = new FileInputStream(file);
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(0);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
//            if (rowIterator.hasNext()) {
//                rowIterator.next();
//            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Person person = new Person();

                person.setName(row.getCell(0).getStringCellValue());
                person.setAge((int) row.getCell(1).getNumericCellValue());
                person.setEmail(row.getCell(2).getStringCellValue());

                items.add(person);
            }

            log.info("items.size = {}", items.size());
        } catch (IOException | IllegalStateException e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        assertTrue(items != null);
    }

    @TestConfiguration
    public static class XlsxTestConfig {
        @Bean
        public Clock appClock() {
            LocalDateTime mockNow = LocalDateTime.of(2019, Month.FEBRUARY, 20, 10, 11, 20);
            Clock result = Clock.fixed(mockNow.atZone(ZoneId.of("Canada/Eastern")).toInstant(), ZoneId.of("Canada/Eastern"));

            return result;
        }

        @Bean
        public ResourceLoaderFile fileLoaderClassPath() {
            ResourceLoaderFile result = new ResourceLoaderFile();

            return result;
        }
    }
}

