package sigua.giorgi.task.crud.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicantDTO extends ApplicantBaseDTO {

    private long applicantId;

    public ApplicantDTO(@NotBlank String fullName, @NotBlank String profession, long applicantId) {
        super(fullName, profession);
        this.applicantId = applicantId;
    }
}
