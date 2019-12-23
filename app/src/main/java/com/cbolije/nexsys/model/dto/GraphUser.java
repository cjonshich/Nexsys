package com.cbolije.nexsys.model.dto;

import com.microsoft.graph.extensions.User;

public class GraphUser {

    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String graphId;

    public GraphUser() {
    }

    public GraphUser(User graphUser) {
        this.firstName = graphUser.givenName;
        this.lastName = graphUser.surname;
        this.fullName = graphUser.displayName;
        this.email = graphUser.mail;
        this.graphId = graphUser.id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String nafullNameme) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
