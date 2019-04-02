package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.PatientDiagnose;
import s2ooadoop.kea.repositories.PatientDiagnoseRepositoryInterface;

@Service
public class PatientDiagnoseService {

	private PatientDiagnoseRepositoryInterface PDRI;

	/**
	 * 
	 * @param ID
	 */
	public PatientDiagnose GetDiagnose(int ID) {
		// TODO - implement PatientDiagnoseService.GetDiagnose
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PatientDiagnose
	 */
	public int CreatePatientDiagnose(PatientDiagnose PatientDiagnose) {
		// TODO - implement PatientDiagnoseService.CreatePatientDiagnose
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PatientDiagnose
	 */
	public void EditPatientDiagnose(PatientDiagnose PatientDiagnose) {
		// TODO - implement PatientDiagnoseService.EditPatientDiagnose
		throw new UnsupportedOperationException();
	}

	public PatientDiagnose DeletePatientDiagnose() {
		// TODO - implement PatientDiagnoseService.DeletePatientDiagnose
		throw new UnsupportedOperationException();
	}

}