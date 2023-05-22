package sigua.giorgi.task.crud.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import sigua.giorgi.task.crud.model.request.Applicant;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvGenerator {

    public void writeStudentsToCsv(List<Applicant> applicants, Writer writer) {
        try {

            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord("Applicant ID", "Full Name", "Profession");
            for (Applicant applicant : applicants) {
                printer.printRecord(applicant.getApplicantId(), applicant.getFullName(), applicant.getProfession());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
