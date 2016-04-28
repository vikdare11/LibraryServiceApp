package document;

import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IDocumentGenerator {
    //void generateBooksList(String outputFile) throws IOException, DocumentException;
    //void generateUsersList(String outputFile) throws IOException, DocumentException;
    //void generateBooksInfo(String outputFile) throws IOException, DocumentException;
    //void generateViewsStatistic(String outputFile) throws IOException, DocumentException;
    //void generateBookCollectionsOfReaders(String outputFile) throws IOException, DocumentException;

    void generateBooksList(HttpServletResponse response) throws IOException, DocumentException;
    void generateUsersList(HttpServletResponse response) throws IOException, DocumentException;
    void generateBooksInfo(HttpServletResponse response) throws IOException, DocumentException;
    void generateViewsStatistic(HttpServletResponse response) throws IOException, DocumentException;
    void generateBookCollectionsOfReaders(HttpServletResponse response) throws IOException, DocumentException;
}
