package mainpackage.dto;

import java.util.List;

public class ClientAddressDTO {
    private int clientAddressId;

    private String country;

    private String city;

    private String zipCode;

    private String street;

    private int building;

    private int apartment;

    private List<OrderDTO> orders;

    public int getClientAddressId() {
        return clientAddressId;
    }

    public void setClientAddressId(int clientAddressId) {
        this.clientAddressId = clientAddressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ClientAddressDTO{" +
            "clientAddressId=" + clientAddressId +
            ", country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", street='" + street + '\'' +
            ", building=" + building +
            ", apartment=" + apartment +
            ", orders=" + orders +
            '}';
    }
}
