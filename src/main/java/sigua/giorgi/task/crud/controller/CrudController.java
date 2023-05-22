package sigua.giorgi.task.crud.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sigua.giorgi.task.crud.model.dto.ApplicantBaseDTO;
import sigua.giorgi.task.crud.model.dto.ApplicantDTO;
import sigua.giorgi.task.crud.model.response.OperationResponse;
import sigua.giorgi.task.crud.service.CrudService;
import sigua.giorgi.task.crud.service.CsvGenerator;

import java.io.IOException;
import java.util.List;

@Controller
public class CrudController {

   final CrudService crudService;
   final CsvGenerator csvGenerator;

    public CrudController(CrudService crudService, CsvGenerator csvGenerator) {
        this.crudService = crudService;
        this.csvGenerator = csvGenerator;
    }

    @PostMapping(value = "/applicant", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<OperationResponse> createApplicant( @Valid @RequestBody ApplicantBaseDTO applicantBaseDTO) {
        return ResponseEntity.ok(crudService.createApplicant(applicantBaseDTO));
    }

    @GetMapping(value = "/applicant-by-id/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable long id) {
        return ResponseEntity.ok(crudService.getApplicantById(id));
    }

    @GetMapping(value = "/applicants", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<ApplicantDTO>> getAll() {
        return ResponseEntity.ok(crudService.getAll());
    }

    @GetMapping(value = "/applicants-csv")
    public void generateCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"applicants.csv\"");
        csvGenerator.writeStudentsToCsv(crudService.getAll(), response.getWriter());
    }

    @PostMapping(value = "/applicant-update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<OperationResponse> updateApplicantProfession(@Valid @RequestBody ApplicantDTO applicantDTO) {
        return ResponseEntity.ok(crudService.updateAplicantName(applicantDTO));
    }

    @DeleteMapping(value = "/applicants/{id}")
    @ResponseBody
    public ResponseEntity<OperationResponse> deleteApplicant(@PathVariable long id) {
        return ResponseEntity.ok(crudService.deleteApplicantyById(id));
    }




}
