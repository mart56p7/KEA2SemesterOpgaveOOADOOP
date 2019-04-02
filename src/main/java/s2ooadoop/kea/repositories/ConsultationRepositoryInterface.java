package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public interface ConsultationRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet GetConsultation(String ID);

	/**
	 * 
	 * @param PatientID
	 * @param Description
	 * @param Conclusion
	 * @param Date
	 */
	int CreateConsultation(int PatientID, String Description, String Conclusion, Date Date);

	/**
	 * 
	 * @param PatientID
	 * @param Description
	 * @param Conclusion
	 * @param Date
	 */
	void EditConsultation(int PatientID, String Description, String Conclusion, Date Date);

	/**
	 * 
	 * @param ID
	 */
	void DeleteConsultation(int ID);

}