package com.app.dto;




public class ForgetPasswordDto {
    private  String uuid;
    private  String contact;

    public ForgetPasswordDto(String uuid, String contact) {
        this.uuid = uuid;
        this.contact = contact;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
