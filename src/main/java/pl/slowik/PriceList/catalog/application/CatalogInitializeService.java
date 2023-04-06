package pl.slowik.PriceList.catalog.application;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.domain.Notebook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CatalogInitializeService {
    private final JpaNotebookRepository notebookRepository;
    @Transactional
    public void initialize() {
        FileInputStream file;
            try {
                file = new FileInputStream(new File("CENNIK SMB 15.03.2023.xls"));
                HSSFWorkbook workbook = new HSSFWorkbook(file);
                HSSFSheet sheet = workbook.getSheetAt(0);
                String sheetName = sheet.getSheetName();
                int lastRowNum = sheet.getLastRowNum();
                System.out.println(sheetName + " " + lastRowNum);
                for (int i = 2; i < 279; i++) {
                    Notebook notebook = new Notebook();
                    HSSFRow row = sheet.getRow(i);
                    notebook.setPn(row.getCell(0).getStringCellValue());
                    notebook.setEanCode(row.getCell(1).getStringCellValue());
                    notebook.setProductFamily(row.getCell(2).getStringCellValue());
                    notebook.setProductSeries(row.getCell(3).getStringCellValue());
                    notebook.setStatus(row.getCell(4).getStringCellValue());
                    notebook.setBpPrice(BigDecimal.valueOf(row.getCell(5).getNumericCellValue()));
                    notebook.setBpPricePln(BigDecimal.valueOf(row.getCell(6).getNumericCellValue()));
                    notebook.setSrpPrice(BigDecimal.valueOf(row.getCell(7).getNumericCellValue()));
                    notebook.setBase(getCellValue(row.getCell(8)));
                    notebook.setColor(getCellValue(row.getCell(9)));
                    notebook.setPanel(row.getCell(10).getStringCellValue());
                    notebook.setCup(row.getCell(11).getStringCellValue());
                    notebook.setMemory(row.getCell(12).getStringCellValue());
                    notebook.setSsd(row.getCell(13).getStringCellValue());
                    notebook.setHdd(row.getCell(14).getStringCellValue());
                    notebook.setGraphics(row.getCell(15).getStringCellValue());
                    notebook.setOdd(row.getCell(16).getStringCellValue());
                    notebook.setWlan(row.getCell(17).getStringCellValue());
                    notebook.setWwan(getCellValue(row.getCell(18)));
                    notebook.setBacklit(row.getCell(19).getStringCellValue());
                    notebook.setFrp(row.getCell(20).getStringCellValue());
                    notebook.setCamera(row.getCell(21).getStringCellValue());
                    notebook.setKeyboard(row.getCell(22).getStringCellValue());
                    notebook.setCardReader(getCellValue(row.getCell(23)));
                    notebook.setOs(row.getCell(24).getStringCellValue());
                    notebook.setWarranty(row.getCell(25).getStringCellValue());
                    notebook.setBattery(row.getCell(26).getStringCellValue());
                    notebook.setAdapter(row.getCell(27).getStringCellValue());
                    notebookRepository.save(notebook);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private String getCellValue(HSSFCell cell) {
        if(cell.getCellType().equals(CellType.NUMERIC)){
            return String.valueOf(cell.getNumericCellValue());
        }else
            return cell.getStringCellValue();
    }
}
