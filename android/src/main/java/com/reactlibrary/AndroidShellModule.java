package com.haxorkit.AndroidShell;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;


public class AndroidShellModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public AndroidShellModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "AndroidShell";
    }

    @ReactMethod
    public void executeCommand(final String command, Callback callback) {
// To avoid UI freezes run in thread 
        new Thread(new Runnable() { 
            public void run() { 
                OutputStream out = null; 
                InputStream in = null; 
                try { 
                    // Send script into runtime process 
                    Process child = Runtime.getRuntime().exec(command);
                    // Get input and output streams 
                    out = child.getOutputStream(); 
                    in = child.getInputStream();
                    //Input stream can return anything
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in)); 
                    String line; 
                    String result = ""; 
                    while ((line = bufferedReader.readLine()) != null)
                    result += line+"\n";
                    //Handle input stream returned message
                    callback.invoke(result); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } finally { 
                    if (in != null) { 
                    try { 
                        in.close(); 
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                    } if (out != null) { 
                    try { 
                        out.flush(); 
                        out.close(); 
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                    } 
                } 
            } 
        }).start(); 
    }

    //  public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
    //     // TODO: Implement some actually useful functionality
    //     callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    // }

    // public void executeCommand(String command, Callback callback) {
    //     // TODO: Implement some actually useful functionality
    //     try {
    //         Process process = Runtime.getRuntime().exec(command);

    //         StringBuilder output = new StringBuilder();

    //         BufferedReader reader = new BufferedReader(new InputStreamReader (process.getInputStream()));

    //         String line;

    //         while((line = reader.readLine()) != null) {
    //             output.append(line + "\n");
    //         }

    //         int exitVal = process.waitFor();
    //         if (exitVal == 0) {
    //             // System.out.println(output);
    //             callback.invoke(output);
    //             System.exit(0);
    //         } else {
    //             callback.invoke("Something abnormal has haapened :( ");
    //         }

    //     //    return output;
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    // }
}
