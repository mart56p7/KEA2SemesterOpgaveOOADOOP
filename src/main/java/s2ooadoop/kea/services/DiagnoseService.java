package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Diagnose;
import s2ooadoop.kea.repositories.DiagnoseRepositoryInterface;

@Service
public class DiagnoseService {

	private DiagnoseRepositoryInterface PDRI;

	/**
	 * 
	 * @param ID
	 */
	public Diagnose GetDiagnose(int ID) {
		// TODO - implement DiagnoseService.GetDiagnose
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Diagnose
	 */
	public int CreateDiagnose(Diagnose Diagnose) {
		// TODO - implement DiagnoseService.CreateDiagnose
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Diagnose
	 */
	public void EditDiagnose(Diagnose Diagnose) {
		// TODO - implement DiagnoseService.EditDiagnose
		throw new UnsupportedOperationException();
	}

	public Diagnose DeleteDiagnose() {
		// TODO - implement DiagnoseService.DeleteDiagnose
		throw new UnsupportedOperationException();
	}

}