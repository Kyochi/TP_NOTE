package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value={
SchtroumpfTest.class,
SchtroumpfEquanimeTest.class,
VillageTest.class,
})
public class TousLesTests {
}