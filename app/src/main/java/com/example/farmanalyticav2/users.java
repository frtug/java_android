package com.example.farmanalyticav2;

public class users {

    public String email;
    public String fullName;
    public String mobileNumber;
    public String state_name;
    public String district_name;
    public String areaFarm;
    public String soilType;
    public String password;
    public String confPassword;

    public users(String email, String fullName, String mobileNumber, String state_name, String district_name, String password, String confPassword, String areaFarm, String soilType) {
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.state_name = state_name;
        this.district_name = district_name;
        this.password = password;
        this.areaFarm = areaFarm;
        this.soilType = soilType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public String getStateName() {
        return state_name;
    }

    public void setStateName(String state_name) {
        this.state_name = state_name;
    }


    public String getDistrictName() {
        return district_name;
    }

    public void setDistrictName(String district_name) {
        this.district_name = district_name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }


    public String getAreaFarm() { return areaFarm; }

    public void setAreaFarm(String areaFarm) { this.areaFarm = areaFarm; }


    public String getSoilType() { return soilType; }

    public void setSoilType(String soilType) { this.soilType = soilType; }
}
