package info.keeper.service;

import info.keeper.models.Contact;
import info.keeper.repositories.ContactRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service
public class DownloadService {
    @Autowired
    ContactRepository contactRepository;
    public boolean downloadUsersNotes(Integer userId) {
        List<Contact> userContacts = contactRepository.allNotesByUserId(userId);
        if(userContacts.size() <1) return false;
        boolean isSuccess = true;
        String notesFolder = "";
        //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet, a workbook can have multiple sheets : in excel you can have multiple sheets in one excel file
        XSSFSheet sheet = workbook.createSheet("All User Notes");
        Map<Integer, Object[]> data = new TreeMap<>();
        // HEADER ROW
        data.put(1, new Object[]{"ID", "NAME", "EMAIL", "PHONE NUMBER", "IMAGE URL", "DESCRIPTION"});
        // iterate over the fetched data and put it into data map
        // TODO : how to handle for larger number of data download at once ? if user has 100K notes ??
        Integer i = 2;
        for(Contact contact : userContacts) {
           data.put(i, new Object[] {contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber(), contact.getImageURL(), contact.getDescription()});
           i++;
        }
        //Iterate over data and write to sheet
        Set<Integer> keyset = data.keySet();
        int rownum = 0; // tracks the row number from 1 to .....
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++); //whole row
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++); // cell is one box  in excel file []
                if(obj instanceof String)
                    ((Cell) cell).setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        // write to the Excel file
        try {
            //Write the workbook in file system
            String home = System.getProperty("user.home");
            if(new File(home+ "/notes").exists()) {
                notesFolder = "notes";
            }
            else {
                new File(home + "/notes").mkdir();
            }
            FileOutputStream out = new FileOutputStream(home + "/" + notesFolder + "/note_" +userId  + ".xlsx");
            workbook.write(out);
            out.close();
            System.out.println("saved in computer.");
        }
        catch (Exception e) {
            isSuccess = false;
            System.out.println("Something went wrong while downloading files : " + e.getMessage());
        }
        return isSuccess;
    }
}
