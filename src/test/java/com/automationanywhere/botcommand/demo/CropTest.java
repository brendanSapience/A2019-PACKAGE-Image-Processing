package com.automationanywhere.botcommand.demo;

import com.automationanywhere.botcommand.data.Value;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CropTest {

    Crop command = new Crop();

    @DataProvider(name = "cimages")
    public Object[][] dataTobeTested(){

        return new Object[][]{
                {5,5,20,20,"c:\\feedback.png","c:\\feedback_ct1.png", ""},
                {5,5,60,60,"c:\\feedback.png","c:\\feedback_ct2.png", ""}
        };
    }

    @Test(dataProvider = "cimages")
    public void imageCropTests(double x,double y,double width, double height,String inputImagePath, String OutputImagePath, String result){
        Value<String> d = command.action(x,y,width,height,inputImagePath,OutputImagePath);
        String output = (String)d.get();
        assertEquals(output,result);
    }
}
