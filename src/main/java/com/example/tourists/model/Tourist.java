package com.example.tourists.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tourists")
public class Tourist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String firstName;
    @Column(nullable = false) private String lastName;
    @Column(nullable = false, unique = true) private String email;
    private String phone;
    private String country;
    private String city;

    protected Tourist() {}
    public Tourist(String firstName, String lastName, String email, String phone, String country, String city) {
        this.firstName = firstName; this.lastName = lastName; this.email = email;
        this.phone = phone; this.country = country; this.city = city;
    }
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String v) { firstName = v; }
    public String getLastName() { return lastName; }
    public void setLastName(String v) { lastName = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { email = v; }
    public String getPhone() { return phone; }
    public void setPhone(String v) { phone = v; }
    public String getCountry() { return country; }
    public void setCountry(String v) { country = v; }
    public String getCity() { return city; }
    public void setCity(String v) { city = v; }
}
