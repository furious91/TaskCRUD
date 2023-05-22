package sigua.giorgi.task.crud.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantBaseDTO {

    @NotBlank
    private String fullName;
    @NotBlank
    private String profession;

}
