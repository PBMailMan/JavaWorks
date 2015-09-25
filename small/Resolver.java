package small;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Resolver {
	
	public Resolver() {
		
	}
	
	/**
	 * Check if one URLs is the likely host of the other
	 * @param storyURL
	 * @param companyDomain
	 * @return true if one 
	 * 
	 * TODO change input to an array or list or something to take an arbitrary number of strings to compare.
	 */
	public boolean resolveBase(String story, String company) {
		if (!story.equals(company)) {
			try {
				URL sURL = new URL(story);
				URL cURL = new URL(company);
				String[] sSplit = splitHost(sURL);
				String[] cSplit = splitHost(cURL);
				int dif = sSplit.length - cSplit.length;
				String[] sTrim = null;
				String[] cTrim = null;
				if (dif > 1 || dif < -1) {
					return false;
				} else if (dif > 0) {
					sTrim = Arrays.copyOfRange(sSplit, 1, sSplit.length - 1);
					cTrim = cSplit;
				} else if (dif < 0) {
					cTrim = Arrays.copyOfRange(cSplit, 1, cSplit.length - 1);
					sTrim = sSplit;
				} else {
					sTrim = Arrays.copyOfRange(sSplit, 0, sSplit.length - 2);
					cTrim = Arrays.copyOfRange(cSplit, 0, cSplit.length - 2);
				} 
				for (int i = 0; i < sTrim.length - 2; i++) {
					String sPart = sTrim[i];
					String cPart = cTrim[i];
					System.out.println(sPart.toString() + ";" + cPart.toString());
					if (!sPart.equals(cPart)) {
						return false;
					}
				}
				return true;
			} catch (MalformedURLException e) {
				System.out.println("invalid URL. Returning false.");
				return false;
			}
		}
		return true;
	}
	
	public String[] splitHost(URL in) {
		return in.getHost().split("\\.");
	}

}
