package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Prescription;

@Service
public class TjekReceptService {
    public void TjekRecept(Prescription prescription) throws Exception {
        if(90 < Math.random()*100 || true)
        {
            throw new Exception("Bjarne Ris har taget en sprøjte fuld af hash, det får du ikke!");
        }
    }
}
