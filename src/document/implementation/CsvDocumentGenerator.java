package document.implementation;

import com.itextpdf.text.DocumentException;
import document.IDocumentGenerator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;
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
    public void generateBooksList(HttpServletResponse response) throws IOException, DocumentException {
        xlsGenerator.generateBooksList(response);
        gerenerateCsvFile(response);
    }

    @Override
    public void generateUsersList(HttpServletResponse response) throws IOException, DocumentException {
        xlsGenerator.generateUsersList(response);
        gerenerateCsvFile(response);
    }

    @Override
    public void generateBooksInfo(HttpServletResponse response) throws IOException, DocumentException {
        xlsGenerator.generateBooksInfo(response);
        gerenerateCsvFile(response);
    }

    @Override
    public void generateViewsStatistic(HttpServletResponse response) throws IOException, DocumentException {
        xlsGenerator.generateViewsStatistic(response);
        gerenerateCsvFile(response);
    }

    @Override
    public void generateBookCollectionsOfReaders(HttpServletResponse response) throws IOException, DocumentException {
        xlsGenerator.generateBookCollectionsOfReaders(response);
        gerenerateCsvFile(response);
    }

    private void gerenerateCsvFile(HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        ByteArrayOutputStream baos = (ByteArrayOutputStream) os;
        File tempFile = convertXlsToCsv(this.fromOutputStreamToInputStream(baos));
        InputStream inputStream = new FileInputStream(tempFile);
        int c = 0;
        byte[] buf = new byte[8192];
        while ((c = inputStream.read(buf, 0, buf.length)) > 0) {
            os.write(buf, 0, c);
            os.flush();
        }
        os.close();
    }

    private File convertXlsToCsv(InputStream inputStream) {
        StringBuffer data = new StringBuffer();
        try
        {
            File temp = File.createTempFile("tempfile", ".csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
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
            bw.write(data.toString());
            bw.close();
            return temp;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream fromOutputStreamToInputStream(ByteArrayOutputStream outputStream){
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
