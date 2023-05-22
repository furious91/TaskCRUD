package sigua.giorgi.task.crud.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sigua.giorgi.task.crud.model.request.Applicant;
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
    public OperationResponse createApplicant(@RequestBody Applicant applicant) {
        return crudService.createApplicant(applicant);
    }

    @GetMapping(value = "/applicant-by-id/{id}", produces = "application/json")
    @ResponseBody
    public Applicant getApplicantById(@PathVariable long id) {
        return crudService.getApplicantById(id);
    }

    @GetMapping(value = "/applicants", produces = "application/json")
    @ResponseBody
    public List<Applicant> getAll() {
        return crudService.getAll();
    }

    @GetMapping(value = "/applicants-csv")
    public void generateCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"applicants.csv\"");
        csvGenerator.writeStudentsToCsv(crudService.getAll(), response.getWriter());
    }

    @PostMapping(value = "/applicant-update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public OperationResponse updateApplicantProfession(@RequestBody Applicant applicant) {
        return crudService.updateAplicantName(applicant);
    }

    @DeleteMapping(value = "/applicants/{id}")
    @ResponseBody
    public OperationResponse deleteApplicant(@PathVariable long id) {
        return crudService.deleteApplicantyById(id);
    }




}
