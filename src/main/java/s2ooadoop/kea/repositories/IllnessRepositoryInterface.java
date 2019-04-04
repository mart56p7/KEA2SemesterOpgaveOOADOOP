package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public interface IllnessRepositoryInterface {

	ResultSet GetIllness(int ID) throws SQLException;

	int CreateIllness(String Name, int[] TreatmentID) throws SQLException;

	void EditIllness(String Name, int[] TreatmentID, int ID) throws SQLException;

	void DeleteIllness(int ID) throws SQLException;

}