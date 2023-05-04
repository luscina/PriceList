package pl.slowik.PriceList.catalog.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slowik.PriceList.catalog.db.*;
import pl.slowik.PriceList.catalog.domain.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogInitializeService {
    private final JpaNotebookRepository notebookRepository;
    private final JpaComponentRepository componentRepository;
    private final JpaModelRepository modelRepository;
    private final JpaWarrantyRepository warrantyRepository;
    @Transactional
    public void initializeNotebooks() {
        FileInputStream file;
            try {
                file = new FileInputStream("CENNIK SMB 15.03.2023.xls");
                HSSFWorkbook workbook = new HSSFWorkbook(file);
                HSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex("Notebooki"));
                HSSFRow headersRow = sheet.getRow(1);
                List<String> headersValue = createHeadrsValueList(headersRow);
                for (int i = 2; i < 279; i++) {
                    Notebook notebook = new Notebook();
                    HSSFRow row = sheet.getRow(i);
                    notebook.setPn(row.getCell(headersValue.indexOf("PN")).getStringCellValue());
                    if(modelRepository.findByPn(notebook.getPn().substring(0, 4)).isPresent()) {
                        Model model = modelRepository.findByPn(notebook.getPn().substring(0, 4)).orElseThrow();
                        notebook.setModel(model);
                    }
                    notebook.setEanCode(row.getCell(headersValue.indexOf("UPC_EAN_JAN_code")).getStringCellValue());
                    notebook.setProductFamily(row.getCell(headersValue.indexOf("Product FAMILY")).getStringCellValue());
                    notebook.setProductSeries(row.getCell(headersValue.indexOf("Product Series")).getStringCellValue());
                    notebook.setStatus(row.getCell(headersValue.indexOf("Status")).getStringCellValue());
                    notebook.setBpPrice(BigDecimal.valueOf(row.getCell(headersValue.indexOf("BP Estimated € Price")).getNumericCellValue()));
                    notebook.setBpPricePln(BigDecimal.valueOf(row.getCell(headersValue.indexOf("BP PLN")).getNumericCellValue()));
                    notebook.setSrpPrice(BigDecimal.valueOf(row.getCell(headersValue.indexOf("SRP incl. VAT (PLN)")).getNumericCellValue()));
                    notebook.setBase(getHSSFCellValue(row.getCell(headersValue.indexOf("Base"))));
                    notebook.setColor(getHSSFCellValue(row.getCell(headersValue.indexOf("Color"))));
                    notebook.setPanel(row.getCell(headersValue.indexOf("Panel")).getStringCellValue());
                    notebook.setCup(row.getCell(headersValue.indexOf("CPU")).getStringCellValue());
                    notebook.setMemory(row.getCell(headersValue.indexOf("Memory")).getStringCellValue());
                    notebook.setSsd(row.getCell(headersValue.indexOf("SSD")).getStringCellValue());
                    notebook.setHdd(row.getCell(headersValue.indexOf("HDD")).getStringCellValue());
                    notebook.setGraphics(row.getCell(headersValue.indexOf("Graphics")).getStringCellValue());
                    notebook.setOdd(row.getCell(headersValue.indexOf("ODD")).getStringCellValue());
                    notebook.setWlan(row.getCell(headersValue.indexOf("WLAN")).getStringCellValue());
                    notebook.setWwan(getHSSFCellValue(row.getCell(headersValue.indexOf("WWAN"))));
                    notebook.setBacklit(row.getCell(headersValue.indexOf("Backlit")).getStringCellValue());
                    notebook.setFrp(row.getCell(headersValue.indexOf("FPR")).getStringCellValue());
                    notebook.setCamera(row.getCell(headersValue.indexOf("Camera")).getStringCellValue());
                    notebook.setKeyboard(row.getCell(headersValue.indexOf("Keyboard")).getStringCellValue());
                    notebook.setCardReader(getHSSFCellValue(row.getCell(headersValue.indexOf("CardReader"))));
                    notebook.setOs(row.getCell(headersValue.indexOf("OS")).getStringCellValue());
                    notebook.setWarranty(row.getCell(headersValue.indexOf("Warranty")).getStringCellValue());
                    notebook.setBattery(row.getCell(headersValue.indexOf("Battery")).getStringCellValue());
                    notebook.setAdapter(row.getCell(headersValue.indexOf("Adapter")).getStringCellValue());
                    if(notebook.getModel() != null) {
                        notebookRepository.save(notebook);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private List<String> createHeadrsValueList(HSSFRow headersRow) {
        List<String> headersValue = new ArrayList<>();
        for (int i = 0; i < headersRow.getLastCellNum(); i++) {
            headersValue.add(getHSSFCellValue(headersRow.getCell(i)));
        }
        return headersValue;
    }

    @Transactional
    public void initializeComponents() {
        FileInputStream file;
        try{
            file = new FileInputStream("CENNIK SMB 15.03.2023.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex("Akcesoria"));
            HSSFRow headersRow = sheet.getRow(1);
            List<String> headersValue = createHeadrsValueList(headersRow);
            for (int i = 2; i < sheet.getLastRowNum(); i++) {
                if(!isHSSFCellEmpty(sheet.getRow(i).getCell(0))){
                    HSSFRow row = sheet.getRow(i);
                    Component component = new Component();
                    component.setPn(getHSSFCellValue(row.getCell(headersValue.indexOf("PN"))));
                    component.setCategory(getHSSFCellValue(row.getCell(headersValue.indexOf("Category"))));
                    component.setSubCategory(getHSSFCellValue(row.getCell(headersValue.indexOf("SubCategory"))));
                    component.setName(getHSSFCellValue(row.getCell(headersValue.indexOf("DESCRIPTION"))));
                    if (!isHSSFCellEmpty(row.getCell(headersValue.indexOf("EAN code")))){
                        component.setEAN(getHSSFCellValue(row.getCell(headersValue.indexOf("EAN code"))));
                    }
                    component.setBpPrice(BigDecimal.valueOf(row.getCell(headersValue.indexOf("BP Estimated € Price")).getNumericCellValue()));
                    componentRepository.save(component);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public void initializeModels() {
        FileInputStream file;
        try {
            file = new FileInputStream("ocm_apr_2023.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            List<Integer> notebookSheetsIndexes = generateNotebookSheetsIndexes(workbook);
            for(Integer sheetIndex : notebookSheetsIndexes) {
                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                XSSFRow headersRow = sheet.getRow(1);
                int lastCellNum = headersRow.getLastCellNum();
                for (int i = 3; i < lastCellNum; i++) {
                    XSSFCell cell = headersRow.getCell(i);
                    if (cell != null) {
                        Set<String> codes = extractModelCode(cell.getStringCellValue());
                        if (codes != null) {
                            for (String code : codes) {
                                log.info(code);
                                Model model = new Model();
                                model.setPn(code);
                                for (int j = 3; j < sheet.getLastRowNum(); j++) {
                                    if (!(sheet.getRow(j) == null)) {
                                        XSSFRow row = sheet.getRow(j);
                                        if (!isXSSFCellEmpty(row.getCell(i))) {
                                            if (componentRepository.findByPn(getXSSFCellValue(row.getCell(2))).isPresent()) {
                                                Component component = componentRepository.findByPn(getXSSFCellValue(row.getCell(2))).orElseThrow();
                                                ComponentModel componentModel = new ComponentModel();
                                                componentModel.setModel(model);
                                                componentModel.setComponent(component);
                                                componentModel.setComment(getXSSFCellValue(row.getCell(i)));
                                                model.addComponentModel(componentModel);
                                            }
                                        }
                                    }
                                }
                                modelRepository.save(model);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> generateNotebookSheetsIndexes(XSSFWorkbook workbook){
        List<Integer> notebookSheetIndexes = new ArrayList<>();
        notebookSheetIndexes.add(workbook.getSheetIndex("TP  X"));
        notebookSheetIndexes.add(workbook.getSheetIndex("TP  L"));
        notebookSheetIndexes.add(workbook.getSheetIndex("TP T"));
        notebookSheetIndexes.add(workbook.getSheetIndex("TP  P"));
        notebookSheetIndexes.add(workbook.getSheetIndex("TP Z"));
        notebookSheetIndexes.add(workbook.getSheetIndex("TP C"));
        notebookSheetIndexes.add(workbook.getSheetIndex("TP  E"));
        notebookSheetIndexes.add(workbook.getSheetIndex("ThinkBook  "));
        return notebookSheetIndexes;
    }


    public void initializeWarranties() {
        FileInputStream file;
        try {
            file = new FileInputStream("CENNIK SMB 15.03.2023.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            int warrantySheetIndex = workbook.getSheetIndex("Gwarancje");
            HSSFSheet sheet = workbook.getSheetAt(warrantySheetIndex);
            int lastRowNum = sheet.getLastRowNum();
            List<String> headersRowValue = createHeadrerRowValueList(sheet);
            List<String> excludedWarrantiesBrand = generateExcludedWarrantiesBrand();
            for (int j = 3; j < lastRowNum; j++) {
                if(sheet.getRow(j) != null) {
                    HSSFRow row = sheet.getRow(j);
                    String brand = getHSSFCellValue(row.getCell(headersRowValue.indexOf("Brand")));
                    if (!excludedWarrantiesBrand.contains(brand)) {
                        String modelCode = getHSSFCellValue(row.getCell(headersRowValue.indexOf("Machine Type")));
                        if (modelRepository.findByPn(modelCode).isPresent()) {
                            Warranty warranty = new Warranty();
                            warranty.setPn(getHSSFCellValue(row.getCell(headersRowValue.indexOf("ePack Part Number"))));
                            warranty.setBaseWarranty(getHSSFCellValue(row.getCell(headersRowValue.indexOf("Base Warranty"))));
                            warranty.setDescription(getHSSFCellValue(row.getCell(headersRowValue.indexOf("Upgrade Service Description"))));
                            warranty.setBpPrice(BigDecimal.valueOf(row.getCell(headersRowValue.indexOf("BP Estimated € Price")).getNumericCellValue()));
                            warranty.setModel(modelRepository.findByPn(modelCode).orElseThrow());
                            warrantyRepository.save(warranty);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> createHeadrerRowValueList(HSSFSheet sheet) {
        HSSFRow headerRow = sheet.getRow(2);
        short lastCellNum = headerRow.getLastCellNum();
        List<String> headersRowValue = new ArrayList<>();
        for (int i = 0; i < lastCellNum; i++) {
            if(headerRow.getCell(i) != null) {
                headersRowValue.add(getHSSFCellValue(headerRow.getCell(i)));
            }
        }
        return headersRowValue;
    }

    public List<String> generateExcludedWarrantiesBrand(){
        List<String> excludedWarrantiesBrand = new ArrayList<>();
        excludedWarrantiesBrand.add("Idea Tablet Entry");
        excludedWarrantiesBrand.add("ThinkSmart");
        excludedWarrantiesBrand.add("TP Halo");
        return excludedWarrantiesBrand;
    }

    private Set<String> extractModelCode(String header) {
        String pattern = "\\b2[A-Za-z0-9]{3}\\b";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(header);
        Set<String> codes = new HashSet<>();
        while (matcher.find()){
            String code = matcher.group(0);
            if(!codes.contains(code)){
                codes.add(code);
            }
        }
        if(codes.size() > 0) {
            return codes;
        } else return null;
    }

    public static boolean isHSSFCellEmpty(final HSSFCell cell) {
        if (cell == null) {
            return true;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return true;
        }

        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
            return true;
        }

        return false;
    }
    public static boolean isXSSFCellEmpty(final XSSFCell cell) {
        if (cell == null) {
            return true;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return true;
        }

        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
            return true;
        }

        return false;
    }

    private String getHSSFCellValue(HSSFCell cell) {
        if(cell.getCellType().equals(CellType.NUMERIC)){
            return String.valueOf(cell.getNumericCellValue());
        }else if(cell.getCellType().equals(CellType.STRING))
            return cell.getStringCellValue();
        else return "";
    }
    private String getXSSFCellValue(XSSFCell cell) {
        if(cell.getCellType().equals(CellType.NUMERIC)){
            return String.valueOf(cell.getNumericCellValue());
        }else if(cell.getCellType().equals(CellType.STRING))
            return cell.getStringCellValue();
        else return "";
    }
}
