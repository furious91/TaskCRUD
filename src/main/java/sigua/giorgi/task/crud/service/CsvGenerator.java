package sigua.giorgi.task.crud.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import sigua.giorgi.task.crud.model.dto.ApplicantBaseDTO;
import sigua.giorgi.task.crud.model.dto.ApplicantDTO;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvGenerator {

    public void writeStudentsToCsv(List<ApplicantDTO> applicantBaseDTOS, Writer writer) {
        try {

            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord("Applicant ID", "Full Name", "Profession");
            for (ApplicantDTO applicantDTO : applicantBaseDTOS) {
                printer.printRecord(applicantDTO.getApplicantId(), applicantDTO.getFullName(), applicantDTO.getProfession());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
