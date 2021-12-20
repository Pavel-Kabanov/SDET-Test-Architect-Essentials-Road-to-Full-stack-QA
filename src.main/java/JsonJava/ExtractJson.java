package JsonJava;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ExtractJson {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDetailsAppium customerDetailsAppium = objectMapper.readValue(new File("src.main/java/JsonJava/JsonResult/CustomerInfoToJavaObject.json"), CustomerDetailsAppium.class);
        System.out.println(customerDetailsAppium.getStudentName());
    }
}
