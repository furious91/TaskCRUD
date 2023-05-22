package sigua.giorgi.task.crud.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sigua.giorgi.task.crud.model.request.Applicant;
import sigua.giorgi.task.crud.model.mapper.ApplicantRowMapper;

import java.util.List;

@Repository
public class CrudRepo {

    final JdbcTemplate jdbcTemplate;

    public CrudRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveApplicant(Applicant applicant) {
        final String query = "INSERT INTO applicant_table(applicant_id, applicant_full_name, applicant_profession) VALUES(?, ?, ?)";
        jdbcTemplate.update(query, applicant.getApplicantId(), applicant.getFullName(), applicant.getProfession());
    }

    public List<Applicant> getAll() {
        final String query = "SELECT * FROM applicant_table";
        return jdbcTemplate.query(query, new ApplicantRowMapper());
    }

    public Applicant getApplicantById(long id) {
        final String query = "SELECT * FROM applicant_table WHERE applicant_id=?";
        List<Applicant> applicants = jdbcTemplate.query(query, new ApplicantRowMapper(), id);
        if (applicants.size() > 0) {
            return applicants.get(0);
        } else {
            return null;
        }
    }

    public void updateAplicantProfession(long id, String profession) {
        final String query = "UPDATE applicant_table SET applicant_profession=? WHERE applicant_id=?";
        jdbcTemplate.update(query, profession, id);
    }

    public void deleteApplicantyById(long id) {
        final String query = "DELETE FROM applicant_table WHERE applicant_id=?";
        jdbcTemplate.update(query, id);
    }

}
