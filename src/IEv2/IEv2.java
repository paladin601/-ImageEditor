/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv2;

import javax.swing.JFrame;

public class IEv2 extends JFrame{

    public IEv2(){
        IEv2UI UI = new IEv2UI();
                
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 680); //No Tocar
        this.add(UI);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        IEv2 JA = new IEv2();
    }
    
}
