package kodlamaio.hrms.core.adapters.mernis;

import java.rmi.RemoteException;
import java.util.Locale;

import kodlamaio.hrms.entities.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisServiceAdapter implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		
		KPSPublicSoapProxy client = new KPSPublicSoapProxy();
		
		boolean result = true;
		try {
			result = client.TCKimlikNoDogrula(
					Long.parseLong(candidate.getNationalIdentity()), candidate.getFirstName().toUpperCase(new Locale("tr")),
					candidate.getLastName().toUpperCase(new Locale("tr")), candidate.getDateOfBirth());
		}  catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
