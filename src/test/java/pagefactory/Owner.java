package pagefactory;

public class Owner {
    private final String name;
    private final String address;

    public Owner(String name, String address) {

        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
