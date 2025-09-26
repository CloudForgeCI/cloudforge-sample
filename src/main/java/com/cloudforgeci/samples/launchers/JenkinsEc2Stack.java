package com.cloudforgeci.samples.launchers;

import com.cloudforgeci.api.core.DeploymentContext;
import com.cloudforgeci.api.compute.JenkinsFactory;
import com.cloudforgeci.api.interfaces.SecurityProfile;
import com.cloudforgeci.api.interfaces.IAMProfile;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class JenkinsEc2Stack extends Stack {
    public JenkinsEc2Stack(final Construct scope, final String id) { 
        this(scope, id, null, SecurityProfile.DEV, IAMProfile.EXTENDED); 
    }
    
    public JenkinsEc2Stack(final Construct scope, final String id, final StackProps props) { 
        this(scope, id, props, SecurityProfile.DEV, IAMProfile.EXTENDED); 
    }
    
    public JenkinsEc2Stack(final Construct scope, final String id, final StackProps props, 
                          final SecurityProfile security, final IAMProfile iamProfile) {
        super(scope, id, props);
        DeploymentContext cfc = DeploymentContext.from(scope);

        try {
            // Use JenkinsFactory to create EC2 Jenkins deployment
            JenkinsFactory.JenkinsSystem jenkinsSystem = JenkinsFactory.createEc2(this, id, cfc);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
