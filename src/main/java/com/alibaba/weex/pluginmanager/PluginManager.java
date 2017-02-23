package com.alibaba.weex.pluginmanager;

import android.content.Context;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by budao on 2016/10/25.
 */
public class PluginManager {
  public static void loadFromConfig(Context context) {
    PluginConfig.init(context);
    registerComponents(PluginConfig.getComponents());
    registerModules(PluginConfig.getModules());
  }

  public static void registerComponent(String name, String className) {
    try {
      Class clazz = Class.forName(className);
      WXSDKEngine.registerComponent(name, clazz);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (WXException e) {
      e.printStackTrace();
    }

  }

  public static void registerModule(String name, String className) {
    try {
      Class clazz = Class.forName(className);
      WXSDKEngine.registerModule(name, clazz);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (WXException e) {
      e.printStackTrace();
    }
  }

  public static void registerComponents(HashMap<String, PluginEntry> components) {
    for (Map.Entry<String, PluginEntry> component : components.entrySet()) {
      registerComponent(component.getKey(), component.getValue().mPluginClass);
    }
  }

  public static void registerModules(HashMap<String, PluginEntry> modules) {
    for (Map.Entry<String, PluginEntry> module : modules.entrySet()) {
      registerModule(module.getKey(), module.getValue().mPluginClass);
    }
  }
}
