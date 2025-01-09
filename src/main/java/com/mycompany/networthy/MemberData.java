
package com.mycompany.networthy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MemberData {
    private final IntegerProperty ConnId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty linkedin;
    private final IntegerProperty phone;
    public MemberData(Integer ConnId, String firstName, String lastName, String email, String linkedin, Integer phone) {
        this.ConnId = new SimpleIntegerProperty(ConnId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.linkedin = new SimpleStringProperty(linkedin);
        this.phone = new SimpleIntegerProperty(phone);
    }
     public Integer getId(){
    return ConnId.get();
    }
    public String getFirstName(){
    return firstName.get();
    }
    public String getLastName(){
    return lastName.get();
    }
    public String getEmail(){
    return email.get();
    }
    public String getLinkedin(){
    return linkedin.get();
    }
     public Integer getPhone(){
    return phone.get();
    }
}
