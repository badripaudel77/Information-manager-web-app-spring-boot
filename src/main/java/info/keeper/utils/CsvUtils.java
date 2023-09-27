package info.keeper.utils;

import com.opencsv.CSVReader;
import info.keeper.models.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility method that reads the csv file prepares contact objects, adds to list and returns that list.
 */
public class CsvUtils {
    public static List<Contact> readCsvFile(MultipartFile file) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
        List<String[]> rows = csvReader.readAll();
        // Start from index 1 to skip the header row (index 0)
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            Contact contact = new Contact();
            contact.setName(row[0]);
            contact.setEmail(row[1]);
            contact.setPhoneNumber(row[2]);
            contact.setImageURL(row[3]);
            contact.setDescription(row[4]);
            contact.setUser(null);
            contacts.add(contact);
        }
        return contacts;
    }
}
