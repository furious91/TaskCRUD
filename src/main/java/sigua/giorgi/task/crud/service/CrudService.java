package sigua.giorgi.task.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sigua.giorgi.task.crud.model.request.Applicant;
import sigua.giorgi.task.crud.model.response.OperationResponse;
import sigua.giorgi.task.crud.repo.CrudRepo;

import java.util.List;

@Service
@Transactional
public class CrudService {

    final CrudRepo crudRepo;

    public CrudService(CrudRepo crudRepo) {
        this.crudRepo = crudRepo;
    }

    public OperationResponse createApplicant(Applicant applicant) {
        Applicant applicantById = crudRepo.getApplicantById(applicant.getApplicantId());
        if (applicantById != null) {
            return new OperationResponse(1, "Applicant with id: " + applicant.getApplicantId() + " already exists in DB.");
        }
        crudRepo.saveApplicant(applicant);
        return new OperationResponse(0, "Applicant: " + applicant.getFullName() + " registered!");
    }

    public List<Applicant> getAll() {
        return crudRepo.getAll();
    }

    public Applicant getApplicantById(long id) {
        return crudRepo.getApplicantById(id);
    }

    public OperationResponse updateAplicantName(Applicant applicant) {
        Applicant applicantById = crudRepo.getApplicantById(applicant.getApplicantId());
        if (applicantById == null) {
            return new OperationResponse(2, "Applicant with id: " + applicant.getApplicantId() + " does not exists in DB.");
        }
        crudRepo.updateAplicantProfession(applicantById.getApplicantId(), applicant.getProfession());
        return new OperationResponse(0, "Applicant with id: " + applicant.getApplicantId() + " updated with new profession: " + applicant.getProfession());
    }

    public OperationResponse deleteApplicantyById(long id) {
        Applicant applicantById = crudRepo.getApplicantById(id);
        if (applicantById == null) {
            return new OperationResponse(2, "Applicant with id: " + id + " does not exists in DB.");
        }
        crudRepo.deleteApplicantyById(id);
        return new OperationResponse(0, "Applicant with id: " + id + " deleted!");
    }

}
