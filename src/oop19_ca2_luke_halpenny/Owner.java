package oop19_ca2_luke_halpenny;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
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
    private ArrayList<Pet> pets;

    public Owner(long id, String name, String email, String telephone, String address) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setAddress(address);
        this.pets = new ArrayList<>();
    }

    public static Owner newOwner(String name, String email, String telephone, String address) {
        long id = incrementalId;
        ++incrementalId;
        LocalDateTime dateRegistered = LocalDateTime.now();
        return new Owner(id, name, email, telephone, address);
    }

    public static Owner fromString(String data) {
        Scanner input = new Scanner(data);

        long id = input.nextLong();
        String name = input.next();
        String email = input.next();
        String telephone = input.next();
        String address = input.next();
        return new Owner(id, name, email, telephone, address);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        String regex = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~\\-][A-Za-z0-9!#$%&'*+/=?^_`{|}~.\\-]{1,62}" +
                "[A-Za-z0-9!#$%&'*+/=?^_`{|}~\\-]" +
                "@" +
                "[A-Za-z0-9][A-Za-z0-9.\\-]{1,253}[A-Za-z0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Email not valid.");
        }
        for (int i = 0; i < email.length() - 1; i++) {
            if(email.charAt(i) == '.' && email.charAt(i+1) == '.') {
                throw new IllegalArgumentException("Email not valid.");
            }
        }
        this.email = email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        String regex = "^(\\+[0-9]{1,3})?([0-9]{7,14})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Telephone number not valid.");
        }
        if(telephone.charAt(0) == '+' && telephone.length() > 16 ||
                telephone.charAt(0) != '+' && telephone.length() > 15) {
            throw new IllegalArgumentException("Telephone number not valid.");
        }
        this.telephone = telephone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Pet> getPets() {
        return this.pets;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public Pet getPet(long id) {
        for (int i = 0; i < this.pets.size(); i++) {
            if(this.pets.get(i).getId() == id) {
                return this.pets.get(i);
            }
        }
        return null;
    }

    public boolean equals(Owner o) {
        return this.getId() == o.getId() && this.getName() == this.getName() && this.getEmail() == o.getEmail()
                && this.getTelephone() == o.getTelephone() && this.getAddress() == o.getAddress()
                && this.getPets().equals(o.getPets());
    }

    @Override
    public int hashCode() {
        return (int) (this.getId() * 3 + this.getName().hashCode() * 5 + this.getEmail().hashCode() * 7
                + this.getTelephone().hashCode() * 11 + this.getAddress().hashCode() * 13
                + this.getPets().hashCode() * 17);
    }

    @Override
    public String toString() {
        return String.format("Owner(id=%d,name=%s,email=%s,telephone=%s,address=%s,#pets=%d)", this.getId(),
                this.getName(), this.getEmail(), this.getTelephone(), this.getAddress(), this.getPets().size());
    }

}
