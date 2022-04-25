package fr.careco.util;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 *
 * @author Indra Hidayatulloh
 */
public class ExcelXLSX {
     
    private Vector vectorDataExcelXLSX = new Vector();
     
    public ExcelXLSX() {
        String excelXLSXFileName = "/home/nicolas/Téléchargements/fiupdate3 echantillon.xlsx";
        vectorDataExcelXLSX = readDataExcelXLSX(excelXLSXFileName);
        displayDataExcelXLSX(vectorDataExcelXLSX);
    }
     
    public static Vector readDataExcelXLSX(String fileName) {
        Vector vectorData = new Vector();
         
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
             
            XSSFWorkbook xssfWorkBook = new XSSFWorkbook(fileInputStream);
             
            // Read data at sheet 0
            XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(0);
             
            Iterator rowIteration = xssfSheet.rowIterator();
             
            // Looping every row at sheet 0
            while (rowIteration.hasNext()) {
                XSSFRow xssfRow = (XSSFRow) rowIteration.next();
                Iterator cellIteration = xssfRow.cellIterator();
                 
                Vector vectorCellEachRowData = new Vector();
                 
                // Looping every cell in each row at sheet 0
                while (cellIteration.hasNext()) {
                    XSSFCell xssfCell = (XSSFCell) cellIteration.next();
                    vectorCellEachRowData.addElement(xssfCell);
                }
                 
                vectorData.addElement(vectorCellEachRowData);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        return vectorData;
    }
     
    public static void displayDataExcelXLSX(Vector vectorData) {
        // Looping every row data in vector
        for(int i=0; i<vectorData.size(); i++) {
            Vector vectorCellEachRowData = (Vector) vectorData.get(i);
            // looping every cell in each row
            for(int j=0; j<vectorCellEachRowData.size(); j++) {
                System.out.print(vectorCellEachRowData.get(j).toString()+" | ");
            }
            System.out.println("");
        }
    }
     
    public static void main(String[] argas) {
        ExcelXLSX excelXLSX = new ExcelXLSX();
    }
     
}
