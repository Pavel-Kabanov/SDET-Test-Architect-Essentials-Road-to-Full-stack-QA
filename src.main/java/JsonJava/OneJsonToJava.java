package JsonJava;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OneJsonToJava {
    public void customerDetailsJson(@NotNull ResultSet resultSet) throws SQLException, IOException {
        ArrayList<CustomerDetails> customerDetailsArrayList = new ArrayList<>();
        while (resultSet.next()) {
            customerDetailsArrayList.add(new CustomerDetails(
                    resultSet.getString(1), // courseName
                    resultSet.getString(2), // purchaseDate
                    resultSet.getInt(3), // amount
                    resultSet.getString(4) // location
            ));
        }
        JSONArray jsonArray = objectsToJson(customerDetailsArrayList);
        String jsonToString = jsonToString(jsonArray);
        writingToJson("src.main/java/JsonJava/JsonResult/CustomerInfo.json", jsonToString);
    }

    public void customerDetailsTestJson(@NotNull ResultSet resultSet) throws SQLException, IOException {
        ArrayList<CustomerDetails> customerDetailsArrayList = new ArrayList<>();
        while (resultSet.next()) {
            customerDetailsArrayList.add(new CustomerDetails(
                    resultSet.getString(1), // courseName
                    resultSet.getString(2), // purchaseDate
                    resultSet.getInt(3), // amount
                    resultSet.getString(4) // location
            ));
        }
        JSONArray jsonArray = objectsToJson(customerDetailsArrayList);
        String jsonToString = jsonToString(jsonArray);
        writingToJson("src.main/java/JsonJava/JsonResult/CustomerInfoTest.json", jsonToString);
    }

    public void clearPrevResults() throws IOException {
        FileUtils.cleanDirectory(new File("src.main/java/JsonJava/JsonResult"));
    }

    // General
    @org.jetbrains.annotations.NotNull
    private JSONArray objectsToJson(@NotNull ArrayList arrayList) {
        JSONArray jsonArray = new JSONArray();
        for (Object o : arrayList) {
            jsonArray.add(new Gson().toJson(o));
        }
        return jsonArray;
    }

    private @NotNull String jsonToString(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        String unescapedString = StringEscapeUtils.unescapeJava(jsonObject.toString());
        return fixQuotes(unescapedString);
    }

    private void writingToJson(String path, String writeMe) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(writeMe);
        }
    }

    // Utils
    private @NotNull String fixQuotes(@NotNull String string) {
        return string
                .replace("\"{", "{")
                .replace("}\"", "}");
    }

}
