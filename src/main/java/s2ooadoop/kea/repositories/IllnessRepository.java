package s2ooadoop.kea.repositories;

import java.sql.ResultSet;

public class IllnessRepository implements IllnessRepositoryInterface {

	private Database Database;

	@Override
	public ResultSet GetIllness(int ID) {
		return null;
	}

	@Override
	public int CreateIllness(String Name, int[] TreatmentID) {
		return 0;
	}

	@Override
	public void EditIllness(String Name, int[] TreatmentID, int ID) {

	}

	@Override
	public void DeleteIllness(int ID) {

	}
}