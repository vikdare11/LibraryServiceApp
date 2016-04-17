package document.implementation;

import com.itextpdf.text.DocumentException;
import document.IDocumentGenerator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.Iterator;

public class CsvDocumentGenerator implements IDocumentGenerator {

    private static final IDocumentGenerator instance = new CsvDocumentGenerator();

    private CsvDocumentGenerator(){}

    public static IDocumentGenerator getInstance() {
        return instance;
    }

    private IDocumentGenerator xlsGenerator = XlsDocumentGenerator.getInstance();

    @Override
    public void generateBooksList(String outputFile) throws IOException, DocumentException {
        String xlsFile = "D://temp//booksListDocument.xls";
        xlsGenerator.generateBooksList(xlsFile);
        convertXlsToCsv(xlsFile, outputFile);
    }

    @Override
    public void generateUsersList(String outputFile) throws IOException, DocumentException {
        String xlsFile = "D://temp//usersListDocument.xls";
        xlsGenerator.generateUsersList(xlsFile);
        convertXlsToCsv(xlsFile, outputFile);
    }

    @Override
    public void generateBooksInfo(String outputFile) throws IOException, DocumentException {
        String xlsFile = "D://temp//booksInfoDocument.xls";
        xlsGenerator.generateBooksInfo(xlsFile);
        convertXlsToCsv(xlsFile, outputFile);
    }

    @Override
    public void generateViewsStatistic(String outputFile) throws IOException, DocumentException {
        String xlsFile = "D://temp//booksViewStatistic.xls";
        xlsGenerator.generateViewsStatistic(xlsFile);
        convertXlsToCsv(xlsFile, outputFile);
    }

    @Override
    public void generateBookCollectionsOfReaders(String outputFile) throws IOException, DocumentException {
        String xlsFile = "D://temp//usersBookCollections.xls";
        xlsGenerator.generateBookCollectionsOfReaders(xlsFile);
        convertXlsToCsv(xlsFile, outputFile);
    }

    private void convertXlsToCsv(String inputFile, String outputFile) {
        StringBuffer data = new StringBuffer();
        try
        {
            FileOutputStream fos = new FileOutputStream(outputFile);
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
            HSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell;
            Row row;
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    cell = cellIterator.next();
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_STRING:
                            String value = cell.getStringCellValue();
                            if (value.split(",")[0] != value) {
                                data.append('"' + cell.getStringCellValue() + '"' + ",");
                            } else {
                                data.append(cell.getStringCellValue() + ",");
                            }
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            data.append("" + ",");
                            break;
                        default:
                            data.append(cell + ",");
                    }
                }
                data.append('\n');
            }
            fos.write(data.toString().getBytes());
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
