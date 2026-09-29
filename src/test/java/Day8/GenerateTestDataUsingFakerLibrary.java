package Day8;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
public class GenerateTestDataUsingFakerLibrary {
	@Test
	void fakeDataGenerator()
	{
		Faker faker=new Faker();
		String fullName=faker.name().fullName();
		String firstName=faker.name().firstName();
		String lastName=faker.name().lastName();
		String Email=faker.internet().emailAddress();
		String password= faker.internet().password();
		String phone_no=faker.phoneNumber().cellPhone();
		
		System.out.println(fullName);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(Email);
		System.out.println(password);
		System.out.println(phone_no);
	}
	

}
