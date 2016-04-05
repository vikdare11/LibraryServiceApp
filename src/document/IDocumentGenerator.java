package document;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDocumentGenerator {
    void generateBooksList() throws IOException, DocumentException;
    void generateUsersList() throws IOException, DocumentException;
    void generateBooksInfo() throws IOException, DocumentException;
    void generateViewsStatistic() throws IOException, DocumentException;
    void generateBookCollectionsOfReaders() throws IOException, DocumentException;
}
