/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Assignment.Assignment.input;
import static Assignment.Stationary.inputString;

/**
 *
 * @author User
 */
public class Member {

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    private String memberID;
    private String name;
    private String phoneNumber;
    private String email;
    private double discountRate;
    private double memberPoints;
    private char memberGrade;

    public Member() {
        this("", "", "", "", 'B', 0.0, 0.0);
    }

    public Member(String memberID, String name, String phoneNumber, String email, char memberGrade, double discountRate, double memberPoints) {
        this.memberID = memberID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.memberGrade = memberGrade;
        this.discountRate = discountRate;
        this.memberPoints = memberPoints;

    }

    //getter
    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public char getMemberGrade() {
        return memberGrade;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getMemberPoints() {
        return memberPoints;
    }

    //setter
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMemberGrade(char memberGrade) {
        this.memberGrade = memberGrade;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setMemberPoints(double memberPoints) {
        this.memberPoints = memberPoints;
    }

    public void rmtoPoints(double rm) {
        double point = rm / 100;
        this.memberPoints = point;
    }

    //other methods
    @Override
    public String toString() {
        return "Member ID: " + memberID + "\nName: " + name + "\nPhone Number: " + phoneNumber + "\nEmail: " + email + "\nMember grade: " + memberGrade + "\nDiscount Rate: " + discountRate + "\n-----------------------------------------------------------";
    }

    public void addMember(ArrayList<Member> memberArray, Member member) {
        memberArray.add(member);
    }

    public void deleteMember(ArrayList<Member> memberArray, Member member) {
        memberArray.remove(member);
    }

    //File
    public static void writeMember(ArrayList<Member> memberArray) throws IOException {
        try (FileWriter writeMemberFile = new FileWriter("Member.txt")) {
            for (Member member : memberArray) {
                writeMemberFile.write(member.getMemberID() + "|"
                        + member.getName() + "|"
                        + member.getPhoneNumber() + "|"
                        + member.getEmail() + "|"
                        + member.getMemberGrade() + "|"
                        + member.getDiscountRate() + "|"
                        + member.getMemberPoints() + "|"
                        + "\n"
                );
            }
        }

    }

    public static void readMemberFromFile(ArrayList<Member> memberArray) throws FileNotFoundException {
        File readMemberFile = new File("Member.txt");

        if (readMemberFile.exists()) {
            Scanner memberRead = new Scanner(readMemberFile);
            while (memberRead.hasNextLine()) {
                String line = memberRead.nextLine();
                String[] info = line.split("\\|");
                memberArray.add(new Member(info[0], info[1], info[2], info[3], info[4].charAt(0), Double.parseDouble(info[5]), Double.parseDouble(info[6])));
            }
        } else {
            File createMemberFile = new File("Member.txt");
            try {
                createMemberFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Assignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File created : " + createMemberFile.getName() + "\n");
        }
    }

    //main function
    public void memberMenu() {
        int choice;

        do {
            Assignment.clearScreen();
            System.out.println("========================================");
            System.out.println("=                MEMBER                =");
            System.out.println("========================================");
            System.out.println("=       1. Register Member             =");
            System.out.println("=       2. View Member                 =");
            System.out.println("=       3. Delete Member               =");
            System.out.println("=       0. Exit                        =");
            System.out.println("========================================");

            System.out.print("Enter your choice > ");
            choice = Validation.getIntegerInput();

            switch (choice) {
                case 1 ->
                    memberRegistration();

                case 2 ->
                    displayMember();

                case 3 -> {
                    DeleteMember();
                }

                case 0 -> {
                }

                default -> {
                    System.out.println("Invalid Input. Please enter again!");
                    Assignment.systemPause();
                }
            }
        } while (choice != 0);
    }

    public void memberRegistration() {

        Assignment.clearScreen();

        Member member = new Member();
        ArrayList<Member> memberArray = new ArrayList();

        int choice;
        char confirm;
        boolean error;

        try {
            readMemberFromFile(memberArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }

        do {
            System.out.println("=======================================");
            System.out.println("=              Add Member             =");
            System.out.println("=======================================");

            setMemberID(generateMemberID(memberArray));
            System.out.println("ID: " + getMemberID());

            do {
                System.out.print("Enter Name: ");
                setName(inputString.nextLine());

                error = checkNameFormat(getName());

                if (error) {
                    System.out.println(RED + "Invalid Name Format!" + RESET);
                }

            } while (error);

            do {
                System.out.print("Enter Phone Number: ");
                setPhoneNumber(inputString.nextLine());

                error = checkPhoneNumberFormat(getPhoneNumber());
                if (error) {

                    System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                    System.out.println(RED + "Example: 012-3456789" + RESET);
                }

            } while (error);

            do {
                System.out.print("Enter Email: ");
                setEmail(inputString.nextLine());

                error = checkEmailFormat(getEmail());
                if (error) {
                    System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                    System.out.println(RED + "Example: abc123@gmail.com" + RESET);
                }

            } while (error);

            do {
                error = false;
                System.out.print("Enter Member Grade (B, S, G) : ");
                setMemberGrade(Character.toUpperCase(inputString.next().charAt(0)));

                switch (getMemberGrade()) {
                    case 'B' ->
                        setDiscountRate(0.1);
                    case 'S' ->
                        setDiscountRate(0.13);
                    case 'G' ->
                        setDiscountRate(0.15);
                    default -> {
                        error = true;
                    }
                }

                if (error) {
                    System.out.println(RED + "Invalid member. Please enter again!" + RESET);
                    System.out.println(RED + "Example: B, S, G" + RESET);
                }

            } while (error);

            do {
                error = false;
                System.out.print("Confirm Member ? (Y/N): ");
                confirm = inputString.next().charAt(0);
                confirm = Character.toUpperCase(confirm);
                inputString.nextLine();

                if (confirm != 'Y' && confirm != 'N') {
                    error = true;
                }

            } while (error);

            if (confirm == 'Y') {
                member.addMember(memberArray, new Member(memberID, name, phoneNumber, email, memberGrade,
                         discountRate, memberPoints));

                try {
                    writeMember(memberArray);
                } catch (IOException ex) {
                    Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            do {
                error = false;
                System.out.print("Add more Member? (Y/N): ");
                confirm = inputString.next().charAt(0);
                confirm = Character.toUpperCase(confirm);
                inputString.nextLine();

                if (confirm != 'Y' && confirm != 'N') {
                    error = true;
                }

            } while (error);

        } while (confirm == 'Y');

        Assignment.systemPause();
    }

    public void displayMember() {
        ArrayList<Member> memberArray = new ArrayList<>();

        Assignment.clearScreen();
        System.out.println("========================");
        System.out.println("=    Member Record     =");
        System.out.println("========================");

        try {
            readMemberFromFile(memberArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Member member : memberArray) {
            System.out.println(member);
        }

        Assignment.systemPause();
    }

    public void viewMember() {
        Assignment.clearScreen();
        Scanner sc = new Scanner(System.in);    //create a scanner object
        System.out.println("Enter Member that you want to view: ");
        String MemberID = sc.nextLine();

        System.out.println("member that you search is: " + memberID); // output user input
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Contact Number: " + phoneNumber);
        System.out.println("Member Grade: " + memberGrade);
        System.out.println("Discount Rate: " + discountRate);
        System.out.println("Member's Points: " + memberPoints);

        Assignment.systemPause();
    }

    public void DeleteMember() {
        Assignment.clearScreen();

        ArrayList<Member> memberArray = new ArrayList();

        String deleteID;
        boolean exist = true;
        boolean error = false;
        char confirm;
        char confirmDelete;
        boolean check;
        int currentIndex = 0;

        try {
            readMemberFromFile(memberArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!");
        }

        if (memberArray.isEmpty()) {
            System.out.println("=========================");
            System.out.println("=  None Member Record!  =");
            System.out.println("=========================");
        } else {
            do {
                confirm = 'N';
                Assignment.clearScreen();
                System.out.println("=======================================");
                System.out.println("=            Delete Member            =");
                System.out.println("=======================================");

                do {
                    exist = false;
                    System.out.print("Enter Member ID to delete(Press X to exit): ");
                    deleteID = inputString.nextLine();

                    deleteID = deleteID.trim();

                    if ((deleteID.equalsIgnoreCase("X"))) {
                        return;
                    }

                    for (int i = 0; i < memberArray.size(); i++) {
                        if (deleteID.equals(memberArray.get(i).getMemberID())) {
                            exist = true;
                            currentIndex = i;
                            break;
                        }
                    }

                    if (!exist) {
                        System.out.println("Member not found!");
                    }

                } while (!exist);

                do {
                    error = false;
                    System.out.println("Confirm to Delete? (Y/N): ");
                    confirmDelete = inputString.next().charAt(0);
                    confirmDelete = Character.toUpperCase(confirmDelete);

                    check = checkYesNo(confirmDelete);
                    if (!check) {
                        error = true;
                        System.out.println(RED + "Invalid input! Please enter again!" + RESET);
                    }
                } while (error);

                if (confirmDelete == 'Y') {
                    deleteMember(memberArray, memberArray.get(currentIndex));
                    try {
                        writeMember(memberArray);
                    } catch (IOException ex) {
                        Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Successful Delete!");
                }

                do {
                    error = false;

                    System.out.print("Delete more member? (Y/N) > ");
                    confirm = inputString.next().charAt(0);
                    confirm = Character.toUpperCase(confirm);

                    check = checkYesNo(confirm);

                    if (!check) {
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }

                } while (error);

                inputString.nextLine();
            } while (confirm == 'Y');
        }

        Assignment.systemPause();
    }

    public String generateMemberID(ArrayList<Member> memberArray) {
        String generatedMemberID;

        if (memberArray.isEmpty()) {
            generatedMemberID = "M0001";
        } else {
            generatedMemberID = memberArray.get(memberArray.size() - 1).getMemberID();
            int bufferMemberIDNum = Integer.parseInt(generatedMemberID.replaceAll("\\D+", ""));
            generatedMemberID = String.format("M%04d", bufferMemberIDNum + 1);
        }

        return generatedMemberID;
    }

    public boolean checkNameFormat(String checkName) {
        boolean error = false;
        int countDigit = 0;

        if (checkName.length() < 3) {
            error = true;
        }

        for (int i = 0; i < checkName.length(); i++) {
            char check = checkName.charAt(i);
            if (Character.isDigit(check)) {
                countDigit++;
            }
        }

        if (countDigit > 0) {
            error = true;
        }

        return error;
    }

    public boolean checkPhoneNumberFormat(String checkPhoneNumber) {
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        int countDash = 0;
        char dash = '-';

        for (int i = 0; i < checkPhoneNumber.length(); i++) {
            char check = checkPhoneNumber.charAt(i);
            if (Character.isLetter(check)) {
                countLetter++;
            } else if (Character.isDigit(check)) {
                countDigit++;
            } else if (check == dash) {
                countDash++;
            }
        }

        if (checkPhoneNumber.length() < 11 || checkPhoneNumber.length() > 12 || countLetter > 0 || countDash > 1 || countDash < 0 || checkPhoneNumber.charAt(3) != dash) {
            error = true;
        }

        return error;
    }

    public boolean checkEmailFormat(String checkEmail) {
        boolean error = false;
        int countAlian = 0;
        int countDot = 0;
        char alian = '@';
        char dot = '.';

        if (Character.isDigit(checkEmail.charAt(0))) {
            error = true;
        }

        if (checkEmail.charAt(0) == alian || checkEmail.charAt(0) == dot) {
            error = true;
        }

        for (int i = 0; i < checkEmail.length(); i++) {
            char check = checkEmail.charAt(i);

            if (check == alian) {
                countAlian++;
            } else if (check == dot) {
                countDot++;
            }
        }

        if (countAlian == 0) {
            error = true;
        }

        if (countDot == 0) {
            error = true;
        }

        return error;
    }

    public boolean checkYesNo(char check) {
        boolean checkYN = true;

        if (check != 'Y' && check != 'N') {
            checkYN = false;
        }

        return checkYN;
    }

}
