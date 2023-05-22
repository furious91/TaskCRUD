package sigua.giorgi.task.crud.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import sigua.giorgi.task.crud.model.request.Applicant;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantRowMapper implements RowMapper<Applicant> {

    @Override
    public Applicant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setApplicantId(rs.getLong("applicant_id"));
        applicant.setFullName(rs.getString("applicant_full_name"));
        applicant.setProfession(rs.getString("applicant_profession"));
        return applicant;
    }
}
