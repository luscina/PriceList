package pl.slowik.PriceList.catalog.application;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class CatalogService {
    public void initialize() {
        FileInputStream file;
        {
            try {
                file = new FileInputStream(new File("CENNIK SMB 15.03.2023.xls"));
                HSSFWorkbook workbook = new HSSFWorkbook(file);
                HSSFSheet sheet = workbook.getSheetAt(0);
                HSSFRow headers = sheet.getRow(1);
                Iterator<Row> rowIterator = sheet.iterator();
                int physicalNumberOfCells = headers.getPhysicalNumberOfCells();
                System.out.println(physicalNumberOfCells);
                Iterator<Cell> cellIterator = headers.iterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType().equals(CellType.STRING)) {
                        System.out.println(cell.getStringCellValue());
                    } else if (cell.getCellType().equals(CellType.NUMERIC)) {
                        System.out.println(cell.getNumericCellValue());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
