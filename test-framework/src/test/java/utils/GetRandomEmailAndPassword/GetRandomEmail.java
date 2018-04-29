package utils.GetRandomEmailAndPassword;

import java.util.UUID;

public class GetRandomEmail {
    public static String email = UUID.randomUUID().toString().replace("-", "") +"@test.pl";
}
