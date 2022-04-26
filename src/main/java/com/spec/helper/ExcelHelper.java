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
    static String SHEET = "CustomerDetails";

   /*
    String fileName = br.readLine();
    String extension = fileName.substring(fileName.lastIndexOf('.') + 1);

    by this logic we can get extension of any type of file
    */

    //checkExcelFormat method is used for checking the format for Excel file type
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals(TYPE)) {
            return true;
        }
        return false;
    }

    //convertExcelToListOfCustomerDetail method is used to convert Excel to List
    public static List<CustomerDetail> convertExcelToListOfCustomerDetail(InputStream input) {
        List<CustomerDetail> customerDetails = new ArrayList<>();
        try {

            /* here SHEET is belong from Excel sheet,
               XSSFWorkbook used for reading the Excel file that's why taken the input,
               XSSFSheet used for reading the sheet(data) from Excel,
            */
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet sheet = workbook.getSheet(SHEET);
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                //here we are leaving first row in Excel because first row does not exist tha data
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cellId = 0;
                CustomerDetail customerDetail = new CustomerDetail();
                while (cells.hasNext()) {
                    Cell cellData = cells.next();
                    switch (cellId) {
                        case 0:
                            customerDetail.setFirstName(cellData.getStringCellValue());
                            break;
                        case 1:
                            customerDetail.setLastName(cellData.getStringCellValue());
                            break;
                        case 2:
                            customerDetail.setDob(cellData.getDateCellValue());
                            break;
                        case 3:
                            customerDetail.setMobileNumber(String.valueOf(cellData.getNumericCellValue()));
                            break;
                        case 4:
                            customerDetail.setGender(cellData.getStringCellValue());
                            break;
                        case 5:
                            customerDetail.setStatus(cellData.getBooleanCellValue());
                            break;
                        case 6:
                            customerDetail.setAddress(cellData.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellId++;
                }
                customerDetails.add(customerDetail);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDetails;
    }

}
