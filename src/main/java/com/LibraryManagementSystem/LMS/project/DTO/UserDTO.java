package com.LibraryManagementSystem.LMS.project.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String address;
    private String contact_no;
    private int role_id;


    public UserDTO(int id, String name, String email, String address, String contact_no, int role_id) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.address=address;
        this.contact_no=contact_no;
        this.role_id=role_id;
    }
}
