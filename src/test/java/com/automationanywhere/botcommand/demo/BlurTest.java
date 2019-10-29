package com.automationanywhere.botcommand.demo;

import com.automationanywhere.botcommand.data.Value;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BlurTest {

    Blur command = new Blur();

    @DataProvider(name = "cimages")
    public Object[][] dataTobeTested(){

        return new Object[][]{
                {"c:\\feedback.png","c:\\feedback_bt1.png", ""}
        };
    }

    @Test(dataProvider = "cimages")
    public void imageBlurTests(String inputImagePath, String OutputImagePath, String result){
        Value<String> d = command.action(inputImagePath,OutputImagePath);
        String output = (String)d.get();
        //System.out.println("Debug:"+output);
        assertEquals(output,result);
    }
}
