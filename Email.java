import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String department;
    private String password;
    private int mailboxCapacity = 1000;
    private String domainName;
    private String completeEmail;

    Scanner scanner = new Scanner(System.in);
    //Valid characters for the password. Also used in password generation
    String passwordSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~`!#$%^&*()_-+={[}]|\\:;\"'<,>.?/";

    //Constructor for the Email object(s)
    public Email() {
        firstName = setName("First name: ");
        lastName = setName("Last name: ");
        domainName = (setName("Domain name/company: ")).toLowerCase();
        department = setDepartment();
        password = generatePW();
        System.out.println("Your generated password is: " + password);
        completeEmail = createEmail(firstName, lastName, domainName, department);
    }

    //Setter for first, last, and domain name by passing the relevant question to the user
    private String setName (String question) {
        System.out.println(question);
        return scanner.next();
    }

    //Sets the department based on user entry of department code
    private String setDepartment() {
        int deptInput = -1;
        String departmentChoice = "";
        while (deptInput < 0 || deptInput > 3) {
            System.out.print("Which department?\n1 for Sales\n2 for Accounting\n3 for Development" +
                    "\n0 for none\nEnter Department Code: ");
            deptInput = (int) scanner.nextFloat();
        }
        switch (deptInput) {
            case 1:
                departmentChoice = "sales";
                break;
            case 2:
                departmentChoice = "accounting";
                break;
            case 3:
                departmentChoice = "development";
                break;
        }
        return departmentChoice;
    }

    //Password Validator w/out regex
    private String passwordValidation(int length, String pass) {
        boolean invalidPW = false;
        while (length < 8 || length > 20) {
            System.out.println("Password is of an invalid length, must be between 8 and 20 characters\n" +
                    "Please enter your new password: ");
            pass = scanner.next();
            length = pass.length();
        }
        //Checks to see if each character of the user entered password is valid
        //If an invalid character is encountered, prompts the user to enter a new password
        for (int i = 0; i < length; i++) {
            if (passwordSet.indexOf(pass.charAt(i)) >= 0) {
                continue;
            } else {
                System.out.println("Invalid character entered, valid characters include:\n" +
                        passwordSet + "\n");
                invalidPW = true;
                break;
            }
        }
        if (invalidPW) {
            changePassword();
            return "";
        }
        return pass;
    }

    //Changes the password
    public void changePassword() {
        System.out.println("Enter your new password\nMust be between 8 and 20 characters");
        password = scanner.next();
        password = passwordValidation(password.length(), password);
        if (password != "") {
            this.password = password;
            System.out.println("Password successfully changed to: " + password);
        }
    }

    //Generates a random password based on user input length of password
    private String generatePW() {
        int length = 0;
        while (length < 8 || length > 20) {
            System.out.print("Password Length (between 8 and 20 characters): ");
            length = (int) scanner.nextFloat();
        }
        char[] pass = new char[length];
        //Iterates through the character set for the password length of the password number of times
        for (int i = 0; i < length; i++) {
            int random = (int)(Math.random() * passwordSet.length());
            pass[i] = passwordSet.charAt(random);
        }
        return new String(pass);
    }

    //Sets mailbox capacity
    public void setMailCapacity(int capacity){
        mailboxCapacity = capacity;
    }

    //Combines to make the email
    private String createEmail (String first, String last, String domain, String dept){
        //Checks if a department exists and adds a . if one exists to the complete email
        if (dept != "")
            dept += ".";
        return (first + "." + last + "@" + dept + domain + ".com");
    }

    //Getter functions
    public String getCompleteEmail() {
        return completeEmail;
    }
    public int getMailboxCapacity() {
        return mailboxCapacity;
    }
}