package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;

import com.jme3.input.KeyInput;

import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */

public class Main extends SimpleApplication{
    
    
    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf){
            if(name.equals("Rotate")){
                rootNode.rotate(0 ,value*speed , 0);
          }
       }
    };

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        Geometry teaGeom = (Geometry) assetManager.loadModel("Models/heli.obj");
        teaGeom.scale((float) 0.0005);

        DirectionalLight dl1 = new DirectionalLight();
        dl1.setDirection(new Vector3f(1, -1, 1).normalizeLocal());
        dl1.setColor(ColorRGBA.White);
        rootNode.addLight(dl1);

        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-1, -1, -1).normalizeLocal());
        dl.setColor(ColorRGBA.White);
        rootNode.addLight(dl);
         FilterPostProcessor fpp = new FilterPostProcessor(assetManager);

        SSAOFilter ssao = new SSAOFilter();//0.49997783f, 42.598858f, 35.999966f, 0.39299846f
        fpp.addFilter(ssao);

        

        viewPort.addProcessor(fpp);

// DirectionalLight dl = new DirectionalLight();
        //dl.setColor(ColorRGBA.White);
        //dl.setDirection(Vector3f.UNIT_XYZ.negate());
        
        //AmbientLight dl = new AmbientLight();
        //dl.setColor(ColorRGBA.Gray);
        /* Update the model. Now it's vertical. */
      

        //rootNode.addLight(dl);
        rootNode.attachChild(teaGeom);

        // Setup first view
        viewPort.setBackgroundColor(ColorRGBA.Black);
        cam.setViewPort(.5f, 1f, 0.375f, 0.625f);
        cam.setLocation(new Vector3f(0, 0, 5f));
        
        cam.setRotation(new Quaternion(0.71f, -0.71f, 0, 0));
        
        // Setup second view
        Camera cam2 = cam.clone();
        cam2.setViewPort(0.25f, 0.75f, 0f, 0.25f);
        cam2.setLocation(new Vector3f(5, 0, 0));
        cam2.setRotation(new Quaternion(0.71f, 0f, -0.71f, 0f));

        ViewPort view2 = renderManager.createMainView("Bottom", cam2);
        view2.setClearFlags(true, true, true);
        view2.attachScene(rootNode);

        // Setup third view
        Camera cam3 = cam.clone();
        cam3.setViewPort(0f, 0.5f, 0.375f, 0.625f);
        cam3.setLocation(new Vector3f(0, 0, -5));
        cam3.setRotation(new Quaternion(0f, 0f, 0.71f, 0.71f));

        ViewPort view3 = renderManager.createMainView("Left", cam3);
        view3.setClearFlags(true, true, true);
        view3.attachScene(rootNode);

        // Setup fourth view
        Camera cam4 = cam.clone();
        cam4.setViewPort(.25f, 0.75f, .75f, 1f);
        cam4.setLocation(new Vector3f(-5, 0, 0));
        cam4.setRotation(new Quaternion(0f, -0.71f, 0f, -0.71f));
        
        
        ViewPort view4 = renderManager.createMainView("Top", cam4);
        view4.setClearFlags(true, true, true);
        view4.attachScene(rootNode);
        cam.setParallelProjection(true);
        cam3.setParallelProjection(true);
        cam2.setParallelProjection(true);
        cam4.setParallelProjection(true);
        
       
        inputManager.addMapping("Rotate",new KeyTrigger(KeyInput.KEY_I));
        inputManager.addListener(analogListener, "Rotate");
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
