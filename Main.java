public class Main {
    public static void main(String[] args) {

        var email1 = new Email();
        String completeEmail = email1.getCompleteEmail();
        System.out.println("Your email is: " + completeEmail);
        System.out.println("Your mailbox capacity is: " + email1.getMailboxCapacity());
        //Uncomment the line below to trigger password change
        //email1.changePassword();
    }
}