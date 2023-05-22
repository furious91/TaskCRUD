package sigua.giorgi.task.crud.model.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {

    private long applicantId;
    private String fullName;
    private String profession;

}
