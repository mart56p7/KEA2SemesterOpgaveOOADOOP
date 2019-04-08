package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public interface IllnessRepositoryInterface {

	ResultSet getIllness(int ID) throws SQLException;

	ResultSet getIllnesses() throws SQLException;

	ResultSet getIllnessTreatmentIds(int ID) throws SQLException;

	int CreateIllness(String Name, int[] treatment_IDs) throws SQLException;

	void EditIllness(int ID, String Name, int[] treatment_IDs) throws SQLException;

	void DeleteIllness(int ID) throws SQLException;

}