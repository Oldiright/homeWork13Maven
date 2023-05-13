package task1;

public class Address {
    private final String street;
    private  final String suite;
    private  final String city;
    private final String zipcode;
    private final Address.Geo geo;



    public Address(String street, String suite, String city, String zipcode, Address.Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }


    public static class Geo {

        private  final float lat;



        private final float lng;



        public Geo(float lat, float lng) {
            this.lat = lat;
            this.lng = lng;
        }


        public float getLat() {
            return lat;
        }


        @Override
        public String toString() {
            return "Geo{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }

}
