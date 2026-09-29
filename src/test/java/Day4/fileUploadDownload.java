package Day4;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
public class fileUploadDownload {
	//@Test
	void fleUpload()
	{
		  File myfile=new File("C:\\Users\\ShubhiSinghRana\\Test1.txt");
		given()
		  .multiPart("file",myfile)
		  .contentType("multipart/form-data")
		.when()
		  .post("http://localhost:8080/uploadFile")
		.then()
		   .statusCode(200)
		   .log().body();
	}
	@Test
	void multiFileUpload()
	{
		File myfile1=new File("C:\\Users\\ShubhiSinghRana\\Test2.txt");
		File myfile2=new File("C:\\Users\\ShubhiSinghRana\\Test3.txt");
		given()
		    .multiPart("files",myfile1)
		    .multiPart("files",myfile2)
		    .contentType("multipart/form-data")
		.when()
		    .post("http://localhost:8080/uploadMultipleFiles")
		.then()
		     .statusCode(200)
		     .log().body();
		
	}
	@Test
	void downloadFile()
	{
		given()
	    .when()
		   .get("http://localhost:8080/downloadFile/Test1.txt")
		.then()
		  .statusCode(200)
		  .log().body();
	}
		

}
