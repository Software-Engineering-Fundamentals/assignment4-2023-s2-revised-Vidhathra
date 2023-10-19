package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Department in an organisation
 */
public class Department {

    private String name;

    private ArrayList<String> ID1 = new ArrayList<String>();

    private ArrayList<String> temp1 = new ArrayList<String>();
    // fixed variable data type
    private ArrayList<Integer> employee = new ArrayList<Integer>();

    private int ID2;

    public String getID() {
        return ID2;
    }

    public void setID(int ID) {
        this.ID2 = ID;
    }

    public void assignNew(string temp2, int ID) {
        this.temp1.add(temp2);
        this.ID1.add(String.valueOf(ID));
    }

    public boolean checkEmployee(int eID) {
        boolean employeeExists = employee.contains(eID);

        if (employeeExists) {
            System.out.println("Employee exists");
            return true;
        } else {
            System.out.println("Employee doesn't exists");
            return false;
        }
    }

}
