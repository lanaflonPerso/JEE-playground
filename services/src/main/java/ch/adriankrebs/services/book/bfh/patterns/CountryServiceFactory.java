package ch.adriankrebs.services.book.bfh.patterns;

/**
 * Created by Adrian on 10/23/2017.
 */

public class CountryServiceFactory {

    private static final String LOCAL = "local";
    private static final String REMOTE = "remote";

        public ICountryService createCountryService(String type) {
            String normalizedType = type.toLowerCase();
            ICountryService service = null;
            if (REMOTE.equals(normalizedType)) {
                System.out.println("producing remote service");
                service = new CountryServiceProxy();
            } else {
                if (LOCAL.equals(normalizedType)){
                    System.out.println("producing local service");
                    service = new CountryService();
                }
            }

            return service;

        }

    public static void main(String[] args) {
        String config = args[0];
        CountryServiceFactory factory = new CountryServiceFactory();
        ICountryService countryService = factory.createCountryService(config);
        System.out.println(countryService.getCountryName("CH"));


    }

}
