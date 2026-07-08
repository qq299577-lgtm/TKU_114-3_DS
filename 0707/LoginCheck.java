public class LoginCheck {
    public static void main(String[] args) {

        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";

        boolean loginSuccess = inputUsername.equals(username) && inputPassword.equals(password);

        System.out.println("Login Success: " + loginSuccess);
    }
}