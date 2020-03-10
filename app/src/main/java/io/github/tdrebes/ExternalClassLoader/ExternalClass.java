package io.github.tdrebes.ExternalClassLoader;

import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class ExternalClass {
    private final Object classInstance;
    private final Method helloWorld;
    private final Method helloWorldWithArgs;

    public ExternalClass(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File file = new File(path);
        Log.e("Main", file.getPath());
        DexClassLoader dexClassLoader = new DexClassLoader(path, file.getAbsolutePath(),
                null, this.getClass().getClassLoader());
        final Class<Object> class1 =
                (Class<Object>) dexClassLoader.loadClass(
                        "io.github.tdrebes.libdex.DexClass");

        classInstance = class1.newInstance();
        helloWorld = class1.getDeclaredMethod("helloWorld");
        Class[] par = new Class[1];
        par[0] = String.class;
        helloWorldWithArgs = class1.getDeclaredMethod("helloWorld", par);
    }

    public String helloWorld() throws InvocationTargetException, IllegalAccessException {
        return (String) helloWorld.invoke(classInstance);
    }

    public String helloWorld(String arg) throws InvocationTargetException, IllegalAccessException {
        return (String) helloWorldWithArgs.invoke(classInstance, arg);
    }
}
