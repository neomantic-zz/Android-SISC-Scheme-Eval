/* Author:: Chad Albers
 * Copyright:: Copyright (c) 2008 Chad Albers
 * License:: GNU Library General Public License Version 2
 * Copyright (c) 2010 Chad Albers <calbers@neomantic.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.

 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301, USA.
*/

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