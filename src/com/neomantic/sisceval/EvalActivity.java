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

    					Value v = interpreter.eval("(+ 3 1)");
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