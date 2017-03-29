/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.textfield;

import javafx.scene.control.TextField;

/**
 *
 * @author sergi
 */
public class StringTextField extends TextField {
    
    public StringTextField(){
        this.setPromptText("Enter only letters");
        
    }
    @Override
    public void replaceText(int i, int i1, String string)
    {
        if (string.matches("[a-zA-Z]") || string.isEmpty() || string.matches(" "))
        {
            super.replaceText(i ,i1, string);
        }
    }
    @Override
    public void replaceSelection(String string)
    {
        super.replaceSelection(string);
    }
    
    
    
}
