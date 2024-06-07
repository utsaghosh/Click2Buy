package com.ecart.ecart.dto;

import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Integer userId;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String gender;
    private String dateOfBirth;
    private Long age;

    public UserModel (Users user) {

        this.setUserId(user.getUserId());
        this.setUserName(user.getUserName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setAddress(user.getAddress());
        this.setAge(user.getAge());
        this.setDateOfBirth(user.getDateOfBirth().toString());
        this.setGender(user.getGender().toString());

    }

    public static List<UserModel> parseModelListFromEntityList(List<Users> userList){


        List<UserModel> userModelList = new ArrayList<UserModel>();
        for(Users userEntity : userList) {
            userModelList.add(new UserModel(userEntity));
        }

        return userModelList;
    }

    public Users parseEntityFromModel() throws InvalidInputException {


        if( ! this.isModelValid()) {
            return null;
        }

        Users user = new Users();
        user.setDateOfBirth(LocalDate.parse(this.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        user.setEmail(this.getEmail());
        user.setGender(Gender.valueOf(this.getGender().toUpperCase()));
        user.setPassword(this.getPassword());
        user.setAddress(this.getAddress());
        user.setUserName(this.getUserName());
        return user;
    }

    public Boolean isModelValid() throws InvalidInputException{


        if( ! this.validateEmail( this.getEmail() ) ) {
            return false;
        }
        if( ! this.validateUserName( this.getUserName() ) ) {
            return false;
        }
        if( ! this.validatePassword( this.getPassword() ) ) {
            return false;
        }
        if( ! this.validateAddress( this.getAddress() ) ) {
            return false;
        }
        if( ! this.validateDateOfBirth( this.getDateOfBirth() )) {
            return false;
        }
        if( ! this.validateGender( this.getGender() )) {
            return false;
        }
        return true;
    }

    public Boolean validateEmail(String email) throws InvalidInputException {

        String emailregex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        if( email == null ) {
            throw new InvalidInputException("Email cannot be null");
        }
        if( email.isBlank() ) {
            throw new InvalidInputException("Email cannot be whitespace");
        }
        if( email.length() > 255 ) {
            throw new InvalidInputException("Email cannot exceed 255 characters");
        }
        if( ! email.matches(emailregex) ) {
            throw new InvalidInputException("Email is invalid");
        }

        return true;
    }

    public Boolean validateUserName(String userName) throws InvalidInputException {

        if( userName == null ) {
            throw new InvalidInputException("UserName cannot be null");
        }
        if( userName.isBlank() ) {
            throw new InvalidInputException("UserName cannot be whitespace");
        }
        if( userName.length() > 50 ) {
            throw new InvalidInputException("UserName cannot exceed 50 characters");
        }
        return true;
    }

    public Boolean validatePassword(String password) throws InvalidInputException {

        if( password == null ) {
            throw new InvalidInputException("Password cannot be null");
        }
        if( password.isBlank() ) {
            throw new InvalidInputException("Password cannot be whitespace");
        }
        if( password.length() > 255 ) {
            throw new InvalidInputException("Password cannot exceed 255 characters");
        }
        return true;
    }

    public Boolean validateAddress(String address) throws InvalidInputException {

        if( UserModel.this.address == null ) {
            throw new InvalidInputException("Address cannot be null");
        }
        if( UserModel.this.address.isBlank() ) {
            throw new InvalidInputException("Address cannot be whitespace");
        }
        if( UserModel.this.address.length() > 255 ) {
            throw new InvalidInputException("Address cannot exceed 255 characters");
        }
        return true;
    }

    public Boolean validateGender(String gender) throws InvalidInputException{

        try {
            Gender.valueOf(gender.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Gender can be MALE/FEMALE/OTHER only");
        }
    }

    public Boolean validateDateOfBirth(String dateOfBirth) throws InvalidInputException {

        try {
            new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth);
            return true;

        } catch (ParseException e) {
            throw new InvalidInputException("Enter valid date in dd-MM-yyyy format");
        }
    }
}
