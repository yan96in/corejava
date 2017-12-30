package classLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.io.*;

/**
 * Created by the best programmer on 2017/10/29.
 * this program demonstrates a custom class loader that decrypts class files.
 */
public class ClassLoderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new ClassLoderFrame();
            frame.setTitle("ClassLoaderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
class ClassLoderFrame extends JFrame{
    private JTextField keyField=new JTextField("3",4);
    private JTextField nameField=new JTextField("Calculator",30);
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;

    public ClassLoderFrame() {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new GridBagLayout());
        //add(new JLabel("class"),new GBC(0,0).setAnchor(GBC.EAST));
        //add(new JLabel(nameField,new GBC(1,0).setWeight(100,0).setAnchor(GBC.WEST));
        //add(new JLabel("key"),new GBC(0,1).setAnchor(GBC.EAST));
        //add(new JLabel(keyField,new GBC(1,1).setWeight(100,0).setAnchor(GBC.WEST));
        JButton loadButton=new JButton("load");
        //add(loadButton,new GBC(0,2,2,1));
        loadButton.addActionListener(e -> runClass(nameField.getText(),keyField.getText()));
    }

    public void runClass(String name,String key){
        try{
            ClassLoader loader=new CrytoClassLoader(Integer.parseInt(key));
            Class<?> c=loader.loadClass(name);
            Method m=c.getMethod("main",String[].class);
            m.invoke(null,(Object)new String[]{} );
        }catch (Throwable e){
            JOptionPane.showMessageDialog(this,e);
        }
    }
}
/*
* this class loader loads encrypted class files
*/
class CrytoClassLoader extends ClassLoader{
    private int key;

    public CrytoClassLoader(int key) {
        this.key = key;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte[] classBytes=null;
            classBytes=loadClassBytes(name);
            Class<?> cl=defineClass(name,classBytes,0,classBytes.length);
            if(cl==null)throw new ClassNotFoundException(name);
            return cl;
        }catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }
    /*
    * loads and decrypt the class file bytes.
     */
    private byte[] loadClassBytes(String name) throws IOException{

        String cname=name.replace('.','/')+".caesar";
        byte[] bytes= Files.readAllBytes(Paths.get(cname));
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] =(byte)(bytes[i]-key);
        }
        return bytes;
    }
}