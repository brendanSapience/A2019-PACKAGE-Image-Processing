/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 *
 */
package com.automationanywhere.botcommand.demo;

import Catalano.Imaging.FastBitmap;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

/**
 * @author Bren Sapience
 *
 */
@BotCommand
@CommandPkg(label="Crop", name="crop", description="Crop an existing image", icon="pkg.svg",
		node_label="Crop {{imagePath}}",
		return_type=STRING, return_label="Assign the output to variable", return_required=false)
public class Crop {

	private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");

	@Execute
	public Value<String> action(
			@Idx(index = "1", type = AttributeType.NUMBER) @Pkg(label = "X Coordinate", default_value_type = DataType.NUMBER) @NotEmpty Double x,
			@Idx(index = "2", type = AttributeType.NUMBER) @Pkg(label = "Y Coordinate", default_value_type = DataType.NUMBER) @NotEmpty Double y,
			@Idx(index = "3", type = AttributeType.NUMBER) @Pkg(label = "Width", default_value_type = DataType.NUMBER) @NotEmpty Double width,
			@Idx(index = "4", type = AttributeType.NUMBER) @Pkg(label = "Height", default_value_type = DataType.NUMBER) @NotEmpty Double height,
			@Idx(index = "5", type = TEXT) @Pkg(label = "Input Image Path") @NotEmpty String imageInputPath,
			@Idx(index = "6", type = TEXT) @Pkg(label = "Output Image Path") @NotEmpty String imageOutputPath
	) {

		if("".equals(String.valueOf(x)))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "x"));

		if("".equals(String.valueOf(y)))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "y"));

		if("".equals(String.valueOf(width)))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "width"));

		if("".equals(String.valueOf(height)))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "height"));

		if("".equals(imageInputPath.trim()))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "imageInputPath"));

		if("".equals(imageOutputPath.trim()))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "imageOutputPath"));

		//imageInputPath -> imageOutputPath
		FastBitmap fb = new FastBitmap(imageInputPath);

		// X is vertical Axis, Y is Horizontal!!!
		try{
			fb.toGrayscale();
			int ImageHeight = fb.getHeight();
			int ImageWidth = fb.getWidth();

			int cropHeight = height.intValue();
			int cropWidth = width.intValue();
			int cropX = x.intValue();
			int cropY = y.intValue();

			int cropYMax = cropY+cropWidth;
			int cropXMax = cropX+cropHeight;

			//System.out.println("DEBUG Image: "+imageInputPath);
			//System.out.println("DEBUG Image Size: "+ImageWidth + ":"+ImageHeight);

			//System.out.println("DEBUG Crop Area BEFORE [Xmin:Ymin-Xmax:Ymax|Width:Height]: "+cropX+":"+cropY+"-"+cropXMax + ":"+cropYMax+"|"+cropWidth+":"+cropHeight+"]");

			if((cropHeight + cropX) >= ImageHeight) cropHeight = ImageHeight - cropX - 1;
			if((cropWidth + cropY) >= ImageWidth) cropWidth = ImageWidth - cropY - 1;

			cropYMax = cropY+cropWidth;
			cropXMax = cropX+cropHeight;

			//System.out.println("DEBUG Crop Area AFTER [Xmin:Ymin-Xmax:Ymax|Width:Height]: "+cropX+":"+cropY+"-"+cropXMax + ":"+cropYMax+"|"+cropWidth+":"+cropHeight+"]");

			//System.out.println("DEBUG Crop Area AFTER [X:Y - Width:Height]: "+cropX+":"+cropY+" - "+cropWidth + ":"+cropHeight);

			Catalano.Imaging.Filters.Crop c = new Catalano.Imaging.Filters.Crop(cropX,cropY,cropWidth,cropHeight);

			c.applyInPlace(fb);
			fb.saveAsJPG(imageOutputPath);
		}catch(Exception e){
			return new StringValue(e.getMessage().toString());
		}

		return new StringValue("");

	}


}
