package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public class ConsultationRepository implements ConsultationRepositoryInterface {

	private Database Database;

	@Override
	public ResultSet GetConsultation(String ID) {
		return null;
	}

	@Override
	public int CreateConsultation(int PatientID, String Description, String Conclusion, Date Date) {
		return 0;
	}

	@Override
	public void EditConsultation(int PatientID, String Description, String Conclusion, Date Date) {

	}

	@Override
	public void DeleteConsultation(int ID) {

	}
}