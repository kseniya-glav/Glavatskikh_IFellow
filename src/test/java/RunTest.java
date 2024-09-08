import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RunTest {
    @BeforeAll
    public static void beforeAll(){
        System.out.println("beforeAll_develop");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("afterAll_develop");
    }

    @Test
    public void test1(){
        System.out.println("1_develop");
        Assertions.assertEquals("Samsung","Samsung");
    }

    @Test
    public void test2(){
        System.out.println("2_develop");
        Assertions.assertEquals("Iphone","Samsung");
    }
}
