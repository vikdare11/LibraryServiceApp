package document;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public interface IDocumentGenerator {
    void generateBooksList() throws FileNotFoundException, DocumentException;
    void generateUsersList() throws FileNotFoundException, DocumentException;
    void generateBooksInfo() throws FileNotFoundException, DocumentException;
    void generateViewsStatistic() throws FileNotFoundException, DocumentException;
    void generateBookCollectionsOfReaders() throws FileNotFoundException, DocumentException;
}
