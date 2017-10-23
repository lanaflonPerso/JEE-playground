package ch.adriankrebs.services.book.bfh.patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Adrian on 10/23/2017.
 */
public class CountryServiceProxy implements ICountryService{

    private static final Logger LOGGER = Logger.getLogger(CountryServiceProxy.class
            .getName());

    private final String dsServerUrl = "http://eadj-simas.rhcloud.com/country/";

    /*
     * (non-Javadoc)
     *
     * @see ds.service.CountryService#getCountryName(java.lang.String)
     */
    @Override
    public String getCountryName(String countryCode) {
        try {
            URL url = new URL(dsServerUrl + countryCode);
            return this.convertStreamToString((InputStream) url.getContent());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem while communicating with server occured", e);
            return null;
        }
    }

    /**
     * Converts an input stream to a single string
     *
     * @param is stream
     * @return string representation of the input stream
     * @throws IOException
     */
    private String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CountryServiceProxy countryServiceProxy = new CountryServiceProxy();

        System.out.println(countryServiceProxy.getCountryName("CH"));
    }

}
