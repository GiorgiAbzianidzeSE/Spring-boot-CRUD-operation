package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOperationApplication.class, args);
	}
	/*JSON OF this USER Object
	 * {
   "firstName" : "Vaxo",
    "lastName" : "vaxo",
   "age" :23,
   "birthDay" : "1950-03-25",
  "address" : {
    "streetName" : "ilori",

    "streetNumber" : 11,

    "zipCode" : 111,

    "isApartment" : true
  }
  
}
	 */

}
