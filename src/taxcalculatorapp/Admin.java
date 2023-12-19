/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
class Admin extends User {
    private String adminTitle;

    public Admin(String username, String password, String name, String surname) {
        super(username, password, name, surname);
    }

    public String getAdminTitle() {
        return adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }

    @Override
    public void modifyProfile(String name, String surname) {
        super.modifyProfile(name, surname);
    }

    public void grantAdminPrivileges(RegularUser user) {
        System.out.println("Admin privileges granted to user: " + user.getUsername());
    }
}
