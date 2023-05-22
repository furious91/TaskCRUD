package sigua.giorgi.task.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sigua.giorgi.task.crud.model.domain.Applicant;
import sigua.giorgi.task.crud.model.dto.ApplicantBaseDTO;
import sigua.giorgi.task.crud.model.dto.ApplicantDTO;
import sigua.giorgi.task.crud.model.response.OperationResponse;
import sigua.giorgi.task.crud.repo.CrudRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CrudService {

    final CrudRepo crudRepo;
    final ModelMapper modelMapper;

    public CrudService(CrudRepo crudRepo, ModelMapper modelMapper) {
        this.crudRepo = crudRepo;
        this.modelMapper = modelMapper;
    }

    public OperationResponse createApplicant(ApplicantBaseDTO applicanBasetDTO) {
        crudRepo.saveApplicant(modelMapper.dtoToDomain(applicanBasetDTO));
        return new OperationResponse(0, "Applicant: " + applicanBasetDTO.getFullName() + " registered!");
    }

    public List<ApplicantDTO> getAll() {
        return crudRepo.getAll().stream().map(dto -> new ApplicantDTO(dto.getProfession(), dto.getFullName(), dto.getApplicantId())).toList();
    }

    public ApplicantDTO getApplicantById(long id) {
        Optional<Applicant> applicantById = crudRepo.getApplicantById(id);
        return applicantById.map(modelMapper::domainToDto).orElse(null);
    }

    public OperationResponse updateAplicantName(ApplicantDTO applicantDTO) {
        Optional<Applicant> applicantById = crudRepo.getApplicantById(applicantDTO.getApplicantId());
        if (applicantById.isEmpty()) {
            return new OperationResponse(2, "Applicant with id: " + applicantDTO.getApplicantId() + " does not exists in DB.");
        }
        crudRepo.updateAplicantProfession(applicantDTO.getApplicantId(), applicantDTO.getProfession());
        return new OperationResponse(0, "Applicant with id: " + applicantDTO.getApplicantId() + " updated with new profession: " + applicantDTO.getProfession());
    }

    public OperationResponse deleteApplicantyById(long id) {
        Optional<Applicant> applicantById = crudRepo.getApplicantById(id);
        if (applicantById.isEmpty()) {
            return new OperationResponse(2, "Applicant with id: " + id + " does not exists in DB.");
        }
        crudRepo.deleteApplicantyById(id);
        return new OperationResponse(0, "Applicant with id: " + id + " deleted!");
    }

}
