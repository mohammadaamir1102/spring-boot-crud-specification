package com.spec.helper;

import com.spec.entity.CustomerDetail;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET="CustomerDetails";

    //checkExcelFormat method is used for checking the format for Excel file type
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals(TYPE)) {
            return true;
        }
        return false;
    }

    //convertExcelToListOfCustomerDetail method is used to convert Excel to List
    public List<CustomerDetail> convertExcelToListOfCustomerDetail(InputStream input){
        List<CustomerDetail> customerDetails = new ArrayList<>();
        try {

            /* here SHEET is belong from Excel sheet,
               XSSFWorkbook used for reading the Excel file that's why taken the input,
               XSSFSheet used for reading the sheet(data) from Excel,
            */
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet sheet = workbook.getSheet(SHEET);
            int rowNumber=0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()){
                Row row = iterator.next();
                if (rowNumber==0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> iterator1 = row.iterator();
                //it is not completed yet

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return customerDetails;
    }

}
