package oop19_ca2_luke_halpenny;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Owner class.
 *
 * <p>Description here.
 *
 * @author Luke Halpenny (D00219060)
 * @version 1.0
 */
public class Owner {

    static long incrementalId = 1;

    private long id;
    private String name;
    private String email;
    private String telephone;
    private String address;

    public Owner(long id, String name, String email, String telephone, String address) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setAddress(address);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Email not valid.");
        }
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        String regex = "^[+]?[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Telephone number not valid.");
        }
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
