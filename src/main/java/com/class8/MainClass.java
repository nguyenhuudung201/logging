package com.class8;

import com.class8.model.Person;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        PersonManagement management = new PersonManagement();

        int choose = 0;
        Scanner input = new Scanner(System.in);

        try {
            do {
                System.out.println("1. Add new Person");
                System.out.println("2. View all Person");
                System.out.println("3. Get Person by ID");
                System.out.println("4. Update Person by ID");
                System.out.println("5. Delete Person by ID");
                System.out.println("6. Exit");
                System.out.println("Chosen Menu");
                choose = input.nextInt();

                switch (choose) {
                    case 1: {
                        Person person = new Person();
                        person.inputData();
                        management.addNewPerson(person);
                    }
                    break;
                    case 2: {
                        management.getAllPersons();
                    }
                    break;
                    case 3: {
                        System.out.print("Enter id: ");
                        int id = input.nextInt();

                        Person person = management.getPersonByIdStored(id);
                        if(person != null) {
                            System.out.println(person.toString());
                        } else {
                            System.out.println("Not found");
                        }
                    }
                    break;
                    case 4: {
                        System.out.print("Enter Person id to update: ");
                        int id = input.nextInt();

                        management.updatePerson( id );
                    }
                    break;
                    case 5:{
                        System.out.print("Enter Person id to delete: ");
                        int id = input.nextInt();

                        management.deletePerson(id);
                    }
                    break;
                    case 6: {
                        return;
                    }
                }
            } while(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
