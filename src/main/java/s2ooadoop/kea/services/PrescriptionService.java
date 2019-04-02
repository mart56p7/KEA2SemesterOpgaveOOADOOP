package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.PatientInterface;
import s2ooadoop.kea.models.Prescription;
import s2ooadoop.kea.repositories.PrescriptionRepositoryInterface;

@Service
public class PrescriptionService {

	private PrescriptionRepositoryInterface RRI;

	/**
	 * 
	 * @param ID
	 */
	public Prescription GetPrescription(int ID) {
		// TODO - implement PrescriptionService.GetPrescription
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Patient
	 * @param ActiveOnly
	 */
	public Prescription[] GetAllPrescriptions(PatientInterface Patient, boolean ActiveOnly) {
		// TODO - implement PrescriptionService.GetAllPrescriptions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Prescription
	 */
	public int CreatePrescription(Prescription Prescription) {
		// TODO - implement PrescriptionService.CreatePrescription
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Prescription
	 */
	public void EditPrescription(Prescription Prescription) {
		// TODO - implement PrescriptionService.EditPrescription
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void DeletePrescription(int ID) {
		// TODO - implement PrescriptionService.DeletePrescription
		throw new UnsupportedOperationException();
	}

}