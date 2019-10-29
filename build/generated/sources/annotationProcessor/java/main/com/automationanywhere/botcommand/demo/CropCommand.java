package com.automationanywhere.botcommand.demo;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Double;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CropCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(CropCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    Crop command = new Crop();
    if(parameters.get("x") == null || parameters.get("x").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","x"));
    }

    if(parameters.get("y") == null || parameters.get("y").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","y"));
    }

    if(parameters.get("width") == null || parameters.get("width").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","width"));
    }

    if(parameters.get("height") == null || parameters.get("height").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","height"));
    }

    if(parameters.get("imageInputPath") == null || parameters.get("imageInputPath").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","imageInputPath"));
    }

    if(parameters.get("imageOutputPath") == null || parameters.get("imageOutputPath").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","imageOutputPath"));
    }

    if(parameters.get("x") != null && parameters.get("x").get() != null && !(parameters.get("x").get() instanceof Double)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","x", "Double", parameters.get("x").get().getClass().getSimpleName()));
    }
    if(parameters.get("y") != null && parameters.get("y").get() != null && !(parameters.get("y").get() instanceof Double)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","y", "Double", parameters.get("y").get().getClass().getSimpleName()));
    }
    if(parameters.get("width") != null && parameters.get("width").get() != null && !(parameters.get("width").get() instanceof Double)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","width", "Double", parameters.get("width").get().getClass().getSimpleName()));
    }
    if(parameters.get("height") != null && parameters.get("height").get() != null && !(parameters.get("height").get() instanceof Double)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","height", "Double", parameters.get("height").get().getClass().getSimpleName()));
    }
    if(parameters.get("imageInputPath") != null && parameters.get("imageInputPath").get() != null && !(parameters.get("imageInputPath").get() instanceof String)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","imageInputPath", "String", parameters.get("imageInputPath").get().getClass().getSimpleName()));
    }
    if(parameters.get("imageOutputPath") != null && parameters.get("imageOutputPath").get() != null && !(parameters.get("imageOutputPath").get() instanceof String)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","imageOutputPath", "String", parameters.get("imageOutputPath").get().getClass().getSimpleName()));
    }
    try {
      Optional<Value> result =  Optional.ofNullable(command.action(parameters.get("x") != null ? (Double)parameters.get("x").get() : (Double)null ,parameters.get("y") != null ? (Double)parameters.get("y").get() : (Double)null ,parameters.get("width") != null ? (Double)parameters.get("width").get() : (Double)null ,parameters.get("height") != null ? (Double)parameters.get("height").get() : (Double)null ,parameters.get("imageInputPath") != null ? (String)parameters.get("imageInputPath").get() : (String)null ,parameters.get("imageOutputPath") != null ? (String)parameters.get("imageOutputPath").get() : (String)null ));
      logger.traceExit(result);
      return result;
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
