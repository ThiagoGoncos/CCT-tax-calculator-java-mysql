/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvidumas
 */
class Admin extends User {
    private String adminTitle;

    public Admin(String username, String password, String name, String surname) {
        super(username, password, name, surname, UserType.ADMIN);
    }

    public String getAdminTitle() {
        return adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }

    @Override
    public void modifyProfile(String name, String surname) {
        super.setName(name);
        super.setSurname(surname);
        System.out.println("Admin profile modified: " + this);
    }

    public void grantAdminPrivileges(RegularUser user) {
        System.out.println("Admin privileges granted to user: " + user.getUsername());
    }
}
