package sigua.giorgi.task.crud.service;

import org.springframework.stereotype.Component;
import sigua.giorgi.task.crud.model.domain.Applicant;
import sigua.giorgi.task.crud.model.dto.ApplicantBaseDTO;
import sigua.giorgi.task.crud.model.dto.ApplicantDTO;

@Component
public class ModelMapper {

    public ApplicantDTO domainToDto(Applicant applicant) {
        ApplicantDTO dto = new ApplicantDTO();
        dto.setApplicantId(applicant.getApplicantId());
        dto.setFullName(applicant.getFullName());
        dto.setProfession(applicant.getProfession());
        return dto;
    }

    public Applicant dtoToDomain(ApplicantBaseDTO applicantBaseDTO) {
        Applicant applicant = new Applicant();
        applicant.setFullName(applicantBaseDTO.getFullName());
        applicant.setProfession(applicantBaseDTO.getProfession());
        return applicant;
    }

}
