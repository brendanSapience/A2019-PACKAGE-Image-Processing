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
import Catalano.Imaging.Filters.Crop;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

/**
 * @author Bren Sapience
 *
 */
@BotCommand
@CommandPkg(label="Grayscale", name="grayscale", description="Grayscale an existing image", icon="pkg.svg",
node_label="Grayscale {{imagePath}}",
return_type=STRING, return_label="Assign the output to variable", return_required=false)
public class Grayscale {
	
	private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");
	
	@Execute
	public Value<String> action(
			@Idx(index = "1", type = TEXT) @Pkg(label = "Input Image Path", default_value_type = STRING) @NotEmpty String imageInputPath,
			@Idx(index = "2", type = TEXT) @Pkg(label = "Output Image Path", default_value_type = STRING) @NotEmpty String imageOutputPath) {
		
		if("".equals(imageInputPath.trim()))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "imageInputPath"));
		
		if("".equals(imageOutputPath.trim()))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "imageOutputPath"));

		//imageInputPath -> imageOutputPath
		FastBitmap fb = new FastBitmap(imageInputPath);
		fb.toGrayscale();

		fb.saveAsJPG(imageOutputPath);

		return new StringValue("");
		
	}
	

}
