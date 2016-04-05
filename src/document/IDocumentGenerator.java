package document;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface IDocumentGenerator {
    void generateBooksList(String outputFile) throws IOException, DocumentException;
    void generateUsersList(String outputFile) throws IOException, DocumentException;
    void generateBooksInfo(String outputFile) throws IOException, DocumentException;
    void generateViewsStatistic(String outputFile) throws IOException, DocumentException;
    void generateBookCollectionsOfReaders(String outputFile) throws IOException, DocumentException;
}
