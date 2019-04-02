package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Consultation;
import s2ooadoop.kea.repositories.ConsultationRepositoryInterface;

@Service
public class ConsultationService {

	private ConsultationRepositoryInterface KRI;

	/**
	 * 
	 * @param ID
	 */
	public Consultation GetConsultation(int ID) {
		// TODO - implement ConsultationService.GetConsultation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Consultation
	 */
	public int CreateConsultation(Consultation Consultation) {
		// TODO - implement ConsultationService.CreateConsultation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Consultation
	 */
	public void EditConsultation(Consultation Consultation) {
		// TODO - implement ConsultationService.EditConsultation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void DeleteConsultation(int ID) {
		// TODO - implement ConsultationService.DeleteConsultation
		throw new UnsupportedOperationException();
	}

}