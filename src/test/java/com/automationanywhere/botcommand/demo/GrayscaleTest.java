package com.automationanywhere.botcommand.demo;

import com.automationanywhere.botcommand.data.Value;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GrayscaleTest {

    Grayscale command = new Grayscale();

    @DataProvider(name = "images")
    public Object[][] dataTobeTested(){
        return new Object[][]{
                {"c:\\feedback.png", "c:\\feedback_ot1.png", ""}
        };
    }

    @Test(dataProvider = "images")
    public void imageGrayscaleTests(String inputImagePath, String OutputImagePath, String result){
        Value<String> d = command.action(inputImagePath,OutputImagePath);
        String output = (String)d.get();
        assertEquals(output,result);
    }
}
