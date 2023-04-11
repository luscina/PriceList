package pl.slowik.PriceList.catalog.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.domain.Notebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogInitializeService {
    private final JpaNotebookRepository notebookRepository;
    @Transactional
    public void initialize() {
        FileInputStream file;
            try {
                file = new FileInputStream("CENNIK SMB 15.03.2023.xls");
                HSSFWorkbook workbook = new HSSFWorkbook(file);
                HSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex("Notebooki"));
                HSSFRow headersRow = sheet.getRow(1);
                List<String> headersValue = new ArrayList<>();
                for (int i = 0; i < headersRow.getLastCellNum(); i++) {
                    headersValue.add(getCellValue(headersRow.getCell(i)));
                }
                for (int i = 2; i < 279; i++) {
                    Notebook notebook = new Notebook();
                    HSSFRow row = sheet.getRow(i);
                    notebook.setPn(row.getCell(headersValue.indexOf("PN")).getStringCellValue());
                    notebook.setEanCode(row.getCell(headersValue.indexOf("UPC_EAN_JAN_code")).getStringCellValue());
                    notebook.setProductFamily(row.getCell(headersValue.indexOf("Product FAMILY")).getStringCellValue());
                    notebook.setProductSeries(row.getCell(headersValue.indexOf("Product Series")).getStringCellValue());
                    notebook.setStatus(row.getCell(headersValue.indexOf("Status")).getStringCellValue());
                    notebook.setBpPrice(BigDecimal.valueOf(row.getCell(headersValue.indexOf("BP Estimated € Price")).getNumericCellValue()));
                    notebook.setBpPricePln(BigDecimal.valueOf(row.getCell(headersValue.indexOf("BP PLN")).getNumericCellValue()));
                    notebook.setSrpPrice(BigDecimal.valueOf(row.getCell(headersValue.indexOf("SRP incl. VAT (PLN)")).getNumericCellValue()));
                    notebook.setBase(getCellValue(row.getCell(headersValue.indexOf("Base"))));
                    notebook.setColor(getCellValue(row.getCell(headersValue.indexOf("Color"))));
                    notebook.setPanel(row.getCell(headersValue.indexOf("Panel")).getStringCellValue());
                    notebook.setCup(row.getCell(headersValue.indexOf("CPU")).getStringCellValue());
                    notebook.setMemory(row.getCell(headersValue.indexOf("Memory")).getStringCellValue());
                    notebook.setSsd(row.getCell(headersValue.indexOf("SSD")).getStringCellValue());
                    notebook.setHdd(row.getCell(headersValue.indexOf("HDD")).getStringCellValue());
                    notebook.setGraphics(row.getCell(headersValue.indexOf("Graphics")).getStringCellValue());
                    notebook.setOdd(row.getCell(headersValue.indexOf("ODD")).getStringCellValue());
                    notebook.setWlan(row.getCell(headersValue.indexOf("WLAN")).getStringCellValue());
                    notebook.setWwan(getCellValue(row.getCell(headersValue.indexOf("WWAN"))));
                    notebook.setBacklit(row.getCell(headersValue.indexOf("Backlit")).getStringCellValue());
                    notebook.setFrp(row.getCell(headersValue.indexOf("FPR")).getStringCellValue());
                    notebook.setCamera(row.getCell(headersValue.indexOf("Camera")).getStringCellValue());
                    notebook.setKeyboard(row.getCell(headersValue.indexOf("Keyboard")).getStringCellValue());
                    notebook.setCardReader(getCellValue(row.getCell(headersValue.indexOf("CardReader"))));
                    notebook.setOs(row.getCell(headersValue.indexOf("OS")).getStringCellValue());
                    notebook.setWarranty(row.getCell(headersValue.indexOf("Warranty")).getStringCellValue());
                    notebook.setBattery(row.getCell(headersValue.indexOf("Battery")).getStringCellValue());
                    notebook.setAdapter(row.getCell(headersValue.indexOf("Adapter")).getStringCellValue());
                    notebookRepository.save(notebook);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void initializeOCM(){
        FileInputStream file;
        try {
            file = new FileInputStream("ocm_apr_2023.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(4);
            XSSFRow headersRow = sheet.getRow(1);
            int headersRowLastCellNum = headersRow.getLastCellNum();
            List<List<String>> codesList = new ArrayList<>();
            for (int i = 3; i < 39 ; i++) {
                XSSFCell cell = headersRow.getCell(i);
                List<String> codes = extractModelCode(cell.getStringCellValue());
                codesList.add(codes);
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> extractModelCode(String header) {
        String pattern = "\\b2[A-Za-z0-9]{3}\\b";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(header);
        List<String> codes = new ArrayList<>();
        while (matcher.find()){
            String code = matcher.group(0);
            if(!codes.contains(code)){
                codes.add(code);
            }
        }
        if(codes.size() > 1) {
            return codes;
        } else return null;
    }

    private String getCellValue(HSSFCell cell) {
        if(cell.getCellType().equals(CellType.NUMERIC)){
            return String.valueOf(cell.getNumericCellValue());
        }else if(cell.getCellType().equals(CellType.STRING))
            return cell.getStringCellValue();
        else return "";
    }
}
