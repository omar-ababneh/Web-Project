 public class Service {
    public static String ExtractTypeUser(String Email){
        String[] Words=Email.split("[@.]");
        return Words[1];
    }
}
