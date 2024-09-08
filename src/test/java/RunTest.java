import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RunTest {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("beforeAll_hotfix");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("afterAll_hotfix");
    }

    @Test
    public void test1(){
        System.out.println("1_hotfix");
        Assertions.assertEquals("Exynos", "Exynos");
    }

    @Test
    public void test2(){
        System.out.println("2_hotfix");
        Assertions.assertEquals("Snapdragon", "Snapdragon");
    }
}
