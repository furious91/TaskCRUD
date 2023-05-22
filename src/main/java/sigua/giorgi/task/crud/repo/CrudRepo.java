package sigua.giorgi.task.crud.repo;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sigua.giorgi.task.crud.model.domain.Applicant;
import sigua.giorgi.task.crud.model.mapper.ApplicantRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class CrudRepo {

    final JdbcTemplate jdbcTemplate;

    public CrudRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveApplicant(Applicant applicant) {
        final String query = "INSERT INTO applicant_table(applicant_id, applicant_full_name, applicant_profession) VALUES(nextval('public.applicant_seq'), ?, ?)";
        jdbcTemplate.update(query, applicant.getFullName(), applicant.getProfession());
    }

    public List<Applicant> getAll() {
        final String query = "SELECT * FROM applicant_table";
        return jdbcTemplate.query(query, new ApplicantRowMapper());
    }

    public Optional<Applicant> getApplicantById(long id) {
        final String query = "SELECT * FROM applicant_table WHERE applicant_id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new ApplicantRowMapper(), id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
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
