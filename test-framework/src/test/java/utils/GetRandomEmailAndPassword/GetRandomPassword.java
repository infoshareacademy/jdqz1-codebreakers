package utils.GetRandomEmailAndPassword;


import java.util.UUID;

public class GetRandomPassword {
        public static String password = UUID.randomUUID().toString().replace("-", "");
}
