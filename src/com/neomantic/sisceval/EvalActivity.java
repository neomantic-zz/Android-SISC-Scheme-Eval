package com.neomantic.sisceval;

import java.io.IOException;

import sisc.data.Value;
import sisc.interpreter.Context;
import sisc.interpreter.Interpreter;
import sisc.interpreter.SchemeCaller;
import sisc.interpreter.SchemeException;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;


public class EvalActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
		final TextView tv = new TextView( this );
		
    	try {
    		Context.execute(new SchemeCaller() {
    			public Object execute(Interpreter interpreter ) throws SchemeException {
    				
    				try {
    					ApplicationInfo info = getApplicationInfo();
						
    					AssetManager manager = getAssets();
    					
    					//gets all the files under assets/scm
    			    	String[] fileNames = manager.list("scm");
    	    	
    			    	//creates jar urls for each file to be loaded
    			    	String[] sourceFiles = new String[ fileNames.length ];
    			    	for( int i = 0; i < fileNames.length; i++ ) {
    			    		sourceFiles[i] = "jar:file:" + info.sourceDir + "!/assets/scm/" + fileNames[i];
    			    	} 
    			    	interpreter.loadSourceFiles( sourceFiles );
    					
    					Value v = interpreter.eval("(length)");
    			    	//show the result 
    			    	tv.setText(v.toString());
    					setContentView(tv);
    					return v;
    				} catch (IOException e) {
    					// Thrown if the given Scheme program cannot be parsed
    					e.printStackTrace();
    					return null;
    				} catch ( SchemeException e ) {
    					e.printStackTrace();
    					return null;
    				}
    			}	
    		});
    	} catch ( SchemeException e ) {
    		e.printStackTrace();
    		return;
    	}

    }
}